package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by mahmoudelbaz on 1/18/18.
 */

public class RectButton extends android.support.v7.widget.AppCompatButton {

    public RectButton(Context context) {
        super(context);
    }

    public RectButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}