package com.example.valentin.ballmotion;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by valentin on 09/10/17.
 */

public class MyView extends View {

    private Paint paint;
    public float x;
    public float y;

    public MyView(Context context) {
        super(context);
        setMyX(50);setMyY(50);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setMyX(50);setMyY(50);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setMyX(50);setMyY(50);
    }


    public float getMyX() {
        return x;
    }

    public float getMyY() {
        return y;
    }

    public void setMyX(float x) {
        this.x = x;
    }

    public void setMyY(float y) {
        this.y = y;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint= new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        int cheight=canvas.getHeight();
        int cwidth=canvas.getWidth();
        canvas.drawCircle(x,y,20,paint);
    }

}
