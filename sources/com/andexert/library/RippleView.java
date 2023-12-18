package com.andexert.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import com.prolificinteractive.materialcalendarview.TitleChanger;
/* loaded from: classes.dex */
public class RippleView extends RelativeLayout {
    private int HEIGHT;
    private int WIDTH;
    private boolean animationRunning;
    private Handler canvasHandler;
    private int durationEmpty;
    private int frameRate;
    private GestureDetector gestureDetector;
    private Boolean hasToZoom;
    private Boolean isCentered;
    private OnRippleCompleteListener onCompletionListener;
    private Bitmap originBitmap;
    private Paint paint;
    private float radiusMax;
    private int rippleAlpha;
    private int rippleColor;
    private int rippleDuration;
    private int ripplePadding;
    private Integer rippleType;
    private final Runnable runnable;
    private ScaleAnimation scaleAnimation;
    private int timer;
    private int timerEmpty;
    private float x;
    private float y;
    private int zoomDuration;
    private float zoomScale;

    /* loaded from: classes.dex */
    public interface OnRippleCompleteListener {
        void onComplete(RippleView rippleView);
    }

    public RippleView(Context context) {
        super(context);
        this.frameRate = 10;
        this.rippleDuration = TitleChanger.DEFAULT_ANIMATION_DELAY;
        this.rippleAlpha = 90;
        this.radiusMax = 0.0f;
        this.animationRunning = false;
        this.timer = 0;
        this.timerEmpty = 0;
        this.durationEmpty = -1;
        this.x = -1.0f;
        this.y = -1.0f;
        this.runnable = new Runnable() { // from class: com.andexert.library.RippleView.1
            @Override // java.lang.Runnable
            public void run() {
                RippleView.this.invalidate();
            }
        };
    }

