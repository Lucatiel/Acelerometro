package com.example.arthur.exemploacelerometro;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    private TextView textViewX;
    private TextView textViewY;
    private TextView textViewZ;
    private TextView textViewDetail;

    private SensorManager mSensorMnager;
    private Sensor mAccelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewX = (TextView) findViewById(R.id.text_view_x);
        textViewY = (TextView) findViewById(R.id.text_view_y);
        textViewZ = (TextView) findViewById(R.id.text_view_z);
        textViewDetail = (TextView) findViewById(R.id.text_view_detail);

        mSensorMnager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorMnager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume(){
        super.onResume();
        mSensorMnager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        mSensorMnager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Float x = event.values[0];
        Float y = event.values[1];
        Float z = event.values[2];

        textViewX.setText("Posição X: " + x.intValue() + "Fçpat: " + x);
        textViewY.setText("Posição Y: " + y.intValue() + "Fçpat: " + y);
        textViewZ.setText("Posição Z: " + z.intValue() + "Fçpat: " + z);

        if(y < 0) { // O dispositivo esta de cabeça pra baixo
            if(x > 0)
                textViewDetail.setText("Virando para ESQUERDA ficando INVERTIDO");
            if(x < 0)
                textViewDetail.setText("Virando para DIREITA ficando INVERTIDO");
        } else {
            if(x > 0)
                textViewDetail.setText("Virando para ESQUERDA ");
            if(x < 0)
                textViewDetail.setText("Virando para DIREITA ");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
