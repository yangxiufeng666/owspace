package com.github.baby.owspace.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/7/22
 * owspace
 */
public class CustomScrollView extends ScrollView{
    private boolean mAllowScroll = true;
    private ArrayList<OnScrollChangedListener> mOnScrollChangedListeners;
    private OnScrollViewScrollListener mOnScrollListener;

    public CustomScrollView(Context paramContext)
    {
        this(paramContext, null);
    }

    public CustomScrollView(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public CustomScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        initListener();
    }

    private void initListener()
    {
        this.mOnScrollChangedListeners = new ArrayList();
    }

    public void addOnScrollChangedListener(OnScrollChangedListener paramOnScrollChangedListener)
    {
        this.mOnScrollChangedListeners.add(paramOnScrollChangedListener);
    }

    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {

    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (this.mAllowScroll)
            return super.onTouchEvent(paramMotionEvent);
        return true;
    }

    public void removeOnScrollChangedListener(OnScrollChangedListener paramOnScrollChangedListener)
    {
        this.mOnScrollChangedListeners.remove(paramOnScrollChangedListener);
    }

    public void setAllowScroll(boolean paramBoolean)
    {
        this.mAllowScroll = paramBoolean;
    }

    public void setOnScrollViewScrollListener(OnScrollViewScrollListener paramOnScrollViewScrollListener)
    {
        this.mOnScrollListener = paramOnScrollViewScrollListener;
    }

    public static abstract interface OnScrollChangedListener
    {
        public abstract void onScrollChanged(CustomScrollView paramCustomScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    }

    public static abstract interface OnScrollViewScrollListener
    {
        public abstract void onScroll(int paramInt);
    }
}
