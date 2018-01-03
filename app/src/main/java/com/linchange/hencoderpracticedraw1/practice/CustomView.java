package com.linchange.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {

    private Paint mPaint; //画笔
    private Path mPath; //路径
    private RectF rectF1; //矩阵
    private RectF rectF2;
    private RectF rectF3;
    private RectF rectF4;
    private RectF rectF5;
    private RectF rectF6;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(); //初始化
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(); //初始化
    }

    /**
     * 初始化
     */
    private void init() {
        setBackgroundColor(Color.parseColor("#506E7A"));

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化画笔
        mPaint.setColor(Color.WHITE); //设置画笔颜色为白色
        mPaint.setStrokeWidth(10); //设置画笔宽
        mPaint.setStyle(Paint.Style.STROKE); //设置画笔填充样式为空心

        mPath = new Path(); //初始化路径
        mPath.moveTo(100, 100); //路径移动
        mPath.lineTo(100, 1110); //绘制直线路径
        mPath.lineTo(1300, 1110);

        float left = 110, right = 260, bottom = 1100; //矩阵的初始左、右、下坐标

        rectF1 = new RectF(left, 1050, right, bottom); //初始化矩阵
        rectF2 = new RectF(left += 200, 950, right += 200, bottom);
        rectF3 = new RectF(left += 200, 950, right += 200, bottom);
        rectF4 = new RectF(left += 200, 850, right += 200, bottom);
        rectF5 = new RectF(left += 200, 600, right += 200, bottom);
        rectF6 = new RectF(left += 200, 700, right += 200, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mPath, mPaint); //绘制路径

        mPaint.setColor(Color.GREEN); //设置画笔颜色为绿色
        mPaint.setStyle(Paint.Style.FILL); //设置画笔样式为全填充
        canvas.drawRect(rectF1, mPaint); //绘制矩阵
        canvas.drawRect(rectF2, mPaint);
        canvas.drawRect(rectF3, mPaint);
        canvas.drawRect(rectF4, mPaint);
        canvas.drawRect(rectF5, mPaint);
        canvas.drawRect(rectF6, mPaint);


        mPaint.setColor(Color.WHITE); //设置画笔颜色为白色
        mPaint.setTextSize(60); //设置画笔字体大小
        float x = 150f, y = 1180; //文字起始值坐标

        String[] strings = {"Jsl","Bat","Gcc","Adc","Gif","TTS"}; //文字数组

        for (int i = 0, len = strings.length; i < len; i++, x += 200) {
            canvas.drawText(strings[i], x, y, mPaint); //绘制文字
        }
    }
}
