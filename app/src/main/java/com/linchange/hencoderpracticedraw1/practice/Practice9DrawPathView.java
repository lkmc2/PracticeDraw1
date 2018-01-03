package com.linchange.hencoderpracticedraw1.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint mPaint;
    private Path mPath;

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
        mPath = new Path();
        mPath.addArc(450, 300, 750, 600, 120, 220);
        mPath.arcTo(730, 300, 1030, 600, 200, 220, false);
        mPath.lineTo(750, 750);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        canvas.drawPath(mPath, mPaint);
        canvas.drawText("爱丽敏", 590, 900, mPaint);
    }
}
