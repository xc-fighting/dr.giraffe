package com.example.androidgame;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;


import android.view.*;
import android.widget.Button;

import android.widget.Toast;
import android.view.View.OnClickListener;
public class game4Activity extends Activity
{
	private dbadapter db=new dbadapter(this);
 public class goods_info
 {
	
	 public int type=-1;//保存商品的类型信息,初始值为-1,可以进行保存的商品信息为0,1,2
	 public boolean isget=false;//在没有回答正确的问题之前，将其是否可以使用这个商品全部为false
	 public goods_info()//缺省的构造函数
	 {
		 
	 }
 }
//由于将保存道具信息的列表声明为静态列表，所以在其他的类中可以进行直接的调用
 public static ArrayList<goods_info> info=new ArrayList<goods_info>(3);//定义一个静态的存储信息的数组,同时要求答题的次数不能超过3次，即玩家最多只能获得三件道具

 public void onCreate(Bundle savedInstancestates)
 {
	 super.onCreate(savedInstancestates);
	 application.addActivity(this);
	 setContentView(R.layout.four);	
	 db.open_database();
	 Button btn=(Button)this.findViewById(R.id.bt1);btn.setOnClickListener(new press());	 
	 Button btn1=(Button)this.findViewById(R.id.enterthegame); btn1.setOnClickListener(new press());
	 Button btn3=(Button)this.findViewById(R.id.back1);btn3.setOnClickListener(new press());
	
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
		AlertDialog.Builder builder=new Builder(game4Activity.this);
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
	
 
 
 
 
 
 
 
 
 
 
 
 public void onDestroy()
 {
	 super.onDestroy();
	
 }
 private class press implements OnClickListener
 {
	
	 
	
	 public press()
	 {
		 
	 }
	
	 public void onClick(View v)
	 {
		 RadioButton r1=(RadioButton)findViewById(R.id.time);
		 RadioButton r2=(RadioButton)findViewById(R.id.speed);
		 RadioButton r3=(RadioButton)findViewById(R.id.function);
		 
		 
		 
		 switch(v.getId())
		 {
		 //当用户点击的按钮时答题的时候，不允许在空选的情况下选择进行答题
		 case R.id.bt1:
		 {
			

		    
			if(info.size()<3)
			{
			 if(r1.isChecked()==false&&r2.isChecked()==false&&r3.isChecked()==false)
			 {
				 Toast.makeText(game4Activity.this,"请勾选道具", Toast.LENGTH_SHORT).show();
			 }
			 else
			 {
			 goods_info temp=new goods_info();
			 if(r1.isChecked()){temp.type=0;}
			 if(r2.isChecked()){temp.type=1;}
			 if(r3.isChecked()){temp.type=2;}	
			 
			 
			 info.add(temp);//向列表中添加商品的信息
			 //将用户的信息进行一次传递
						
			 Intent intent=new Intent(game4Activity.this,game5Activity.class);
			 
			          
			 game4Activity.this.startActivity(intent);
			 
			
			 }
			}
			else 
			{
				Toast.makeText(game4Activity.this,"道具个数超过3个，请直接进入游戏", Toast.LENGTH_SHORT).show();
			}
		    
		  
			 
		 }break;
		 
		 
		 case R.id.enterthegame:
		 {
			// for(int i=0;i<info.size();i++)
			// {
			//	 if(info.get(i).isget=false)info.remove(i);
			// }
			 Intent intent=new Intent(game4Activity.this,gamerunActivity.class);
			 game4Activity.this.startActivity(intent);
			
		 }break;
		 
		 
		 case R.id.back1:
		 {
			 Intent intent=new Intent(game4Activity.this,game2Activity.class);
			 game4Activity.this.startActivity(intent);
			
		 }
		 }
		 
	 }
 }
}
