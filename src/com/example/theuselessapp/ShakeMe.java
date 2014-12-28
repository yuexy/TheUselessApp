package com.example.theuselessapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

public class ShakeMe extends Activity implements SensorListener 
{   
    private float x, y, z,last_x,last_y,last_z;
    private long lastUpdate;
    MediaPlayer shake_music;
    protected void onCreate(Bundle savedInstanceState) 
    {   
        super.onCreate(savedInstanceState); 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.shakeme_lay);
        shake_music = MediaPlayer.create(this, R.raw.shakeme_music);
        shake_music.setLooping(false);
        SensorManager mysensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE); 
        Sensor sensor = mysensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mysensorMgr.registerListener(this,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_GAME); 
    }
    @Override
    public void onAccuracyChanged(int sensor, int accuracy) 
    {
       // TODO Auto-generated method stub
    }
    private static final int SHAKE_THRESHOLD = 5000;//���ƾ��ȣ�ԽС��ʾ��ӦԽ����
    @Override
    public void onSensorChanged(int sensor, float[] values) 
    {
       // TODO Auto-generated method stub
       if (sensor == SensorManager.SENSOR_ACCELEROMETER) 
       {   
          long curTime = System.currentTimeMillis();   
          // ÿ100������һ�� 
          if ((curTime - lastUpdate) > 100) 
          { 
             long diffTime = (curTime - lastUpdate);  
             lastUpdate = curTime;      
             x = values[SensorManager.DATA_X];   
             y = values[SensorManager.DATA_Y];   
             z = values[SensorManager.DATA_Z];       
             float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;  
             if (speed > SHAKE_THRESHOLD) 
             {   
            	 shake_music.start();
                //��⵽ҡ�κ�ִ�еĴ���
             }
             last_x = x;   
             last_y = y;   
             last_z = z;   
          }   
       }
   }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			shake_music.stop();
			finish();
		}
		return false;
	}
}