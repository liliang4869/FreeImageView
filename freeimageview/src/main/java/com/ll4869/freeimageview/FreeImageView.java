package com.ll4869.freeimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class FreeImageView extends AppCompatImageView {
    public static final int TOP_OR_LEFT = 0;
    public static final int CENTER = 1;
    public static final int BOTTOM_OR_RIGHT = 2;
    private int parentHeight;
    private int parentWidth;
    private int lastX;
    private int lastY;
    private int horizontalPos = CENTER;//0-left 1-center 2-right
    private int verticalPos = CENTER;//0-top 2-center 3-bottom;
    private int minHeight = 90;//in px
    private int minWidth = 90;//in px
    private int sideWidth = 30;//recognize as border where you can drag to resize; in px;

    public int getMinHeight() {
        return minHeight;
    }


    public int getMinWidth() {
        return minWidth;
    }


    //set min height,min width and sideWidth;
    public void setMinHWS(int width, int height, int sideWidth) {
        // ensure there is enough space for drag&move
        if (sideWidth * 3 > width) width = 3 * sideWidth;
        if (sideWidth * 3 > height) height = 3 * sideWidth;
        this.minWidth = width;
        this.minHeight = height;
        this.sideWidth = sideWidth;
    }

    public int getSideWidth() {
        return sideWidth;
    }

    public FreeImageView(@NonNull Context context) {
        super(context);
    }

    public FreeImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FreeImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX = rawX;
                lastY = rawY;
                ViewGroup parent;
                if (getParent() != null) {
                    parent = (ViewGroup) getParent();
                    parentHeight = parent.getHeight();
                    parentWidth = parent.getWidth();
                }
                horizontalPos = event.getX() < sideWidth ? TOP_OR_LEFT :
                        (layoutParams.width - event.getX()) < sideWidth ? BOTTOM_OR_RIGHT : CENTER;
                verticalPos = event.getY() < sideWidth ? TOP_OR_LEFT :
                        (layoutParams.height - event.getY()) < sideWidth? BOTTOM_OR_RIGHT : CENTER;
                break;
            case MotionEvent.ACTION_MOVE:
                if (parentHeight <= 0 || parentWidth <= 0) {
                    break;
                }
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                int distance = (int) Math.sqrt(dx * dx + dy * dy);
                if (distance == 0) {
                    break;
                }
                float x = getX() + (horizontalPos == BOTTOM_OR_RIGHT ? TOP_OR_LEFT : dx);
                float y = getY() + (verticalPos == BOTTOM_OR_RIGHT ? TOP_OR_LEFT : dy);
                float w = layoutParams.width + (horizontalPos == CENTER ? 0 : horizontalPos == TOP_OR_LEFT ? (-1 * dx) : dx);
                float h = layoutParams.height + (verticalPos == CENTER ? 0 : verticalPos == TOP_OR_LEFT ? (-1 * dy) : dy);
                w = w < minWidth ? minWidth : w > parentWidth ? parentWidth : w;
                h = h < minHeight ? minHeight : h > parentHeight ? parentHeight : h;
                x = x < 0 ? 0 : Math.min(x, parentWidth - w);
                y = y < 0 ? 0 : Math.min(y, parentHeight - h);
                layoutParams.height = (int) h;
                layoutParams.width = (int) w;
                setLayoutParams(layoutParams);
                setX(x);
                setY(y);
                lastX = rawX;
                lastY = rawY;
                break;

        }
        return true;
    }
}
