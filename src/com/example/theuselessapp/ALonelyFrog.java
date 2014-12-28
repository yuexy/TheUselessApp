package com.example.theuselessapp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

public class ALonelyFrog extends Activity
{
	MediaPlayer[] frog_music = new MediaPlayer[20];
	int num = 0;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alonelyfrog_lay);
		for(int i = 0; i < 10; i++)
		{
			frog_music[i] = MediaPlayer.create(this, R.raw.alonelyfrog_music);
			frog_music[i].setLooping(true);
		}
		
		final Handler mHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if(msg.what == 0x123)
				{
					num++;
					if(num >= 10)
					{
						num = 9;
					}
					for(int i = 0;i <= num; i++)
					{
						frog_music[i].start();
					}
					//show.setImageResource(imgs[ nums++ % imgs.length]);
				}
			}
		};
		
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(0x123);
			}
			
		}, 0 ,2500);
		
	}
	

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			for(int i = 0;i < 10;i++)
			 {
				 frog_music[i].stop();
			 }
			finish();
		}
		return false;
	}
}
