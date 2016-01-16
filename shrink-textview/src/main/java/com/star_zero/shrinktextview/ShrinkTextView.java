package com.star_zero.shrinktextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Shrink display the text size to fit the view width.
 */
public class ShrinkTextView extends AppCompatTextView {

    private int minSize;

    public ShrinkTextView(Context context) {
        this(context, null);
    }

    public ShrinkTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public ShrinkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ShrinkTextView, defStyleAttr, R.style.Widget_ShrinkTextView);

        try {
            minSize = a.getDimensionPixelSize(R.styleable.ShrinkTextView_shrink_min_size, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        shrinkTextSize();
    }

    /**
     * Shrink text size.
     */
    protected void shrinkTextSize() {

        Paint paint = new Paint();

        int viewWidth = this.getWidth();
        float textSize = getTextSize();

        if (minSize >= textSize) {
            return;
        }

        paint.setTextSize(textSize);
        float textWidth = paint.measureText(this.getText().toString());

        while (viewWidth <  textWidth) {

            if (minSize >= textSize) {
                textSize = minSize;
                break;
            }

            textSize--;

            paint.setTextSize(textSize);
            textWidth = paint.measureText(this.getText().toString());
        }

        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
}
