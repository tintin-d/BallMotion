package com.example.valentin.ballmotion;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by valentin on 09/10/17.
 */

public class MyView extends View {

    public boolean played=false;
    public MediaPlayer mediaPlayer;
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
        int maxH=canvas.getHeight();
        int maxW=canvas.getWidth();
        if(x>=maxW-20){
            x=maxW-20;
            if(!played){
                play();
            }
        }
        if(x<=20){
            x=20;
            if(!played){
                play();
            }
        }
        if(y>=maxH-20){
            y=maxH-20;
            if(!played){
                play();
            }
        }
        if(y<=20){
            y=20;
            if(!played){
                play();
            }
        }
        paint= new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,20,paint);
    }

    public void stop(View v){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer=null;
            played=false;
        }
    }

    public void play(){
        stop(null);
        played=true;
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.ball);
        mediaPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stop(null);
                    }
                }
        );
        mediaPlayer.start();
    }

}
