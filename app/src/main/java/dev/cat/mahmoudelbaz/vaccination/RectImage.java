package dev.cat.mahmoudelbaz.vaccination;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by mahmoudelbaz on 1/18/18.
 */

public class RectImage extends android.support.v7.widget.AppCompatImageView {

    public RectImage(Context context) {
        super(context);
    }

    public RectImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RectImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}