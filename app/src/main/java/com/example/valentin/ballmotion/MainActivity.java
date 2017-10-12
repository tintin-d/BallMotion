package com.example.valentin.ballmotion;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button plus;
    Button minus;
    ToggleButton tgButton;
    MyView myView;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private boolean accelSopported;
    public float speedX;
    public float speedY;
    public float coefacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus=(Button)findViewById(R.id.plus);
        minus=(Button)findViewById(R.id.minus);
        tgButton=(ToggleButton)findViewById(R.id.tgButton);
        myView=(MyView)findViewById(R.id.myView);

        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelSopported=mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_GAME);
        speedX=speedY=1;
        coefacc=1;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:

                float ax = sensorEvent.values[0];
                float ay = sensorEvent.values[1];
                float az = sensorEvent.values[2];
                double xAngle = Math.atan( ax / (Math.sqrt((ay*ay) + (az*az))));
                double yAngle = Math.atan( ay / (Math.sqrt((ax*ax) + (az*az))));

                xAngle *= 180.00;   yAngle *= 180.00;
                xAngle /= 3.141592; yAngle /= 3.141592;
                speedX=-(float)(xAngle/10);
                speedY=(float)(yAngle/10);
                if(tgButton.isChecked()){
                  move();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override
    public void onPause(){
        mSensorManager.unregisterListener(this, mAccelerometer);
        myView.stop(null);
        super.onPause();
    }

    public void move(){

        float curX=myView.getMyX();
        float curY=myView.getMyY();
        myView.setMyX(curX+speedX*1*coefacc);
        myView.setMyY(curY+speedY*1*coefacc);
        myView.invalidate();

    }

    public void plusClick(View v){coefacc*=1.25;}
    public void minusClick(View v){coefacc=(float)(coefacc/1.25);}

    @Override
    public void onDestroy(){
        myView.stop(null);
        super.onDestroy();
    }
}
