package com.tarun.gridapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;

public class CustomGridView extends GridLayout {
    public CustomGridView(Context context) {
        super(context);
        setLayoutTransition(null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayoutTransition(null);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutTransition(null);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutTransition(null);
    }

    @Override
    public int getOrientation() {
        return super.getOrientation();
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }

    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    @Override
    public void setRowCount(int rowCount) {
        super.setRowCount(rowCount);
    }

    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    @Override
    public void setColumnCount(int columnCount) {
        super.setColumnCount(columnCount);
    }

    @Override
    public boolean getUseDefaultMargins() {
        return super.getUseDefaultMargins();
    }

    @Override
    public void setUseDefaultMargins(boolean useDefaultMargins) {
        super.setUseDefaultMargins(useDefaultMargins);
    }

    @Override
    public int getAlignmentMode() {
        return super.getAlignmentMode();
    }

    @Override
    public void setAlignmentMode(int alignmentMode) {
        super.setAlignmentMode(alignmentMode);
    }

    @Override
    public boolean isRowOrderPreserved() {
        return super.isRowOrderPreserved();
    }

    @Override
    public void setRowOrderPreserved(boolean rowOrderPreserved) {
        super.setRowOrderPreserved(rowOrderPreserved);
    }

    @Override
    public boolean isColumnOrderPreserved() {
        return super.isColumnOrderPreserved();
    }

    @Override
    public void setColumnOrderPreserved(boolean columnOrderPreserved) {
        super.setColumnOrderPreserved(columnOrderPreserved);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return super.generateLayoutParams(lp);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }

    @Override
    public void onViewRemoved(View child) {

        super.onViewRemoved(child);
    }

    private void reorder(final int start, final float x, final float y) {

        if (start < getChildCount() - 1) {
            final View next = getChildAt(start + 1);

            int[] loc = new int[2];
            next.getLocationOnScreen(loc);
            final int xC = (int) next.getX();// loc[0];
            final int yC = (int) next.getY(); //loc[1];

            next.animate().x(x).y(y)
                    .setDuration(Constants.animationSpeed)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                                next.setX(x);
                                next.setY(y);
                            reorder(start + 1, xC, yC);
                        }
                    });
        }
    }

    public int getIndex(int tag) {
        for (int i =0; i< getChildCount(); i++) {
            if ( (int)getChildAt(i).getTag() == tag) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeViewAt(final int index) {

        if (index == getChildCount()-1) {

            getChildAt(getChildCount()-1).startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.vertical_flip));
            super.removeViewAt(index);
            return;
        }
        final View v = getChildAt(index);
        final float x = v.getX();// loc[0];
        final float y = v.getY(); //loc[1];
        v.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.vertical_flip));
        v.postOnAnimationDelayed(new Runnable() {
            @Override
            public void run() {
                reorder(index, x, y);
                v.setVisibility(GONE);
            }
        }, 600);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }
}
