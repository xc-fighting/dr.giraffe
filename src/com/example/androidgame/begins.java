package com.example.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


import android.view.View;

import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;


public class begins extends Activity implements  ViewFactory{
	
	ImageSwitcher V1;
	private static int []pictures={R.drawable.begin1,R.drawable.begin3,R.drawable.begin2};
	
	private Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what==0x444)
			{
				Intent intent=new Intent(begins.this,MainActivity.class);
				begins.this.startActivity(intent);
				
			}
			if(msg.what==0x441)
			{
				//V1.setImageResource(pictures[0]);
				V1.setBackgroundResource(pictures[0]);
			}
			if(msg.what==0x442)
			{
				//V1.setImageResource(pictures[1]);
				V1.setBackgroundResource(pictures[1]);
			}
			if(msg.what==0x443)
			{
				//V1.setImageResource(pictures[2]);
				V1.setBackgroundResource(pictures[2]);
			}
		}
	};
	
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	application.addActivity(this);
    	
    	setContentView(R.layout.begin);
    	V1=(ImageSwitcher)this.findViewById(R.id.imageSwitcher1);
    	V1.setFactory(begins.this);
    	
		new drawthread().start();
				
				
				
			
			
			
		
			
    
    	
    	
    }
	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return new ImageView(this);
	}
	
  private class drawthread extends Thread
  {
	 
	  public void run()
	  {
		  try {
			
			  Message msg1=new Message();
			msg1.what=0x441;
			handler.sendMessage(msg1);
			sleep(2000);
			  Message msg2=new Message();
			msg2.what=0x442;
			handler.sendMessage(msg2);
			sleep(2000);
			  Message msg3=new Message();
			msg3.what=0x443;
			handler.sendMessage(msg3);
			sleep(2000);
			
			Message msg=new Message();
			msg.what=0x444;
			handler.sendMessage(msg);
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
  }

	
}
