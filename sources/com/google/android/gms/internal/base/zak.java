package com.google.android.gms.internal.base;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes.dex */
public final class zak extends Drawable implements Drawable.Callback {
    private int zaa;
    private long zab;
    private int zac;
    private int zad;
    private int zae;
    private int zaf;
    private boolean zag;
    private boolean zah;
    private zaj zai;
    private Drawable zaj;
    private Drawable zak;
    private boolean zal;
    private boolean zam;
    private boolean zan;
    private int zao;

    public zak(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? zai.zaa : drawable;
        this.zaj = drawable;
        drawable.setCallback(this);
        zaj zajVar = this.zai;
        zajVar.zab = drawable.getChangingConfigurations() | zajVar.zab;
        drawable2 = drawable2 == null ? zai.zaa : drawable2;
        this.zak = drawable2;
        drawable2.setCallback(this);
        zaj zajVar2 = this.zai;
        zajVar2.zab = drawable2.getChangingConfigurations() | zajVar2.zab;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        zaj zajVar = this.zai;
        return changingConfigurations | zajVar.zaa | zajVar.zab;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (zac()) {
            this.zai.zaa = getChangingConfigurations();
            return this.zai;
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return Math.max(this.zaj.getIntrinsicHeight(), this.zak.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.max(this.zaj.getIntrinsicWidth(), this.zak.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        if (!this.zan) {
            this.zao = Drawable.resolveOpacity(this.zaj.getOpacity(), this.zak.getOpacity());
            this.zan = true;
        }
        return this.zao;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.zah && super.mutate() == this) {
            if (!zac()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zaj.mutate();
            this.zak.mutate();
            this.zah = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        this.zaj.setBounds(rect);
        this.zak.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.zaf == this.zad) {
            this.zaf = i;
        }
        this.zad = i;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.zaj.setColorFilter(colorFilter);
        this.zak.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zaa() {
        return this.zak;
    }

    public final void zab(int i) {
        this.zac = this.zad;
        this.zaf = 0;
        this.zae = 250;
        this.zaa = 1;
        invalidateSelf();
    }

    public final boolean zac() {
        if (!this.zal) {
            boolean z = false;
            if (this.zaj.getConstantState() != null && this.zak.getConstantState() != null) {
                z = true;
            }
            this.zam = z;
            this.zal = true;
        }
        return this.zam;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
        if (r0 == 0) goto L22;
     */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(android.graphics.Canvas r7) {
        int r0 = r6.zaa;
        int r2 = 0;
        if (r0 != 1) {
            if (r0 == 2 && r6.zab >= 0) {
                float r0 = ((float) (android.os.SystemClock.uptimeMillis() - r6.zab)) / r6.zae;
                r3 = r0 >= 1.0f;
                if (r3) {
                    r6.zaa = 0;
                }
                r6.zaf = (int) ((r6.zac * java.lang.Math.min(r0, 1.0f)) + 0.0f);
            }
        } else {
            r6.zab = android.os.SystemClock.uptimeMillis();
            r6.zaa = 2;
            r3 = false;
        }
        int r0 = r6.zaf;
        boolean r1 = r6.zag;
        android.graphics.drawable.Drawable r4 = r6.zaj;
        android.graphics.drawable.Drawable r5 = r6.zak;
        if (r3) {
            if (!r1) {
                r2 = r0;
            }
            r4.draw(r7);
            r0 = r2;
            int r1 = r6.zad;
            if (r0 == r1) {
                r5.setAlpha(r1);
                r5.draw(r7);
                return;
            }
            return;
        }
        if (r1) {
            r4.setAlpha(r6.zad - r0);
        }
        r4.draw(r7);
        if (r1) {
            r4.setAlpha(r6.zad);
        }
        if (r0 > 0) {
            r5.setAlpha(r0);
            r5.draw(r7);
            r5.setAlpha(r6.zad);
        }
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zak(zaj zajVar) {
        this.zaa = 0;
        this.zad = 255;
        this.zaf = 0;
        this.zag = true;
        this.zai = new zaj(zajVar);
    }
}
