package com.prolificinteractive.materialcalendarview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.TypedValue;
import android.widget.ImageView;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class DirectionButton extends ImageView {
    public DirectionButton(Context context) {
        super(context);
        setBackgroundResource(getThemeSelectableBackgroundId(context));
    }

    public void setColor(int i) {
        setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(z ? 1.0f : 0.1f);
    }

    private static int getThemeSelectableBackgroundId(Context context) {
        int identifier = context.getResources().getIdentifier("selectableItemBackgroundBorderless", "attr", context.getPackageName());
        if (identifier == 0) {
            identifier = Build.VERSION.SDK_INT >= 21 ? 16843868 : 16843534;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(identifier, typedValue, true);
        return typedValue.resourceId;
    }
}
