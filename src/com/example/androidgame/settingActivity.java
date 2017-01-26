package com.example.androidgame;

import com.example.androidgame.R;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;



import android.view.*;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import android.view.View.OnClickListener;
public class settingActivity extends Activity
{
	private dbadapter db=new dbadapter(this);
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  
	application.addActivity(this);
	
	
	  setContentView(R.layout.setting);
	  db.open_database();
	  Button btn=(Button)this.findViewById(R.id.ack);
	  Button btn1=(Button)this.findViewById(R.id.jixu);
	 
	  Button btn3=(Button)this.findViewById(R.id.resetall);
	  Button btn4=(Button)this.findViewById(R.id.buttonback4);
	  btn.setOnClickListener(new press());
	  btn1.setOnClickListener(new press());
	 
	  btn3.setOnClickListener(new press());
	  btn4.setOnClickListener(new press());
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
		AlertDialog.Builder builder=new Builder(settingActivity.this);
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
	  public void onClick(View v)
	  {
		  EditText t1=(EditText)findViewById(R.id.title);
		  EditText t2=(EditText)findViewById(R.id.intro);
		  EditText t3=(EditText)findViewById(R.id.answer);
		  String str1=t1.getText().toString();
		  String str2=t2.getText().toString();
		  String str3=t3.getText().toString();
		  switch(v.getId())
		  {
		  case R.id.ack:
		  {			
				  question q=new question(str1,str2,str3);
				  db.insert_question(q);
				  Toast.makeText(settingActivity.this,"设置问题成功",Toast.LENGTH_SHORT).show();
		  }break;
		  case R.id.jixu:
		  {
			  t1.setText("");
			  t2.setText("");
			  t3.setText("");
		  }break;
		 
		  case R.id.resetall:
		  {
			  db.deleteAllanswers();
			  db.deleteAllScore();
			  db.deleteAllData();
			  Toast.makeText(settingActivity.this,"成功清空数据表", Toast.LENGTH_SHORT).show();
			  Intent intent=new Intent(settingActivity.this,game2Activity.class);
			  settingActivity.this.startActivity(intent);
		  }break;
		  case R.id.buttonback4:
		  {
			  Intent intent=new Intent(settingActivity.this,option.class);
			  settingActivity.this.startActivity(intent);
		  }break;
		  }
	  }
  }
}
