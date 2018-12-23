package com.hencoder.hencoderpracticelayout1.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.hencoder.hencoderpracticelayout1.Utils;

public class Sample02CircleView extends View {
  Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  //半径
  private static final int PADIUS = (int) Utils.dpToPixel(80);
  //边距
  private static final int PADDING = (int) Utils.dpToPixel(30);

  public Sample02CircleView(Context context) {
    super(context);
  }

  public Sample02CircleView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Sample02CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int height = (PADIUS + PADDING) * 2;
    int width = (PADIUS + PADDING) * 2;
    height = resolveSize(height, heightMeasureSpec);
    width = resolveSize(width, widthMeasureSpec);
    //int specMode = MeasureSpec.getMode(widthMeasureSpec);
    //int specSize = MeasureSpec.getSize(widthMeasureSpec);
    //switch (specMode) {
    //  case MeasureSpec.EXACTLY:
    //    width = specSize;
    //    break;
    //  case MeasureSpec.AT_MOST:
    //    if (specSize < width) {
    //      width = specSize;
    //    }
    //    break;
    //  case MeasureSpec.UNSPECIFIED:
    //  default:
    //    break;
    //}
    setMeasuredDimension(width, height);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawColor(Color.RED);
    canvas.drawCircle(PADIUS + PADDING, PADDING + PADIUS, PADIUS, mPaint);
  }
}
