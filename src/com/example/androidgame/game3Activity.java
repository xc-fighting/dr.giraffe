package com.example.androidgame;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;


import android.view.*;
import android.widget.Button;

import android.view.View.OnClickListener;
public class game3Activity extends Activity
{
  public static final String []titles={"unit1","unit2","unit3","unit4"};
  public static int index;
  private dbadapter db=new dbadapter(this);
 public void onCreate(Bundle savedInstancestate)
 {
	 super.onCreate(savedInstancestate);
	 application.addActivity(this);
	 setContentView(R.layout.three);
	 
	 
	 db.open_database();
	 
	 
	 Button btn=(Button)this.findViewById(R.id.back);
	 btn.setOnClickListener(new press());
	 ImageButton b1=(ImageButton)this.findViewById(R.id.imageButton1);b1.setOnClickListener(new press());
	 ImageButton b2=(ImageButton)this.findViewById(R.id.imageButton2);b2.setOnClickListener(new press());
	 ImageButton b3=(ImageButton)this.findViewById(R.id.imageButton3);b3.setOnClickListener(new press());
	 ImageButton b4=(ImageButton)this.findViewById(R.id.imageButton4);b4.setOnClickListener(new press());
 }
 
 
 public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
		{
			
			this.dialog();
			return true;
		}
		else return false;
	}

public void dialog()//当用户按下返回按钮的时候显示菜单提示项
	{
		AlertDialog.Builder builder=new Builder(game3Activity.this);
		builder.setMessage("你确定要退出游戏吗?");
		builder.setTitle("提示框");
		builder.setPositiveButton("YES", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				dialog.dismiss();
				application.finishAll();
				//gamerunActivity.this.finish();
				
			}
		});
		builder.setNegativeButton("NO",new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
			}
		});
		builder.create().show();
	}
	 
 
 
 
 
 
 
 
 private class press implements OnClickListener
 {
	
	
	 public press()
	 {
		 
	 }
	public void onClick(View v)
	 {
		 switch(v.getId())
		 {
		 case R.id.back:
		 {
			
			 Intent intent=new Intent(game3Activity.this,game2Activity.class);
			
			 game3Activity.this.startActivity(intent);
			 
			
			
			 
			
				
			 
			 
			 
		 }break;
		 case R.id.imageButton1:
		 {
			 
			 Intent intent=new Intent(game3Activity.this,game4Activity.class);
			
			 game3Activity.index=0;
			 game3Activity.this.startActivity(intent);
			 
			
			 
			
				
			 
			
				 
			
		 }break;
		 case R.id.imageButton2:
		 {
			 
			 Intent intent=new Intent(game3Activity.this,game4Activity.class);
			
			 game3Activity.index=1;
			 game3Activity.this.startActivity(intent);
			 
			
			 
			 
			
		 }break;
		 case R.id.imageButton3:
		 {
			
			 Intent intent=new Intent(game3Activity.this,game4Activity.class);
			
			 game3Activity.index=2;
			 game3Activity.this.startActivity(intent);
			
			
			 
			 
		 }break;
		 case R.id.imageButton4:
		 {
			
			
			 Intent intent=new Intent(game3Activity.this,game4Activity.class);
			 
			 game3Activity.index=3;
			 game3Activity.this.startActivity(intent);
			
		 }break;
		 }
		
	 }
 }
}
