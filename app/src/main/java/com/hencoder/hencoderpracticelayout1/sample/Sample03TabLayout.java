package com.hencoder.hencoderpracticelayout1.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paihaozhan
 * 自定义 tablayout
 */
public class Sample03TabLayout extends ViewGroup {
  //子view的位置集合
  private List<Rect> childerenBounds = new ArrayList<>();

  public Sample03TabLayout(Context context) {
    super(context);
  }

  public Sample03TabLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public Sample03TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public Sample03TabLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @SuppressLint("DrawAllocation") @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //已经使用的宽、高
    int useWidth = 0;
    int useHeight = 0;
    //累积的宽,高
    int accumulateWidth = 0;
    int accumulateHeight = 0;
    //求这行的最大高度
    int lineMaxHeight = 0;
    int specMode = MeasureSpec.getMode(widthMeasureSpec);
    int specSize = MeasureSpec.getSize(widthMeasureSpec);
    //获取子view的集合，并循环遍历出一个子view
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, useHeight);
      //如果累积的宽+子view的宽大于父view的宽，那就换行，并累积高度
      if (specMode != MeasureSpec.UNSPECIFIED
          && accumulateWidth + child.getMeasuredWidth() > specSize) {
        //累积的宽度重置为0
        accumulateWidth = 0;
        //累积高度
        accumulateHeight += lineMaxHeight;
        //最大高度重置为0
        lineMaxHeight = 0;
        //重新计算子view的宽高
        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, useHeight);
      }
      //子view的位置信息
      Rect childBound;
      if (childerenBounds.size() <= i) {
        childBound = new Rect();
        childerenBounds.add(childBound);
      } else {
        childBound = childerenBounds.get(i);
      }
      //获取子view的位置信息
      childBound.set(accumulateWidth,
          accumulateHeight,
          accumulateWidth + child.getMeasuredWidth(),
          accumulateHeight + child.getMeasuredHeight());
      //累积的宽度
      accumulateWidth += child.getMeasuredWidth();
      //求最大值
      useWidth = Math.max(useWidth, accumulateWidth);
      //将每一个子view的高度进行对比，求出最大值
      lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
    }
    int width = useWidth;
    int height = accumulateHeight + lineMaxHeight;
    setMeasuredDimension(width, height);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      Rect childBound = childerenBounds.get(i);
      child.layout(childBound.left, childBound.top, childBound.right, childBound.bottom);
    }
  }

  @Override public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new MarginLayoutParams(getContext(), attrs);
  }
}
