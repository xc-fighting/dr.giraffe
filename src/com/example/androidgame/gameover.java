package com.example.androidgame;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;

import android.view.View.OnClickListener;



public class gameover extends Activity
{
	
	
  protected void onCreate(Bundle savedinstanceState)
  {
	  super.onCreate(savedinstanceState);
	  
	
	  
	  Intent intent=getIntent();
	  String temp=(String)intent.getStringExtra("score");
	 
	  setContentView(R.layout.gameend);
	  TextView show_mark=(TextView)this.findViewById(R.id.endmark);
	  TextView show_name=(TextView)this.findViewById(R.id.endname);
	  show_mark.setText(temp);
	  show_name.setText(MainActivity.temp.get_username());
	  Button btn=(Button)this.findViewById(R.id.luo);
	  btn.setOnClickListener(new pressbtn());
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
		AlertDialog.Builder builder=new Builder(gameover.this);
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
	
  
  
  
  
  protected void onDestroy()
  {
	  super.onDestroy();
	 
  }
  
  
  
  private class pressbtn implements OnClickListener
  {
	  

		
		public void onClick(View v) {
					
		
			Intent intent=new Intent(gameover.this,game2Activity.class);
			gameover.this.startActivity(intent);
			gameover.this.finish();
		}
  }
}
