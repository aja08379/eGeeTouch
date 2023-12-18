package cn.pedant.SweetAlert;

import android.content.Context;
import com.pnikosis.materialishprogress.ProgressWheel;
/* loaded from: classes.dex */
public class ProgressHelper {
    private int mBarColor;
    private int mBarWidth;
    private int mCircleRadius;
    private ProgressWheel mProgressWheel;
    private boolean mToSpin = true;
    private float mSpinSpeed = 0.75f;
    private int mRimWidth = 0;
    private int mRimColor = 0;
    private boolean mIsInstantProgress = false;
    private float mProgressVal = -1.0f;

    public ProgressHelper(Context context) {
        this.mBarWidth = context.getResources().getDimensionPixelSize(R.dimen.common_circle_width) + 1;
        this.mBarColor = context.getResources().getColor(R.color.success_stroke_color);
        this.mCircleRadius = context.getResources().getDimensionPixelOffset(R.dimen.progress_circle_radius);
    }

    public ProgressWheel getProgressWheel() {
        return this.mProgressWheel;
    }

    public void setProgressWheel(ProgressWheel progressWheel) {
        this.mProgressWheel = progressWheel;
        updatePropsIfNeed();
    }

    private void updatePropsIfNeed() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            if (!this.mToSpin && progressWheel.isSpinning()) {
                this.mProgressWheel.stopSpinning();
            } else if (this.mToSpin && !this.mProgressWheel.isSpinning()) {
                this.mProgressWheel.spin();
            }
            if (this.mSpinSpeed != this.mProgressWheel.getSpinSpeed()) {
                this.mProgressWheel.setSpinSpeed(this.mSpinSpeed);
            }
            if (this.mBarWidth != this.mProgressWheel.getBarWidth()) {
                this.mProgressWheel.setBarWidth(this.mBarWidth);
            }
            if (this.mBarColor != this.mProgressWheel.getBarColor()) {
                this.mProgressWheel.setBarColor(this.mBarColor);
            }
            if (this.mRimWidth != this.mProgressWheel.getRimWidth()) {
                this.mProgressWheel.setRimWidth(this.mRimWidth);
            }
            if (this.mRimColor != this.mProgressWheel.getRimColor()) {
                this.mProgressWheel.setRimColor(this.mRimColor);
            }
            if (this.mProgressVal != this.mProgressWheel.getProgress()) {
                if (this.mIsInstantProgress) {
                    this.mProgressWheel.setInstantProgress(this.mProgressVal);
                } else {
                    this.mProgressWheel.setProgress(this.mProgressVal);
                }
            }
            if (this.mCircleRadius != this.mProgressWheel.getCircleRadius()) {
                this.mProgressWheel.setCircleRadius(this.mCircleRadius);
            }
        }
    }

    public void resetCount() {
        ProgressWheel progressWheel = this.mProgressWheel;
        if (progressWheel != null) {
            progressWheel.resetCount();
        }
    }

    public boolean isSpinning() {
        return this.mToSpin;
    }

    public void spin() {
        this.mToSpin = true;
        updatePropsIfNeed();
    }

    public void stopSpinning() {
        this.mToSpin = false;
        updatePropsIfNeed();
    }

    public float getProgress() {
        return this.mProgressVal;
    }

    public void setProgress(float f) {
        this.mIsInstantProgress = false;
        this.mProgressVal = f;
        updatePropsIfNeed();
    }

    public void setInstantProgress(float f) {
        this.mProgressVal = f;
        this.mIsInstantProgress = true;
        updatePropsIfNeed();
    }

    public int getCircleRadius() {
        return this.mCircleRadius;
    }

    public void setCircleRadius(int i) {
        this.mCircleRadius = i;
        updatePropsIfNeed();
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setBarWidth(int i) {
        this.mBarWidth = i;
        updatePropsIfNeed();
    }

    public int getBarColor() {
        return this.mBarColor;
    }

    public void setBarColor(int i) {
        this.mBarColor = i;
        updatePropsIfNeed();
    }

    public int getRimWidth() {
        return this.mRimWidth;
    }

    public void setRimWidth(int i) {
        this.mRimWidth = i;
        updatePropsIfNeed();
    }

    public int getRimColor() {
        return this.mRimColor;
    }

    public void setRimColor(int i) {
        this.mRimColor = i;
        updatePropsIfNeed();
    }

    public float getSpinSpeed() {
        return this.mSpinSpeed;
    }

    public void setSpinSpeed(float f) {
        this.mSpinSpeed = f;
        updatePropsIfNeed();
    }
}
