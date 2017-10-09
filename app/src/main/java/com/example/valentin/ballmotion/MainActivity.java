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
    ImageView img;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private boolean accelSopported;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus=(Button)findViewById(R.id.plus);
        minus=(Button)findViewById(R.id.minus);
        tgButton=(ToggleButton)findViewById(R.id.tgButton);
        img=(ImageView)findViewById(R.id.imgView);

        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelSopported=mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void drawBall(){
        Bitmap b = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        Paint paint= new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        int cheight=c.getHeight();
        int cwidth=c.getWidth();
        c.drawCircle(cheight/2,cwidth/2,10,paint);
        img.setImageBitmap(b);
    }

    public void move(View v){

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:

                float ax = sensorEvent.values[0];
                float ay = sensorEvent.values[1];
                float az = sensorEvent.values[2];
                double xAngle = Math.atan( ax / (Math.sqrt((ay*ay) + (az*az))));
                double yAngle = Math.atan( ay / (Math.sqrt((ax*az) + (az*az))));
                double zAngle = Math.atan( Math.sqrt(ax*ax+ay*ay) / az);

                xAngle *= 180.00;   yAngle *= 180.00;   zAngle *= 180.00;
                xAngle /= 3.141592; yAngle /= 3.141592; zAngle /= 3.141592;
                Log.d("sensor",""+xAngle+"  "+yAngle+"  "+zAngle);
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
        super.onPause();
    }
}
