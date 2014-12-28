package com.example.theuselessapp;

import com.example.theuselessapp.AGravityBall.TestMotionView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class APigSnout extends Activity
{
	RelativeLayout apigsnout_rlay;
	MediaPlayer pig_music;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.apigsnout_lay);
		pig_music = MediaPlayer.create(this, R.raw.apigsnout_music);
		pig_music.setLooping(false);
		apigsnout_rlay = (RelativeLayout)findViewById(R.id.apigsnout_rlay);
		apigsnout_rlay.addView(new TestMotionView(this));
		
	}
	
	public class TestMotionView extends View
	{
		private int act;
		private float X = 1000000;
		private float Y = 1000000;
		Bitmap pig;
		
		public TestMotionView(Context c)
		{
			super(c);
			pig = BitmapFactory.decodeResource(getResources(), R.drawable.apigsnout_img);
			setFocusable(true);
		}
		
		@Override
		protected void onDraw(Canvas canvas)
		{
			super.onDraw(canvas);
			Paint p = new Paint();
			canvas.drawBitmap(pig, 100000,100000, p);
			//canvas.drawBitmap(pig, 0,0, p);
			if(MotionEvent.ACTION_MOVE == act || MotionEvent.ACTION_DOWN == act ) 
			{ 
				canvas.drawBitmap(pig, X - (pig.getHeight())/2 ,Y - (pig.getWidth())/2, p);
				//paint.setColor(Color.RED);
			}
			else
			{
				canvas.drawBitmap(pig, 100000,100000, p);
				//pig_music.stop();
			}
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event)
		{
			pig_music.start();
			act = event.getAction();
			X = event.getX();
			Y = event.getY();
			invalidate();
			return true;
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			pig_music.stop();
			finish();
		}
		return false;
	}
}
