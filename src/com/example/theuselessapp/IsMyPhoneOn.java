package com.example.theuselessapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;

public class IsMyPhoneOn extends Activity
{
	ImageView show;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ismyphoneon_lay);
		show = (ImageView)findViewById(R.id.ismyphoneonimg);
	}
	
	 public boolean onTouchEvent(MotionEvent event)
	 {
		 show.setImageResource(R.drawable.is_my_phone_on_yes);
		 return true;
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
