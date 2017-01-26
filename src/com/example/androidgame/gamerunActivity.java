package com.example.androidgame;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;


import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;

import android.view.Window;
import android.view.WindowManager;

import android.view.MotionEvent;

import android.widget.Toast;

public class gamerunActivity extends Activity
{
	private GestureDetector myDetector;// 监听手势
	private mysurface mySurface; 
   
	private String send=null;
	
    private dbadapter db=new dbadapter(this);
    
    private DisplayMetrics dm; 
    
    public static int screen_width;
    public static int screen_height;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);		
		
		 application.addActivity(this);
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
		 //以下代码为获取屏幕像素尺寸的代码
		  dm = new DisplayMetrics();   
          getWindowManager().getDefaultDisplay().getMetrics(dm);
          
          screen_width=dm.widthPixels;
          screen_height=dm.heightPixels;
		 
		myDetector = new GestureDetector(this, new MyGestureListener());	
		db.open_database();	    
		mySurface=new mysurface(this);
		setContentView(mySurface);
		
	}
	
  
	public void dialog()//当用户按下返回按钮的时候显示菜单提示项
	{
		AlertDialog.Builder builder=new Builder(gamerunActivity.this);
	
		
						builder.setMessage("暂停游戏");
						builder.setTitle("提示框");
						builder.setPositiveButton("返回游戏", new android.content.DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								dialog.dismiss();
								
								
								mySurface.mp.start();
								mySurface.getthread().pauseflag=false;
								
								
							}
						});
					
						builder.setNegativeButton("结束游戏",new android.content.DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.dismiss();					
								application.finishAll();
							}
						});
		
		builder.create().show();
	}
	
	
	

	
	
	
	
   protected void onStart()
    {
    	super.onStart();
    	
    }
   protected void onPause()
    {
    	super.onPause();
    	
    }
   protected void onDestroy()
   {
	   super.onDestroy();
	   db.close_database();
	  
   }
	public boolean onTouchEvent(MotionEvent event) 
	{
		return myDetector.onTouchEvent(event);

	}

	public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
		{
			this.mySurface.mp.pause();
			this.mySurface.getthread().pauseflag=true;
			this.dialog();
			return true;
		}
		else return false;
	}
	
	
	
	
	private class MyGestureListener extends SimpleOnGestureListener 
	{
        
		
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) 
		{
			final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
			if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE&& Math.abs(velocityX) > FLING_MIN_VELOCITY) 
			{
				
				if (mySurface.getthread().getTracknumberofAvoider() > 0)
					mySurface.getthread().setTracknumberofAvoider(mySurface.getthread().getTracknumberofAvoider()-1);
				
			} 
			else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE&& Math.abs(velocityX) > FLING_MIN_VELOCITY)
			{
				
				if (mySurface.getthread().getTracknumberofAvoider() < 5)
					mySurface.getthread().setTracknumberofAvoider(mySurface.getthread().getTracknumberofAvoider()+1);
				
			}
			
			return false;
		}
		
		public void onLongPress(MotionEvent e)
		//public void onSingleTapUp(MotionEvent e)
		{
			
			float x=e.getX();
			//float y=e.getY();
			if(x>=250)
			{
				
				mySurface.getthread().rule.listen_goods();
				
				
			}
			
			
			
			if(mySurface.isover==true)
			{
				int score=mySurface.getthread().get_total_score();
				
				String mark=""+score;
				Bundle mes=new Bundle();
				mes.putSerializable("score", mark);
				Intent intent=new Intent(gamerunActivity.this,gameover.class);
				
				db.insert_score(MainActivity.temp.get_username(), score);
				Toast.makeText(gamerunActivity.this,"您的成绩已经提交",Toast.LENGTH_SHORT).show();
				
				int month=MainActivity.time.get(Calendar.MONTH)+1;
				String date=""+MainActivity.time.get(Calendar.YEAR)+"."+month+"."+MainActivity.time.get(Calendar.DAY_OF_MONTH)
						+"."+MainActivity.time.get(Calendar.HOUR_OF_DAY)+":"+MainActivity.time.get(Calendar.MINUTE);
				send=MainActivity.temp.get_username()+"-"+score+"-"+date;
				
				new MyThread().start();
				
				
				
				intent.putExtras(mes);
				
				
				
				gamerunActivity.this.startActivity(intent);
				
			
				
			}
			
		}
		
		
		public boolean onDoubleTap(MotionEvent e) {
			mySurface.getthread().setLongneckflag(true);
			return false;
		}
		
		private class MyThread extends Thread
		{
			Socket ss=null;
			OutputStream dout=null;
			
			public void run()
			{
				try 
				{
					ss=new Socket(game2Activity.ip,8008);
					dout=ss.getOutputStream();				
					dout.write(send.getBytes());
				} 
				catch (UnknownHostException e) 
				{
					
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					
					e.printStackTrace();
				}
			}
		}

		
		
		
	}

	
	
}
















