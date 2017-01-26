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
	
	 public int type=-1;//������Ʒ��������Ϣ,��ʼֵΪ-1,���Խ��б������Ʒ��ϢΪ0,1,2
	 public boolean isget=false;//��û�лش���ȷ������֮ǰ�������Ƿ����ʹ�������Ʒȫ��Ϊfalse
	 public goods_info()//ȱʡ�Ĺ��캯��
	 {
		 
	 }
 }
//���ڽ����������Ϣ���б�����Ϊ��̬�б����������������п��Խ���ֱ�ӵĵ���
 public static ArrayList<goods_info> info=new ArrayList<goods_info>(3);//����һ����̬�Ĵ洢��Ϣ������,ͬʱҪ�����Ĵ������ܳ���3�Σ���������ֻ�ܻ����������

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

public void dialog()//���û����·��ذ�ť��ʱ����ʾ�˵���ʾ��
	{
		AlertDialog.Builder builder=new Builder(game4Activity.this);
		builder.setMessage("��ȷ��Ҫ�˳���Ϸ��?");
		builder.setTitle("��ʾ��");
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
		 //���û�����İ�ťʱ�����ʱ�򣬲������ڿ�ѡ�������ѡ����д���
		 case R.id.bt1:
		 {
			

		    
			if(info.size()<3)
			{
			 if(r1.isChecked()==false&&r2.isChecked()==false&&r3.isChecked()==false)
			 {
				 Toast.makeText(game4Activity.this,"�빴ѡ����", Toast.LENGTH_SHORT).show();
			 }
			 else
			 {
			 goods_info temp=new goods_info();
			 if(r1.isChecked()){temp.type=0;}
			 if(r2.isChecked()){temp.type=1;}
			 if(r3.isChecked()){temp.type=2;}	
			 
			 
			 info.add(temp);//���б��������Ʒ����Ϣ
			 //���û�����Ϣ����һ�δ���
						
			 Intent intent=new Intent(game4Activity.this,game5Activity.class);
			 
			          
			 game4Activity.this.startActivity(intent);
			 
			
			 }
			}
			else 
			{
				Toast.makeText(game4Activity.this,"���߸�������3������ֱ�ӽ�����Ϸ", Toast.LENGTH_SHORT).show();
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
