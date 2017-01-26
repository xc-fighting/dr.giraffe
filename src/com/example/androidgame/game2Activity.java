package com.example.androidgame;
/*import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;*/
import java.util.Calendar;


import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
//import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;



import android.view.*;
import android.widget.Button;
//import android.widget.EditText;


import android.widget.Toast;
import android.view.View.OnClickListener;
public class game2Activity extends Activity
{
	public static Calendar time=Calendar.getInstance();
	public dbadapter db=new dbadapter(this);
	public static String ip=MainActivity.ip;
	/*public Handler handler=new Handler()
	{
		
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0x111:{Toast.makeText(game2Activity.this,"当前连接可用",Toast.LENGTH_SHORT).show();}break;
			case 0x999:{Toast.makeText(game2Activity.this,"连接超时，请检查服务器ip是否正确",Toast.LENGTH_SHORT).show();}break;
			}
		}
	};*/
	 
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	
	  application.addActivity(this);
		db.open_database();
	  setContentView(R.layout.two);
	 
	
	
	  Button btn1=(Button)this.findViewById(R.id.button1);
	  Button btn2=(Button)this.findViewById(R.id.Button2);
	  Button btn3=(Button)this.findViewById(R.id.Button3);
	  Button btn4=(Button)this.findViewById(R.id.Button4);
	  Button btn5=(Button)this.findViewById(R.id.options);
	 // Button btn6=(Button)this.findViewById(R.id.button6);
	  btn1.setOnClickListener(new pressbtn());
	  btn2.setOnClickListener(new pressbtn());
	  btn3.setOnClickListener(new pressbtn());
	  btn4.setOnClickListener(new pressbtn());
	  btn5.setOnClickListener(new pressbtn());
	//  btn6.setOnClickListener(new pressbtn());
	  
	  
	//   e1=(EditText)findViewById(R.id.ipconfig);
	  
	  
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
		AlertDialog.Builder builder=new Builder(game2Activity.this);
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
	  db.close_database();
  }
  
  
  
  private class pressbtn implements OnClickListener
  {
	 
	  public pressbtn()
	  {
		  
	  }
	 
	  public void onClick(View v)
	  {
		  switch(v.getId())
		  {
		  case R.id.button1:
		  {  
			  Intent intent=new Intent(game2Activity.this,game3Activity.class);			 
			  game2Activity.this.startActivity(intent);
			  
		  }break;
		  case R.id.Button2:
		  {
			Intent intent=new Intent(game2Activity.this,multiplayActivity.class);
			game2Activity.this.startActivity(intent);
		  }break;
		  case R.id.Button3:
		  {
			 Intent intent=new Intent(game2Activity.this,helps.class);
			 game2Activity.this.startActivity(intent);
		  }break;
		  case R.id.Button4:
		  {
			  
			  int month=time.get(Calendar.MONTH)+1;
			  String date=""+time.get(Calendar.YEAR)+"-"+month+"-"+time.get(Calendar.DAY_OF_MONTH)
						+"-"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE);
			  MainActivity.temp.set_logouttime(date);
			
			  Toast.makeText(game2Activity.this,MainActivity.temp.get_userId()+" "+MainActivity.temp.get_username()+date,Toast.LENGTH_SHORT).show();
			
			  db.update_user_logout_time(date,MainActivity.temp.get_username());
			  Intent intent=new Intent();
			  intent.setAction(Intent.ACTION_MAIN);
			  intent.addCategory(Intent.CATEGORY_HOME);
			  startActivity(intent);
			  
			  application.finishAll();
			 
			  
		  }break;
		  case R.id.options:
		  {
			  Intent intent=new Intent(game2Activity.this,option.class);
			  game2Activity.this.startActivity(intent);
			  
		  }break;
		 /* case R.id.testconn:
		  {
			  String ipaddr=e1.getText().toString();
			  new mythread(ipaddr).start();
		  }break;*/
		 /* case R.id.button6:
		  {
			  connAlert();
		  }break;*/
		
		  }
	  }
  }
  
  
  /*private class mythread extends Thread
  {
	  private Socket ss=null;
	  private String ip=null;
	  public mythread(String tmp)
	  {
		  this.ip=tmp;
	  }
	  public void run()
	  {
		  try 
		  {
			ss=new Socket(ip,8008);
			ss.setSoTimeout(5000);
			//ss.getOutputStream().write("getip".getBytes());
			Message msg=new Message();
			msg.what=0x111;
			handler.sendMessage(msg);
			
			game2Activity.ip=ip;
			
		  } 
		  catch(SocketTimeoutException e)
		  {
			  Message msg=new Message();
				msg.what=0x999;
				handler.sendMessage(msg);
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
  }*/
  
  /*EditText e1=null;
  private void connAlert()
  {
	  final Dialog  dlg=new Dialog(game2Activity.this);
	  dlg.show();
	  Window window=dlg.getWindow();
	  window.setContentView(R.layout.netsetting);
	  Button btn=(Button)window.findViewById(R.id.conn1);
	  e1=(EditText)window.findViewById(R.id.editconn);
	  btn.setOnClickListener(new OnClickListener()
	  {
		  

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 String ipaddr=e1.getText().toString();
			 if(ipaddr.equals("")==false)
			 {
			  new mythread(ipaddr).start();
			  dlg.cancel();
			 }
			 else
			 {
				 Toast.makeText(game2Activity.this, "请您填写ip地址", Toast.LENGTH_SHORT).show();
			 }
			
		}
		  
	  });
	  Button btn7=(Button)window.findViewById(R.id.cancel);
	  btn7.setOnClickListener(new OnClickListener()
	  {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			dlg.cancel();
			
		}
		  
	  });
	  
  }*/
  
  
  
  
  
  
}
