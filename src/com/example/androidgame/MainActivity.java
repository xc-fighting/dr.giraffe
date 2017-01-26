package com.example.androidgame;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.*;


import android.view.*;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import android.view.View.OnClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Calendar;

import com.example.androidgame.R;



public class MainActivity extends Activity {
	
	private EditText e1=null;
    public static String ip=null;
	public static user temp=new user();
	public static Calendar time=Calendar.getInstance();
	private dbadapter db=new dbadapter(this);
	public Handler handler=new Handler()
	{
		
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
			case 0x111:{Toast.makeText(MainActivity.this,"当前连接可用",Toast.LENGTH_SHORT).show();}break;
			case 0x999:{Toast.makeText(MainActivity.this,"连接超时，请检查服务器ip是否正确",Toast.LENGTH_SHORT).show();}break;
			}
		}
	};
	
	
	public  void initquestions()
	{
		if(db.getallquestion()==null)
		{
			question ques=new question();
			
			ques.set_title("unit1");
			ques.set_quesintro("please answer the name of passage you learn today?\nA:hello world\nB:nihao\nC:haha");
			ques.set_answer("A");
			db.insert_question(ques);
			
			ques.set_title("unit2");
			ques.set_quesintro("please answer the meanining of the native\nA:local\nB:end\nC:delighted");
			ques.set_answer("A");
			db.insert_question(ques);
			
			ques.set_title("unit3");
			ques.set_quesintro("please answer the meanining of the liberate\nA:free sb\nB:in purpose\nC:delighted");
			ques.set_answer("A");
			db.insert_question(ques);
			
			
			ques.set_title("unit4");
			ques.set_quesintro("please answer the meanining of the deliberate\nA:local\nB:in prupose\nC:delighted");
			ques.set_answer("B");
			db.insert_question(ques);
			
			
			
		}
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
		AlertDialog.Builder builder=new Builder(MainActivity.this);
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
	
private void connAlert()
{
	  final Dialog  dlg=new Dialog(MainActivity.this);
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
			ip=ipaddr;
			new mythread(ip).start();
			dlg.dismiss();
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
	  
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		
	    application.addActivity(this);
		
		setContentView(R.layout.activity_main);
		db.open_database();
		
		initquestions();
		Button btn1=(Button)this.findViewById(R.id.button1);
		Button btn2=(Button)this.findViewById(R.id.button2);
		Button btn=(Button)this.findViewById(R.id.btnnet);
		Button btn3=(Button)this.findViewById(R.id.xuchen);
		btn1.setOnClickListener(new pressbtn());
	    btn2.setOnClickListener(new pressbtn());
	    btn3.setOnClickListener(new pressbtn());
		btn.setOnClickListener(new pressbtn());
	    //connAlert();
		
	}
	protected void onDestroy()
	{
		super.onDestroy();
		db.close_database();
	}
	
	
	
	
	
	 private class mythread extends Thread
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
	  }
	
	
	/*private class getThread extends Thread
	{
		InputStream s=null;
		public String exist=null;
		public getThread(InputStream ss)
		{
			this.s=ss;
		}
		public void run()
		{
			byte[] buffer=new byte[1024];
			try
			{
				int index=s.read(buffer);
				this.exist=new String(buffer,0,index);
				
			} 
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
		}
		
		
	}*/
	private class connect extends Thread{
		private user send=null;
		//private InputStream din=null;
		private OutputStream dout=null;
		
		//private getThread get1=null;
		public connect(user u)
		{
			this.send=u;
		}
		public void run() 
		{
			try
			{
				Socket s=new Socket(ip,8008);
				dout=s.getOutputStream();
				String message=send.get_username()+"~"+send.get_pwd()+"~"+send.get_role();
				dout.write(message.getBytes());
				//Message mes=new Message();
				//mes.obj=message;
				//mes.what=0x67;
			//	handler.sendMessage(mes);
				/*din=s.getInputStream();
				get1=new getThread(din);
				get1.start();*/
				
				
				
			} 
			catch (UnknownHostException e) 
			{
				
				e.printStackTrace();
			} catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}
		
		
		
		
	}
	private class pressbtn implements OnClickListener
	{
		public void onClick(View v)
		{
			EditText t1=(EditText)findViewById(R.id.editText1);
			EditText t2=(EditText)findViewById(R.id.editText2);
			RadioButton teacher=(RadioButton)findViewById(R.id.radioButton2);
			RadioButton student=(RadioButton)findViewById(R.id.radioButton1);
			CheckBox box=(CheckBox)findViewById(R.id.checkBox1);
			String str1=t1.getText().toString();
			String str2=t2.getText().toString();
			int month=time.get(Calendar.MONTH)+1;
			String date=""+time.get(Calendar.YEAR)+"-"+month+"-"+time.get(Calendar.DAY_OF_MONTH)
					+"-"+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE);
			Toast.makeText(MainActivity.this,date,Toast.LENGTH_SHORT).show();
			switch(v.getId())
			{
			case R.id.button1:
			{
				
				if(str1.equals("")==false&&str2.equals("")==false)
				{       
					   
						if(db.getOneUser(str1, str2)==false)
						{							
							Toast.makeText(MainActivity.this, "该用户不存在或者密码错误", Toast.LENGTH_SHORT).show();//如果不存在这个用户名
						}
						else 
						{
							user []users=db.getUsers_accord_to_name(str1);
							int i=users.length-1;
							temp=new user(users[i].get_username(),users[i].get_pwd(),users[i].get_role(),date,"0/0/0");
							db.update_user_accordto_name(temp);
							Bundle message=new Bundle();
							message.putSerializable("user_info", temp);
							Intent intent=new Intent(MainActivity.this,game2Activity.class);
							intent.putExtras(message);
							MainActivity.this.startActivity(intent);
						   
						}
				}
				else Toast.makeText(MainActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
				
			}break;
			case R.id.button2:
			{
				if(str1.equals("")==false&&str2.equals("")==false)
				{
				if(db.getUsers_accord_to_name(str1)!=null)Toast.makeText(MainActivity.this, "该用户已经存在", Toast.LENGTH_SHORT).show();
				else
				{
					if(box.isChecked())
					{
							 String str5="";
							if(teacher.isChecked())
							{
							 str5="teacher";
							}
							if(student.isChecked())
							{
							 str5="student";
							}
							user person=new user(str1,str2,str5,"","");//对于注册用户，不加入其登录时间
							db.insert_user(person);
							new connect(person).start();
							Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
					}
					else 
					{
						Toast.makeText(MainActivity.this, "请选择注册", Toast.LENGTH_SHORT).show();
					}
				}
				}
				else Toast.makeText(MainActivity.this, "请完整填写信息", Toast.LENGTH_SHORT).show();
			}break;
			case R.id.xuchen:
			{
				Intent intent=new Intent(MainActivity.this,loginhelpActivity.class);
				MainActivity.this.startActivity(intent);
			}break;
			case R.id.btnnet:
			{
				connAlert();
			}break;
			}
		}
	}

		
		
	
	

	
	

}
