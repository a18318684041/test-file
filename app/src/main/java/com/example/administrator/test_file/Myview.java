package com.example.administrator.test_file;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class Myview extends View {
    private Paint mpaint;
    private Rect rect;
    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.rect!=null){
            canvas.drawRect(rect,mpaint);
        }
    }
    //从外部传矩形的信息
    public  void drawRect(Rect  rect){
        this.rect = rect;
        //待参数传递完毕的时候开始重新绘制图形
        invalidate();
    }
}
