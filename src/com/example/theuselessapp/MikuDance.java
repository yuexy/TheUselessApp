package com.example.theuselessapp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;

public class MikuDance extends Activity
{
	int[] imgs = new int[]
	{
		R.drawable.mikudance_up,
		R.drawable.mikudance_down
	};
	MediaPlayer playMusic;
	int nums = 0;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mikudance_lay);
		playMusic = MediaPlayer.create(this, R.raw.mikudance_music);
		playMusic.setLooping(true);
		playMusic.start();
		
		final ImageView show = (ImageView)findViewById(R.id.mikudance_imgv);
		final Handler mHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if(msg.what == 0x123)
				{
					show.setImageResource(imgs[ nums++ % imgs.length]);
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
			
		}, 0 ,350);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			playMusic.stop();
			finish();
		}
		return false;
	}
}
