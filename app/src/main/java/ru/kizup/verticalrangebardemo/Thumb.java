package ru.kizup.verticalrangebardemo;

import android.graphics.Rect;
import android.util.Log;

/**
 * Created by: dpuzikov on 15.03.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class Thumb {

    private int centerX;
    private int centerY;
    private Rect rect;
    private int value;
    private int size;
    private VerticalRangeBar view;
    private boolean isStartThumb;

    public Thumb(int centerX, int centerY, int size, VerticalRangeBar verticalRangeBar, boolean isStartThumb) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;
        this.view = verticalRangeBar;
        rect = new Rect();
        this.isStartThumb = isStartThumb;
        setRect();
    }

    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    private void setRect() {
        int halfSize = size / 2;
        rect.set(centerX - halfSize, centerY - halfSize, centerX + halfSize, centerY + halfSize);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        setRect();
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        int padding = isStartThumb ? view.getPaddingTop() : view.getPaddingBottom();
        float value = ((float) (centerY - padding) / view.getStepPx());
        Log.d("VerticalRangeBar", "Value -> " + value);
        this.value = Math.round(value);
        setRect();
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getValue() {
        return value;
    }

    void decrease() {
        setValue(value - 1);
    }

    void increase() {
        setValue(value + 1);
    }

    public void setValue(int value) {
        this.value = value;
        int offset = isStartThumb ? view.getPaddingTop() : view.getPaddingBottom();
        int y = (int) (view.getStepPx() * value) + offset;
//        Log.d("VerticalRangeBar", "centerY -> " + y);
        setCenterY(y);
    }
}
