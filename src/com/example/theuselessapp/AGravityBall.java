package com.example.theuselessapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class AGravityBall extends Activity
{
	int a=3;
	private SensorManager sensorManager;
	private Sensor sensor;
	private float xx, yy, zz;
	RelativeLayout relativeLayout;
	public int screenWidth, screenHeight;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.agravityball_lay);
		relativeLayout = (RelativeLayout)findViewById(R.id.agravityball_rlay);
		
		WindowManager wm = getWindowManager();
		Display display = wm.getDefaultDisplay();
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		move();
		relativeLayout.addView(new TestMotionView(this));
	}
	
	void move()
	{
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); 
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); 
		    
		SensorEventListener lsn = new SensorEventListener() 
		{
			public void onSensorChanged(SensorEvent e) 
			{ 
				xx = e.values[SensorManager.DATA_X]; 
				yy = e.values[SensorManager.DATA_Y]; 
				zz = e.values[SensorManager.DATA_Z]; 
				
	            setTitle("X:" + xx + "," + "Y:" + yy + ","
	                        + "Z:" + zz); 
	         }
	  
	            public void onAccuracyChanged(Sensor s, int accuracy) 
	            { 
	            } 
	    }; 
	    sensorManager .registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME);
	}

public class TestMotionView extends View
{
	float x=screenWidth/2;
	float y=screenHeight/2;
	
	float getx()
	{
		return x;
	}
	
	float gety()
	{
		return y;
	}
	
	public TestMotionView(Context c)
	{
		super(c);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		x=x-xx*a;
		y=y+yy*a;
		
		if(x<=0)x=0;
		if(x>screenWidth)x=screenWidth;
		if(y<=0)y=0;
		if(y>screenHeight)y=screenHeight;
		Paint paint = new Paint();
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(x, y,20, paint);
		invalidate();
	}
}

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			finish();
		}
		return false;
	}
}
