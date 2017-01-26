package com.example.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class loginhelpActivity extends Activity{
	Button btn=null;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginhelp);
		application.addActivity(this);
		btn=(Button)this.findViewById(R.id.backtologin);
		btn.setOnClickListener(new press());
		
	}
	private class press implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.backtologin)
			{
				Intent intent=new Intent(loginhelpActivity.this,MainActivity.class);
				loginhelpActivity.this.startActivity(intent);
				
			}
			
		}
		
	}

}
