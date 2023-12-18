package com.github.florent37.singledateandtimepicker.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import com.github.florent37.singledateandtimepicker.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class WheelPicker<V> extends View {
    public static final int ALIGN_CENTER = 0;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    protected static final String FORMAT = "%1$02d";
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SCROLLING = 2;
    protected Adapter<V> adapter;
    private final Camera camera;
    private int currentItemPosition;
    protected V defaultValue;
    private int downPointY;
    private int drawnCenterX;
    private int drawnCenterY;
    private final Handler handler;
    private boolean hasAtmospheric;
    private boolean hasCurtain;
    private boolean hasIndicator;
    private boolean hasSameWidth;
    private boolean isClick;
    private boolean isCurved;
    private boolean isCyclic;
    private boolean isForceFinishScroll;
    private int lastPointY;
    protected int lastScrollPosition;
    protected Listener<WheelPicker, V> listener;
    private int mCurtainColor;
    private int mDrawnItemCount;
    private int mHalfDrawnItemCount;
    private int mHalfItemHeight;
    private int mHalfWheelHeight;
    private int mIndicatorColor;
    private int mIndicatorSize;
    private int mItemAlign;
    private int mItemHeight;
    private int mItemSpace;
    private int mItemTextColor;
    private int mItemTextSize;
    private int mSelectedItemTextColor;
    private int mTextMaxHeight;
    private int mTextMaxWidth;
    private int mVisibleItemCount;
    private final Matrix matrixDepth;
    private final Matrix matrixRotate;
    private int maxFlingY;
    private String maxWidthText;
    private int maximumVelocity;
    private int minFlingY;
    private int minimumVelocity;
    private OnItemSelectedListener onItemSelectedListener;
    private OnWheelChangeListener onWheelChangeListener;
    private Paint paint;
    private final Rect rectCurrentItem;
    private final Rect rectDrawn;
    private final Rect rectIndicatorFoot;
    private final Rect rectIndicatorHead;
    private Runnable runnable;
    private int scrollOffsetY;
    private Scroller scroller;
    private int selectedItemPosition;
    private int textMaxWidthPosition;
    private int touchSlop;
    private VelocityTracker tracker;
    private int wheelCenterX;
    private int wheelCenterY;

    /* loaded from: classes.dex */
    public interface BaseAdapter<V> {
        V getItem(int i);

        int getItemCount();

        String getItemText(int i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface Listener<PICKER extends WheelPicker, V> {
        void onCurrentScrolled(PICKER picker, int i, V v);

        void onSelected(PICKER picker, int i, V v);
    }

    /* loaded from: classes.dex */
    public interface OnItemSelectedListener {
        void onCurrentItemOfScroll(WheelPicker wheelPicker, int i);

        void onItemSelected(WheelPicker wheelPicker, Object obj, int i);
    }

    /* loaded from: classes.dex */
    public interface OnWheelChangeListener {
        void onWheelScrollStateChanged(int i);

        void onWheelScrolled(int i);

        void onWheelSelected(int i);
    }

    protected abstract List<V> generateAdapterValues();

    protected abstract void init();

    protected abstract V initDefault();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onFinishedLoop() {
    }

    public WheelPicker(Context context) {
        this(context, null);
    }

    public WheelPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.handler = new Handler();
        this.adapter = new Adapter<>();
        this.rectDrawn = new Rect();
        this.rectIndicatorHead = new Rect();
        this.rectIndicatorFoot = new Rect();
        this.rectCurrentItem = new Rect();
        this.camera = new Camera();
        this.matrixRotate = new Matrix();
        this.matrixDepth = new Matrix();
        this.minimumVelocity = 50;
        this.maximumVelocity = 8000;
        this.touchSlop = 8;
        this.runnable = new Runnable() { // from class: com.github.florent37.singledateandtimepicker.widget.WheelPicker.1
            @Override // java.lang.Runnable
            public void run() {
                int itemCount;
                if (WheelPicker.this.adapter == null || (itemCount = WheelPicker.this.adapter.getItemCount()) == 0) {
                    return;
                }
                if (WheelPicker.this.scroller.isFinished() && !WheelPicker.this.isForceFinishScroll) {
                    if (WheelPicker.this.mItemHeight == 0) {
                        return;
                    }
                    int i = (((-WheelPicker.this.scrollOffsetY) / WheelPicker.this.mItemHeight) + WheelPicker.this.selectedItemPosition) % itemCount;
                    if (i < 0) {
                        i += itemCount;
                    }
                    WheelPicker.this.currentItemPosition = i;
                    WheelPicker.this.onItemSelected();
                    if (WheelPicker.this.onWheelChangeListener != null) {
                        WheelPicker.this.onWheelChangeListener.onWheelSelected(i);
                        WheelPicker.this.onWheelChangeListener.onWheelScrollStateChanged(0);
                    }
                }
                if (WheelPicker.this.scroller.computeScrollOffset()) {
                    if (WheelPicker.this.onWheelChangeListener != null) {
                        WheelPicker.this.onWheelChangeListener.onWheelScrollStateChanged(2);
                    }
                    WheelPicker wheelPicker = WheelPicker.this;
                    wheelPicker.scrollOffsetY = wheelPicker.scroller.getCurrY();
                    int i2 = (((-WheelPicker.this.scrollOffsetY) / WheelPicker.this.mItemHeight) + WheelPicker.this.selectedItemPosition) % itemCount;
                    if (WheelPicker.this.onItemSelectedListener != null) {
                        WheelPicker.this.onItemSelectedListener.onCurrentItemOfScroll(WheelPicker.this, i2);
                    }
                    WheelPicker wheelPicker2 = WheelPicker.this;
                    wheelPicker2.onItemCurrentScroll(i2, wheelPicker2.adapter.getItem(i2));
                    WheelPicker.this.postInvalidate();
                    WheelPicker.this.handler.postDelayed(this, 16L);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WheelPicker);
        this.mItemTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_text_size, getResources().getDimensionPixelSize(R.dimen.WheelItemTextSize));
        this.mVisibleItemCount = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_visible_item_count, 7);
        this.selectedItemPosition = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_selected_item_position, 0);
        this.hasSameWidth = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_same_width, false);
        this.textMaxWidthPosition = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_maximum_width_text_position, -1);
        this.maxWidthText = obtainStyledAttributes.getString(R.styleable.WheelPicker_wheel_maximum_width_text);
        this.mSelectedItemTextColor = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_selected_item_text_color, -1);
        this.mItemTextColor = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_item_text_color, -7829368);
        this.mItemSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_item_space, getResources().getDimensionPixelSize(R.dimen.WheelItemSpace));
        this.isCyclic = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_cyclic, false);
        this.hasIndicator = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_indicator, false);
        this.mIndicatorColor = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_indicator_color, -1166541);
        this.mIndicatorSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelPicker_wheel_indicator_size, getResources().getDimensionPixelSize(R.dimen.WheelIndicatorSize));
        this.hasCurtain = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_curtain, false);
        this.mCurtainColor = obtainStyledAttributes.getColor(R.styleable.WheelPicker_wheel_curtain_color, -1996488705);
        this.hasAtmospheric = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_atmospheric, false);
        this.isCurved = obtainStyledAttributes.getBoolean(R.styleable.WheelPicker_wheel_curved, false);
        this.mItemAlign = obtainStyledAttributes.getInt(R.styleable.WheelPicker_wheel_item_align, 0);
        obtainStyledAttributes.recycle();
        updateVisibleItemCount();
        Paint paint = new Paint(69);
        this.paint = paint;
        paint.setTextSize(this.mItemTextSize);
        this.scroller = new Scroller(getContext());
        if (Build.VERSION.SDK_INT >= 4) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
            this.minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.touchSlop = viewConfiguration.getScaledTouchSlop();
        }
        init();
        this.defaultValue = initDefault();
        this.adapter.setData(generateAdapterValues());
        int itemPosition = this.adapter.getItemPosition(this.defaultValue);
        this.currentItemPosition = itemPosition;
        this.selectedItemPosition = itemPosition;
    }

    public void updateAdapter() {
        this.adapter.setData(generateAdapterValues());
        notifyDatasetChanged();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAdapter(this.adapter);
        setDefault(this.defaultValue);
    }

    private void updateVisibleItemCount() {
        int i = this.mVisibleItemCount;
        if (i < 2) {
            throw new ArithmeticException("Wheel's visible item count can not be less than 2!");
        }
        if (i % 2 == 0) {
            this.mVisibleItemCount = i + 1;
        }
        int i2 = this.mVisibleItemCount + 2;
        this.mDrawnItemCount = i2;
        this.mHalfDrawnItemCount = i2 / 2;
    }

    private void computeTextSize() {
        this.mTextMaxHeight = 0;
        this.mTextMaxWidth = 0;
        if (this.hasSameWidth) {
            this.mTextMaxWidth = (int) this.paint.measureText(this.adapter.getItemText(0));
        } else if (isPosInRang(this.textMaxWidthPosition)) {
            this.mTextMaxWidth = (int) this.paint.measureText(this.adapter.getItemText(this.textMaxWidthPosition));
        } else if (!TextUtils.isEmpty(this.maxWidthText)) {
            this.mTextMaxWidth = (int) this.paint.measureText(this.maxWidthText);
        } else {
            int itemCount = this.adapter.getItemCount();
            for (int i = 0; i < itemCount; i++) {
                String itemText = this.adapter.getItemText(i);
                this.mTextMaxWidth = Math.max(this.mTextMaxWidth, (int) this.paint.measureText(itemText));
            }
        }
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        this.mTextMaxHeight = (int) (fontMetrics.bottom - fontMetrics.top);
    }

    private void updateItemTextAlign() {
        int i = this.mItemAlign;
        if (i == 1) {
            this.paint.setTextAlign(Paint.Align.LEFT);
        } else if (i == 2) {
            this.paint.setTextAlign(Paint.Align.RIGHT);
        } else {
            this.paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    protected void updateDefault() {
        setSelectedItemPosition(getDefaultItemPosition());
    }

    public void setDefault(V v) {
        this.defaultValue = v;
        updateDefault();
    }

    public void setDefaultDate(Date date) {
        Adapter<V> adapter = this.adapter;
        if (adapter == null || adapter.getItemCount() <= 0) {
            return;
        }
        int findIndexOfDate = findIndexOfDate(date);
        this.defaultValue = this.adapter.getData().get(findIndexOfDate);
        setSelectedItemPosition(findIndexOfDate);
    }

    public void selectDate(Date date) {
        setSelectedItemPosition(findIndexOfDate(date));
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.mTextMaxWidth;
        int i4 = this.mTextMaxHeight;
        int i5 = this.mVisibleItemCount;
        int i6 = (i4 * i5) + (this.mItemSpace * (i5 - 1));
        if (this.isCurved) {
            i6 = (int) ((i6 * 2) / 3.141592653589793d);
        }
        setMeasuredDimension(measureSize(mode, size, i3 + getPaddingLeft() + getPaddingRight()), measureSize(mode2, size2, i6 + getPaddingTop() + getPaddingBottom()));
    }

    private int measureSize(int i, int i2, int i3) {
        return i == 1073741824 ? i2 : i == Integer.MIN_VALUE ? Math.min(i3, i2) : i3;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.rectDrawn.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        this.wheelCenterX = this.rectDrawn.centerX();
        this.wheelCenterY = this.rectDrawn.centerY();
        computeDrawnCenter();
        this.mHalfWheelHeight = this.rectDrawn.height() / 2;
        int height = this.rectDrawn.height() / this.mVisibleItemCount;
        this.mItemHeight = height;
        this.mHalfItemHeight = height / 2;
        computeFlingLimitY();
        computeIndicatorRect();
        computeCurrentItemRect();
    }

    private void computeDrawnCenter() {
        int i = this.mItemAlign;
        if (i == 1) {
            this.drawnCenterX = this.rectDrawn.left;
        } else if (i == 2) {
            this.drawnCenterX = this.rectDrawn.right;
        } else {
            this.drawnCenterX = this.wheelCenterX;
        }
        this.drawnCenterY = (int) (this.wheelCenterY - ((this.paint.ascent() + this.paint.descent()) / 2.0f));
    }

    private void computeFlingLimitY() {
        int i = this.selectedItemPosition;
        int i2 = this.mItemHeight;
        int i3 = i * i2;
        this.minFlingY = this.isCyclic ? Integer.MIN_VALUE : ((-i2) * (this.adapter.getItemCount() - 1)) + i3;
        if (this.isCyclic) {
            i3 = Integer.MAX_VALUE;
        }
        this.maxFlingY = i3;
    }

    private void computeIndicatorRect() {
        if (this.hasIndicator) {
            int i = this.mIndicatorSize / 2;
            int i2 = this.wheelCenterY;
            int i3 = this.mHalfItemHeight;
            int i4 = i2 + i3;
            int i5 = i2 - i3;
            this.rectIndicatorHead.set(this.rectDrawn.left, i4 - i, this.rectDrawn.right, i4 + i);
            this.rectIndicatorFoot.set(this.rectDrawn.left, i5 - i, this.rectDrawn.right, i5 + i);
        }
    }

    private void computeCurrentItemRect() {
        if (this.hasCurtain || this.mSelectedItemTextColor != -1) {
            this.rectCurrentItem.set(this.rectDrawn.left, this.wheelCenterY - this.mHalfItemHeight, this.rectDrawn.right, this.wheelCenterY + this.mHalfItemHeight);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        String itemText;
        int i;
        int i2;
        OnWheelChangeListener onWheelChangeListener = this.onWheelChangeListener;
        if (onWheelChangeListener != null) {
            onWheelChangeListener.onWheelScrolled(this.scrollOffsetY);
        }
        int i3 = this.mItemHeight;
        int i4 = this.mHalfDrawnItemCount;
        if (i3 - i4 <= 0) {
            return;
        }
        int i5 = ((-this.scrollOffsetY) / i3) - i4;
        int i6 = this.selectedItemPosition + i5;
        int i7 = -i4;
        while (i6 < this.selectedItemPosition + i5 + this.mDrawnItemCount) {
            if (this.isCyclic) {
                int itemCount = this.adapter.getItemCount();
                int i8 = i6 % itemCount;
                if (i8 < 0) {
                    i8 += itemCount;
                }
                itemText = this.adapter.getItemText(i8);
            } else {
                itemText = isPosInRang(i6) ? this.adapter.getItemText(i6) : "";
            }
            this.paint.setColor(this.mItemTextColor);
            this.paint.setStyle(Paint.Style.FILL);
            int i9 = this.drawnCenterY;
            int i10 = this.mItemHeight;
            int i11 = (i7 * i10) + i9 + (this.scrollOffsetY % i10);
            if (this.isCurved) {
                float abs = (((i9 - Math.abs(i9 - i11)) - this.rectDrawn.top) * 1.0f) / (this.drawnCenterY - this.rectDrawn.top);
                int i12 = this.drawnCenterY;
                float f = (-(1.0f - abs)) * 90.0f * (i11 > i12 ? 1 : i11 < i12 ? -1 : 0);
                if (f < -90.0f) {
                    f = -90.0f;
                }
                float f2 = f <= 90.0f ? f : 90.0f;
                i = computeSpace((int) f2);
                int i13 = this.wheelCenterX;
                int i14 = this.mItemAlign;
                if (i14 == 1) {
                    i13 = this.rectDrawn.left;
                } else if (i14 == 2) {
                    i13 = this.rectDrawn.right;
                }
                int i15 = this.wheelCenterY - i;
                this.camera.save();
                this.camera.rotateX(f2);
                this.camera.getMatrix(this.matrixRotate);
                this.camera.restore();
                float f3 = -i13;
                float f4 = -i15;
                this.matrixRotate.preTranslate(f3, f4);
                float f5 = i13;
                float f6 = i15;
                this.matrixRotate.postTranslate(f5, f6);
                this.camera.save();
                this.camera.translate(0.0f, 0.0f, computeDepth(i2));
                this.camera.getMatrix(this.matrixDepth);
                this.camera.restore();
                this.matrixDepth.preTranslate(f3, f4);
                this.matrixDepth.postTranslate(f5, f6);
                this.matrixRotate.postConcat(this.matrixDepth);
            } else {
                i = 0;
            }
            if (this.hasAtmospheric) {
                int i16 = this.drawnCenterY;
                int abs2 = (int) ((((i16 - Math.abs(i16 - i11)) * 1.0f) / this.drawnCenterY) * 255.0f);
                this.paint.setAlpha(abs2 < 0 ? 0 : abs2);
            }
            if (this.isCurved) {
                i11 = this.drawnCenterY - i;
            }
            if (this.mSelectedItemTextColor != -1) {
                canvas.save();
                if (this.isCurved) {
                    canvas.concat(this.matrixRotate);
                }
                canvas.clipRect(this.rectCurrentItem, Region.Op.DIFFERENCE);
                float f7 = i11;
                canvas.drawText(itemText, this.drawnCenterX, f7, this.paint);
                canvas.restore();
                this.paint.setColor(this.mSelectedItemTextColor);
                canvas.save();
                if (this.isCurved) {
                    canvas.concat(this.matrixRotate);
                }
                canvas.clipRect(this.rectCurrentItem);
                canvas.drawText(itemText, this.drawnCenterX, f7, this.paint);
                canvas.restore();
            } else {
                canvas.save();
                canvas.clipRect(this.rectDrawn);
                if (this.isCurved) {
                    canvas.concat(this.matrixRotate);
                }
                canvas.drawText(itemText, this.drawnCenterX, i11, this.paint);
                canvas.restore();
            }
            i6++;
            i7++;
        }
        if (this.hasCurtain) {
            this.paint.setColor(this.mCurtainColor);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(this.rectCurrentItem, this.paint);
        }
        if (this.hasIndicator) {
            this.paint.setColor(this.mIndicatorColor);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(this.rectIndicatorHead, this.paint);
            canvas.drawRect(this.rectIndicatorFoot, this.paint);
        }
    }

    private boolean isPosInRang(int i) {
        return i >= 0 && i < this.adapter.getItemCount();
    }

    private int computeSpace(int i) {
        return (int) (Math.sin(Math.toRadians(i)) * this.mHalfWheelHeight);
    }

    private int computeDepth(int i) {
        return (int) (this.mHalfWheelHeight - (Math.cos(Math.toRadians(i)) * this.mHalfWheelHeight));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            int action = motionEvent.getAction();
            if (action == 0) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                VelocityTracker velocityTracker = this.tracker;
                if (velocityTracker == null) {
                    this.tracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                this.tracker.addMovement(motionEvent);
                if (!this.scroller.isFinished()) {
                    this.scroller.abortAnimation();
                    this.isForceFinishScroll = true;
                }
                int y = (int) motionEvent.getY();
                this.lastPointY = y;
                this.downPointY = y;
            } else if (action == 1) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                if (!this.isClick) {
                    this.tracker.addMovement(motionEvent);
                    if (Build.VERSION.SDK_INT >= 4) {
                        this.tracker.computeCurrentVelocity(1000, this.maximumVelocity);
                    } else {
                        this.tracker.computeCurrentVelocity(1000);
                    }
                    this.isForceFinishScroll = false;
                    int yVelocity = (int) this.tracker.getYVelocity();
                    if (Math.abs(yVelocity) > this.minimumVelocity) {
                        this.scroller.fling(0, this.scrollOffsetY, 0, yVelocity, 0, 0, this.minFlingY, this.maxFlingY);
                        Scroller scroller = this.scroller;
                        scroller.setFinalY(scroller.getFinalY() + computeDistanceToEndPoint(this.scroller.getFinalY() % this.mItemHeight));
                    } else {
                        Scroller scroller2 = this.scroller;
                        int i = this.scrollOffsetY;
                        scroller2.startScroll(0, i, 0, computeDistanceToEndPoint(i % this.mItemHeight));
                    }
                    if (!this.isCyclic) {
                        int finalY = this.scroller.getFinalY();
                        int i2 = this.maxFlingY;
                        if (finalY > i2) {
                            this.scroller.setFinalY(i2);
                        } else {
                            int finalY2 = this.scroller.getFinalY();
                            int i3 = this.minFlingY;
                            if (finalY2 < i3) {
                                this.scroller.setFinalY(i3);
                            }
                        }
                    }
                    this.handler.post(this.runnable);
                    VelocityTracker velocityTracker2 = this.tracker;
                    if (velocityTracker2 != null) {
                        velocityTracker2.recycle();
                        this.tracker = null;
                    }
                }
            } else if (action != 2) {
                if (action == 3) {
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    VelocityTracker velocityTracker3 = this.tracker;
                    if (velocityTracker3 != null) {
                        velocityTracker3.recycle();
                        this.tracker = null;
                    }
                }
            } else if (Math.abs(this.downPointY - motionEvent.getY()) < this.touchSlop && computeDistanceToEndPoint(this.scroller.getFinalY() % this.mItemHeight) > 0) {
                this.isClick = true;
            } else {
                this.isClick = false;
                this.tracker.addMovement(motionEvent);
                OnWheelChangeListener onWheelChangeListener = this.onWheelChangeListener;
                if (onWheelChangeListener != null) {
                    onWheelChangeListener.onWheelScrollStateChanged(1);
                }
                float y2 = motionEvent.getY() - this.lastPointY;
                if (Math.abs(y2) >= 1.0f) {
                    this.scrollOffsetY = (int) (this.scrollOffsetY + y2);
                    this.lastPointY = (int) motionEvent.getY();
                    invalidate();
                }
            }
        }
        return true;
    }

    private int computeDistanceToEndPoint(int i) {
        int i2;
        if (Math.abs(i) > this.mHalfItemHeight) {
            if (this.scrollOffsetY < 0) {
                i2 = -this.mItemHeight;
            } else {
                i2 = this.mItemHeight;
            }
            return i2 - i;
        }
        return -i;
    }

    public void scrollTo(final int i) {
        int i2 = this.currentItemPosition;
        if (i != i2) {
            int i3 = this.scrollOffsetY;
            ValueAnimator ofInt = ValueAnimator.ofInt(i3, ((i2 - i) * this.mItemHeight) + i3);
            ofInt.setDuration(300L);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.github.florent37.singledateandtimepicker.widget.WheelPicker.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    WheelPicker.this.scrollOffsetY = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    WheelPicker.this.invalidate();
                }
            });
            ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.github.florent37.singledateandtimepicker.widget.WheelPicker.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    WheelPicker.this.currentItemPosition = i;
                    WheelPicker.this.onItemSelected();
                }
            });
            ofInt.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onItemSelected() {
        int i = this.currentItemPosition;
        V item = this.adapter.getItem(i);
        OnItemSelectedListener onItemSelectedListener = this.onItemSelectedListener;
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(this, item, i);
        }
        onItemSelected(i, item);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onItemSelected(int i, V v) {
        Listener<WheelPicker, V> listener = this.listener;
        if (listener != null) {
            listener.onSelected(this, i, v);
        }
    }

    protected void onItemCurrentScroll(int i, V v) {
        if (this.lastScrollPosition != i) {
            Listener<WheelPicker, V> listener = this.listener;
            if (listener != null) {
                listener.onCurrentScrolled(this, i, v);
                if (this.lastScrollPosition == this.adapter.getItemCount() - 1 && i == 0) {
                    onFinishedLoop();
                }
            }
            this.lastScrollPosition = i;
        }
    }

    protected String getFormattedValue(Object obj) {
        return String.valueOf(obj);
    }

    public int getVisibleItemCount() {
        return this.mVisibleItemCount;
    }

    public void setVisibleItemCount(int i) {
        this.mVisibleItemCount = i;
        updateVisibleItemCount();
        requestLayout();
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        computeFlingLimitY();
        invalidate();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public void setSelectedItemPosition(int i) {
        int max = Math.max(Math.min(i, this.adapter.getItemCount() - 1), 0);
        this.selectedItemPosition = max;
        this.currentItemPosition = max;
        this.scrollOffsetY = 0;
        computeFlingLimitY();
        requestLayout();
        invalidate();
    }

    public int getCurrentItemPosition() {
        return this.currentItemPosition;
    }

    public int getDefaultItemPosition() {
        return this.adapter.getData().indexOf(this.defaultValue);
    }

    public int getTodayItemPosition() {
        return this.adapter.getData().indexOf(getResources().getString(R.string.picker_today));
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        updateItemTextAlign();
        computeTextSize();
        notifyDatasetChanged();
    }

    public void notifyDatasetChanged() {
        if (this.selectedItemPosition > this.adapter.getItemCount() - 1 || this.currentItemPosition > this.adapter.getItemCount() - 1) {
            int itemCount = this.adapter.getItemCount() - 1;
            this.currentItemPosition = itemCount;
            this.selectedItemPosition = itemCount;
        } else {
            this.selectedItemPosition = this.currentItemPosition;
        }
        this.scrollOffsetY = 0;
        computeTextSize();
        computeFlingLimitY();
        requestLayout();
        postInvalidate();
    }

    public void setSameWidth(boolean z) {
        this.hasSameWidth = z;
        computeTextSize();
        requestLayout();
        invalidate();
    }

    public boolean hasSameWidth() {
        return this.hasSameWidth;
    }

    public void setOnWheelChangeListener(OnWheelChangeListener onWheelChangeListener) {
        this.onWheelChangeListener = onWheelChangeListener;
    }

    public String getMaximumWidthText() {
        return this.maxWidthText;
    }

    public void setMaximumWidthText(String str) {
        Objects.requireNonNull(str, "Maximum width text can not be null!");
        this.maxWidthText = str;
        computeTextSize();
        requestLayout();
        postInvalidate();
    }

    public int getMaximumWidthTextPosition() {
        return this.textMaxWidthPosition;
    }

    public void setMaximumWidthTextPosition(int i) {
        if (!isPosInRang(i)) {
            throw new ArrayIndexOutOfBoundsException("Maximum width text Position must in [0, " + this.adapter.getItemCount() + "), but current is " + i);
        }
        this.textMaxWidthPosition = i;
        computeTextSize();
        requestLayout();
        postInvalidate();
    }

    public int getSelectedItemTextColor() {
        return this.mSelectedItemTextColor;
    }

    public void setSelectedItemTextColor(int i) {
        this.mSelectedItemTextColor = i;
        computeCurrentItemRect();
        postInvalidate();
    }

    public int getItemTextColor() {
        return this.mItemTextColor;
    }

    public void setItemTextColor(int i) {
        this.mItemTextColor = i;
        postInvalidate();
    }

    public int getItemTextSize() {
        return this.mItemTextSize;
    }

    public void setItemTextSize(int i) {
        if (this.mItemTextSize != i) {
            this.mItemTextSize = i;
            this.paint.setTextSize(i);
            computeTextSize();
            requestLayout();
            postInvalidate();
        }
    }

    public int getItemSpace() {
        return this.mItemSpace;
    }

    public void setItemSpace(int i) {
        this.mItemSpace = i;
        requestLayout();
        postInvalidate();
    }

    public void setIndicator(boolean z) {
        this.hasIndicator = z;
        computeIndicatorRect();
        postInvalidate();
    }

    public boolean hasIndicator() {
        return this.hasIndicator;
    }

    public int getIndicatorSize() {
        return this.mIndicatorSize;
    }

    public void setIndicatorSize(int i) {
        this.mIndicatorSize = i;
        computeIndicatorRect();
        postInvalidate();
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorColor = i;
        postInvalidate();
    }

    public void setCurtain(boolean z) {
        this.hasCurtain = z;
        computeCurrentItemRect();
        postInvalidate();
    }

    public boolean hasCurtain() {
        return this.hasCurtain;
    }

    public int getCurtainColor() {
        return this.mCurtainColor;
    }

    public void setCurtainColor(int i) {
        this.mCurtainColor = i;
        postInvalidate();
    }

    public void setAtmospheric(boolean z) {
        this.hasAtmospheric = z;
        postInvalidate();
    }

    public boolean hasAtmospheric() {
        return this.hasAtmospheric;
    }

    public boolean isCurved() {
        return this.isCurved;
    }

    public void setCurved(boolean z) {
        this.isCurved = z;
        requestLayout();
        postInvalidate();
    }

    public int getItemAlign() {
        return this.mItemAlign;
    }

    public void setItemAlign(int i) {
        this.mItemAlign = i;
        updateItemTextAlign();
        computeDrawnCenter();
        postInvalidate();
    }

    public Typeface getTypeface() {
        Paint paint = this.paint;
        if (paint != null) {
            return paint.getTypeface();
        }
        return null;
    }

    public void setTypeface(Typeface typeface) {
        Paint paint = this.paint;
        if (paint != null) {
            paint.setTypeface(typeface);
        }
        computeTextSize();
        requestLayout();
        postInvalidate();
    }

    public int findIndexOfDate(Date date) {
        int i;
        String formattedValue = getFormattedValue(date);
        if (this instanceof WheelDayOfMonthPicker) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(5) - 1;
        } else if ((this instanceof WheelDayPicker) && getFormattedValue(new Date()).equals(formattedValue)) {
            return getTodayItemPosition();
        } else {
            if (this instanceof WheelMonthPicker) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date);
                return calendar2.get(2);
            } else if (this instanceof WheelYearPicker) {
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(date);
                return calendar3.get(1) - ((WheelYearPicker) this).minYear;
            } else {
                try {
                    i = Integer.parseInt(formattedValue);
                } catch (NumberFormatException unused) {
                    i = Integer.MIN_VALUE;
                }
                int itemCount = this.adapter.getItemCount();
                int i2 = 0;
                for (int i3 = 0; i3 < itemCount; i3++) {
                    String itemText = this.adapter.getItemText(i3);
                    if (i != Integer.MIN_VALUE) {
                        int parseInt = Integer.parseInt(itemText);
                        if ((this instanceof WheelHourPicker) && ((WheelHourPicker) this).isAmPm) {
                            parseInt %= 12;
                        }
                        if (parseInt <= i) {
                            i2 = i3;
                        }
                    } else if (formattedValue.equals(itemText)) {
                        return i3;
                    }
                }
                return i2;
            }
        }
    }

    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= 24) {
            return getResources().getConfiguration().getLocales().get(0);
        }
        return getResources().getConfiguration().locale;
    }

    /* loaded from: classes.dex */
    public static class Adapter<V> implements BaseAdapter {
        private List<V> data;

        public Adapter() {
            this(new ArrayList());
        }

        public Adapter(List<V> list) {
            ArrayList arrayList = new ArrayList();
            this.data = arrayList;
            arrayList.addAll(list);
        }

        @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker.BaseAdapter
        public int getItemCount() {
            return this.data.size();
        }

        @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker.BaseAdapter
        public V getItem(int i) {
            int itemCount = getItemCount();
            if (itemCount == 0) {
                return null;
            }
            return this.data.get((i + itemCount) % itemCount);
        }

        @Override // com.github.florent37.singledateandtimepicker.widget.WheelPicker.BaseAdapter
        public String getItemText(int i) {
            return String.valueOf(this.data.get(i));
        }

        public List<V> getData() {
            return this.data;
        }

        public void setData(List<V> list) {
            this.data.clear();
            this.data.addAll(list);
        }

        public void addData(List<V> list) {
            this.data.addAll(list);
        }

        public int getItemPosition(V v) {
            List<V> list = this.data;
            if (list != null) {
                return list.indexOf(v);
            }
            return -1;
        }
    }
}