    public RippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.frameRate = 10;
        this.rippleDuration = TitleChanger.DEFAULT_ANIMATION_DELAY;
        this.rippleAlpha = 90;
        this.radiusMax = 0.0f;
        this.animationRunning = false;
        this.timer = 0;
        this.timerEmpty = 0;
        this.durationEmpty = -1;
        this.x = -1.0f;
        this.y = -1.0f;
        this.runnable = new Runnable() { // from class: com.andexert.library.RippleView.1
            @Override // java.lang.Runnable
            public void run() {
                RippleView.this.invalidate();
            }
        };
        init(context, attributeSet);
    }

    public RippleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.frameRate = 10;
        this.rippleDuration = TitleChanger.DEFAULT_ANIMATION_DELAY;
        this.rippleAlpha = 90;
        this.radiusMax = 0.0f;
        this.animationRunning = false;
        this.timer = 0;
        this.timerEmpty = 0;
        this.durationEmpty = -1;
        this.x = -1.0f;
        this.y = -1.0f;
        this.runnable = new Runnable() { // from class: com.andexert.library.RippleView.1
            @Override // java.lang.Runnable
            public void run() {
                RippleView.this.invalidate();
            }
        };
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleView);
        this.rippleColor = obtainStyledAttributes.getColor(R.styleable.RippleView_rv_color, getResources().getColor(R.color.rippelColor));
        this.rippleType = Integer.valueOf(obtainStyledAttributes.getInt(R.styleable.RippleView_rv_type, 0));
        this.hasToZoom = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.RippleView_rv_zoom, false));
        this.isCentered = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.RippleView_rv_centered, false));
        this.rippleDuration = obtainStyledAttributes.getInteger(R.styleable.RippleView_rv_rippleDuration, this.rippleDuration);
        this.frameRate = obtainStyledAttributes.getInteger(R.styleable.RippleView_rv_framerate, this.frameRate);
        this.rippleAlpha = obtainStyledAttributes.getInteger(R.styleable.RippleView_rv_alpha, this.rippleAlpha);
        this.ripplePadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleView_rv_ripplePadding, 0);
        this.canvasHandler = new Handler();
        this.zoomScale = obtainStyledAttributes.getFloat(R.styleable.RippleView_rv_zoomScale, 1.03f);
        this.zoomDuration = obtainStyledAttributes.getInt(R.styleable.RippleView_rv_zoomDuration, 200);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.rippleColor);
        this.paint.setAlpha(this.rippleAlpha);
        setWillNotDraw(false);
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.andexert.library.RippleView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
                RippleView.this.animateRipple(motionEvent);
                RippleView.this.sendClickEvent(true);
            }
        });
        setDrawingCacheEnabled(true);
        setClickable(true);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.animationRunning) {
            int i = this.rippleDuration;
            int i2 = this.timer;
            int i3 = this.frameRate;
            if (i <= i2 * i3) {
                this.animationRunning = false;
                this.timer = 0;
                this.durationEmpty = -1;
                this.timerEmpty = 0;
                canvas.restore();
                invalidate();
                OnRippleCompleteListener onRippleCompleteListener = this.onCompletionListener;
                if (onRippleCompleteListener != null) {
                    onRippleCompleteListener.onComplete(this);
                    return;
                }
                return;
            }
            this.canvasHandler.postDelayed(this.runnable, i3);
            if (this.timer == 0) {
                canvas.save();
            }
            canvas.drawCircle(this.x, this.y, this.radiusMax * ((this.timer * this.frameRate) / this.rippleDuration), this.paint);
            this.paint.setColor(Color.parseColor("#ffff4444"));
            if (this.rippleType.intValue() == 1 && this.originBitmap != null) {
                int i4 = this.timer;
                int i5 = this.frameRate;
                float f = i4 * i5;
                int i6 = this.rippleDuration;
                if (f / i6 > 0.4f) {
                    if (this.durationEmpty == -1) {
                        this.durationEmpty = i6 - (i4 * i5);
                    }
                    int i7 = this.timerEmpty + 1;
                    this.timerEmpty = i7;
                    Bitmap circleBitmap = getCircleBitmap((int) (this.radiusMax * ((i7 * i5) / this.durationEmpty)));
                    canvas.drawBitmap(circleBitmap, 0.0f, 0.0f, this.paint);
                    circleBitmap.recycle();
                }
            }
            this.paint.setColor(this.rippleColor);
            if (this.rippleType.intValue() == 1) {
                int i8 = this.frameRate;
                if ((this.timer * i8) / this.rippleDuration > 0.6f) {
                    Paint paint = this.paint;
                    int i9 = this.rippleAlpha;
                    paint.setAlpha((int) (i9 - (i9 * ((this.timerEmpty * i8) / this.durationEmpty))));
                } else {
                    this.paint.setAlpha(this.rippleAlpha);
                }
            } else {
                Paint paint2 = this.paint;
                int i10 = this.rippleAlpha;
                paint2.setAlpha((int) (i10 - (i10 * ((this.timer * this.frameRate) / this.rippleDuration))));
            }
            this.timer++;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.WIDTH = i;
        this.HEIGHT = i2;
        float f = this.zoomScale;
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, f, 1.0f, f, i / 2, i2 / 2);
        this.scaleAnimation = scaleAnimation;
        scaleAnimation.setDuration(this.zoomDuration);
        this.scaleAnimation.setRepeatMode(2);
        this.scaleAnimation.setRepeatCount(1);
    }

    public void animateRipple(MotionEvent motionEvent) {
        createAnimation(motionEvent.getX(), motionEvent.getY());
    }

    public void animateRipple(float f, float f2) {
        createAnimation(f, f2);
    }

    private void createAnimation(float f, float f2) {
        if (!isEnabled() || this.animationRunning) {
            return;
        }
        if (this.hasToZoom.booleanValue()) {
            startAnimation(this.scaleAnimation);
        }
        this.radiusMax = Math.max(this.WIDTH, this.HEIGHT);
        if (this.rippleType.intValue() != 2) {
            this.radiusMax /= 2.0f;
        }
        this.radiusMax -= this.ripplePadding;
        if (this.isCentered.booleanValue() || this.rippleType.intValue() == 1) {
            this.x = getMeasuredWidth() / 2;
            this.y = getMeasuredHeight() / 2;
        } else {
            this.x = f;
            this.y = f2;
        }
        this.animationRunning = true;
        if (this.rippleType.intValue() == 1 && this.originBitmap == null) {
            this.originBitmap = getDrawingCache(true);
        }
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.gestureDetector.onTouchEvent(motionEvent)) {
            animateRipple(motionEvent);
            sendClickEvent(false);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendClickEvent(Boolean bool) {
        if (getParent() instanceof AdapterView) {
            AdapterView<?> adapterView = (AdapterView) getParent();
            int positionForView = adapterView.getPositionForView(this);
            long itemIdAtPosition = adapterView.getItemIdAtPosition(positionForView);
            if (bool.booleanValue()) {
                if (adapterView.getOnItemLongClickListener() != null) {
                    adapterView.getOnItemLongClickListener().onItemLongClick(adapterView, this, positionForView, itemIdAtPosition);
                }
            } else if (adapterView.getOnItemClickListener() != null) {
                adapterView.getOnItemClickListener().onItemClick(adapterView, this, positionForView, itemIdAtPosition);
            }
        }
    }

    private Bitmap getCircleBitmap(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(this.originBitmap.getWidth(), this.originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        float f = this.x;
        float f2 = i;
        float f3 = this.y;
        Rect rect = new Rect((int) (f - f2), (int) (f3 - f2), (int) (f + f2), (int) (f3 + f2));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(this.x, this.y, f2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(this.originBitmap, rect, rect, paint);
        return createBitmap;
    }

    public void setRippleColor(int i) {
        this.rippleColor = getResources().getColor(i);
    }

    public int getRippleColor() {
        return this.rippleColor;
    }

    public RippleType getRippleType() {
        return RippleType.values()[this.rippleType.intValue()];
    }

    public void setRippleType(RippleType rippleType) {
        this.rippleType = Integer.valueOf(rippleType.ordinal());
    }

    public Boolean isCentered() {
        return this.isCentered;
    }

    public void setCentered(Boolean bool) {
        this.isCentered = bool;
    }

    public int getRipplePadding() {
        return this.ripplePadding;
    }

    public void setRipplePadding(int i) {
        this.ripplePadding = i;
    }

    public Boolean isZooming() {
        return this.hasToZoom;
    }

    public void setZooming(Boolean bool) {
        this.hasToZoom = bool;
    }

    public float getZoomScale() {
        return this.zoomScale;
    }

    public void setZoomScale(float f) {
        this.zoomScale = f;
    }

    public int getZoomDuration() {
        return this.zoomDuration;
    }

    public void setZoomDuration(int i) {
        this.zoomDuration = i;
    }

    public int getRippleDuration() {
        return this.rippleDuration;
    }

    public void setRippleDuration(int i) {
        this.rippleDuration = i;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public void setFrameRate(int i) {
        this.frameRate = i;
    }

    public int getRippleAlpha() {
        return this.rippleAlpha;
    }

    public void setRippleAlpha(int i) {
        this.rippleAlpha = i;
    }

    public void setOnRippleCompleteListener(OnRippleCompleteListener onRippleCompleteListener) {
        this.onCompletionListener = onRippleCompleteListener;
    }

    /* loaded from: classes.dex */
    public enum RippleType {
        SIMPLE(0),
        DOUBLE(1),
        RECTANGLE(2);
        
        int type;

        RippleType(int i) {
            this.type = i;
        }
    }
}
