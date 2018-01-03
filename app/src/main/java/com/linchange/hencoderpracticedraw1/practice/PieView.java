package com.linchange.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PieView extends View {

    private static final float CENTER_X = 700; //中心点横坐标
    private static final float CENTER_Y = 700; //中心点纵坐标
    private static final  float RADIUS = 400; //圆半径

    private Paint piePaint; //饼图画笔
    private Paint linePaint; //线条画笔
    private Paint textPaint; //文字画笔

    private List<PieBean> pieBeanList; //饼图列表

    private RectF mRectF; //饼图矩阵

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(); //初始化
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(); //初始化
    }

    /**
     * 初始化
     */
    private void init() {
        setBackgroundColor(Color.parseColor("#506E7A")); //设置屏幕背景色

        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化饼图画笔

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化线条画笔
        linePaint.setColor(Color.WHITE); //设置字体为白色
        linePaint.setStrokeWidth(10); //设置画笔宽度

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //初始化文字画笔
        textPaint.setColor(Color.WHITE); //设置画笔颜色
        textPaint.setTextSize(50); //设置画笔字体大小

        //初始化矩阵
        mRectF = new RectF(CENTER_X - RADIUS, CENTER_Y - RADIUS, CENTER_X + RADIUS, CENTER_Y + RADIUS);

        pieBeanList = new ArrayList<PieBean>(); //初始化饼图列表
        //为饼图列表填充数据
        pieBeanList.add(new PieBean("Marshmallow", 60, Color.YELLOW));
        pieBeanList.add(new PieBean("Froyo", 10, Color.LTGRAY));
        pieBeanList.add(new PieBean("Gingerbread", 10, Color.GREEN));
        pieBeanList.add(new PieBean("Ice Cream", 10, Color.BLUE));
        pieBeanList.add(new PieBean("Jelly Bean", 50, Color.CYAN));
        pieBeanList.add(new PieBean("KitKat", 100, Color.DKGRAY));
        pieBeanList.add(new PieBean("Lollipop", 120, Color.RED, true));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //当前开始角度
        float currentStartAngle = -60;
        for (PieBean pieBean : pieBeanList) { //遍历饼图列表
            piePaint.setColor(pieBean.getColor()); //设置饼图画笔颜色
            float sweepAngle = pieBean.getRotateAngle(); //获取旋转角度

            if (pieBean.getIsDivide()) { //饼图被分隔
                mRectF.offset(-20, -40); //圆形矩阵偏移
            }

            //绘制扇形
            canvas.drawArc(mRectF, currentStartAngle, sweepAngle, true, piePaint);

            float lineAngle = currentStartAngle + sweepAngle / 2; //获取线条的角度
            currentStartAngle += sweepAngle; //设置已旋转过的角度

            //分别求线条的横坐标起点、纵坐标起点、横坐标终点、纵坐标终点
            float lineStartX = CENTER_X + RADIUS * (float) Math.cos(lineAngle / 180 * Math.PI);
            float lineStartY = CENTER_Y + RADIUS * (float) Math.sin(lineAngle / 180 * Math.PI);
            float lineEndX = CENTER_X + (RADIUS + 50) * (float) Math.cos(lineAngle / 180 * Math.PI);
            float lineEndY = CENTER_Y + (RADIUS + 50) * (float) Math.sin(lineAngle / 180 * Math.PI);

            if (pieBean.getIsDivide()) { //饼图被分隔，线条位置偏移
                lineStartX -= 20;
                lineEndX -= 20;
                lineStartY -= 40;
                lineEndY -= 40;
            }
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, linePaint); //绘制线条

            float textX = lineEndX; //设置文字横坐标
            float textY = lineEndY; //设置文字纵坐标

            //针对角度对文字的横纵坐标变换
            if (currentStartAngle > 300 || currentStartAngle < 90) {
                textX += 20;
                textY += 20;
            } else if (currentStartAngle > 90 && currentStartAngle <= 180) {
                textX -= 60;
                textY += 60;
            } else {
                textX -= 60;
                textY -= 30;
            }
            canvas.drawText(pieBean.getTitle(), textX, textY, textPaint); //绘制文字

            if (pieBean.getIsDivide()) { //饼图被分隔
                mRectF.offset(20, 40); //圆形矩阵移动回原位
            }
        }
    }

    //饼图类
    private static class PieBean {
        private String title; //标题
        private float rotateAngle; //旋转角度
        @ColorInt private int color; //饼图颜色
        private boolean isDivide; //是否与中心分隔

        public PieBean(String title, float rotateAngle, int color) {
            this(title, rotateAngle, color, false);
        }

        public PieBean(String title, float rotateAngle, int color, boolean isDivide) {
            this.title = title;
            this.rotateAngle = rotateAngle;
            this.color = color;
            this.isDivide = isDivide;
        }

        public String getTitle() { //获取标题
            return title;
        }

        public float getRotateAngle() { //获取旋转角度
            return rotateAngle;
        }

        public int getColor() { //获取颜色
            return color;
        }

        public boolean getIsDivide() { //获取是否被分隔
            return isDivide;
        }
    }
}
