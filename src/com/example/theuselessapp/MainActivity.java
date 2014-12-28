package com.example.theuselessapp;

import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import com.example.hitmebyfish.HitMeByFish;

public class MainActivity extends Activity 
{
	int num = 7;

	Button start;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		final SharedPreferences preferences;
		preferences = getSharedPreferences("count" , MODE_WORLD_READABLE);
		start = (Button)findViewById(R.id.main_button);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				int acnum = preferences.getInt("count", 0);
				
				//acnum = 6;
				Intent intent = new Intent();
				if(acnum == 0)
				{
					intent.setClass(MainActivity.this, MikuDance.class);
				}
				else if(acnum == 1)
				{
					intent.setClass(MainActivity.this, ABlueBox.class);
				}
				else if(acnum == 2)
				{
					intent.setClass(MainActivity.this, IsMyPhoneOn.class);
				}
				else if(acnum == 3)
				{
					intent.setClass(MainActivity.this, AGravityBall.class);
				}
				else if(acnum == 4)
				{
					intent.setClass(MainActivity.this, APigSnout.class);
				}
				else if(acnum == 5)
				{
					intent.setClass(MainActivity.this, ALonelyFrog.class);
				}
				else if(acnum == 6)
				{
					intent.setClass(MainActivity.this, ShakeMe.class);
				}
				else if(acnum == 7)
				{
					intent.setClass(MainActivity.this, HitMeByFish.class);
				}

				Editor editor = preferences.edit();
				acnum++;
				if(acnum > num)
				{
					acnum = 0;
				}
				editor.putInt("count", acnum);
				editor.commit();

				startActivity(intent);
			}
		});
	}
}
