package com.example.androidgame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class multiplayActivity extends Activity{
	
	
	EditText input;
	TextView show;
	Button send;
	String message_send=null;
	ClientThread client;
	
	Handler handler=new Handler()
	{
		public void handleMessage(Message msg)
		  {
			super.handleMessage(msg);
			switch(msg.what)
			{
				case 0x123:
				{
					show.setText("\n"+msg.obj.toString());
				}break;
				case -1:
				{
					show.setText("服务器的连接出现异常\n");
				}break;
				case 0x555:
				{
					new receiveThread().start();
				}break;
				
				
			}
			/*try {
				client.join();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}*/
		  }
	};
	ClientThread clientThread;
   public void onCreate(Bundle savedInstanceState)
   {
	   super.onCreate(savedInstanceState);
	   
	   application.addActivity(this);
	   
	   
	   setContentView(R.layout.multiplaychat);
	   input=(EditText)findViewById(R.id.editTextchat);
	   send=(Button)findViewById(R.id.sendtoserver);
	   show=(TextView)findViewById(R.id.chattext);
	   send.setOnClickListener(new press());
	   
	   ImageButton btn=(ImageButton)this.findViewById(R.id.refreshButton1);
	   btn.setOnClickListener(new OnClickListener()
	   {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Message mes=new Message();
			mes.what=0x555;
			handler.sendMessage(mes);
			
		}
		   
	   });
	
	 
	  
	  
   }
   
   
   
   private class press implements OnClickListener
   {	
			public void onClick(View v) 
			{	 
			  	client=new ClientThread();
			  	client.start();
			}	   
   }
   
   
   
   
   public class ClientThread extends Thread{

		private Socket s=null;
		
		InputStream din=null;
		OutputStream dout=null;
		
		public ClientThread() 
		{
			
		}
		public void init() throws UnknownHostException, IOException
		{
			
			s=new Socket(game2Activity.ip,8008);
		}
		public void run() {
			// TODO Auto-generated method stub
			    
			     
				try {
					
					if(s==null)init();					
					din=s.getInputStream();
				    dout=s.getOutputStream();				   
				
					try 
					{
									
								    message_send=MainActivity.temp.get_username()+" says:"+input.getText().toString();							   
								    dout.write(message_send.getBytes());							  
								    new receiveThread(this.s).start();
								    
								   
					} 
					catch (IOException e)
					{
								
						e.printStackTrace();
					}
					
					
				   
				} 
				catch (UnknownHostException e)
				{
					
					e.printStackTrace();
					 Message msg=new Message();
					
					    msg.what=-1;
					    handler.sendMessage(msg);
					
				} 
				catch (IOException e) 
				{
					
					e.printStackTrace();
					 Message msg=new Message();
					  
					    msg.what=-1;
					    handler.sendMessage(msg);
				}
				
				
				
			
			
		}

	}

private class receiveThread extends Thread
{
	private Socket s=null;
	public receiveThread()
	{
		
	}
	
	 public receiveThread(Socket ss)
	 {
		 this.s=ss;
	 }
	 public void run()
	 {
		 try
		 {
			 
			if(s==null)s=new Socket(game2Activity.ip,8008);
		
		     OutputStream dout=s.getOutputStream();
		     
		     
			  InputStream din=s.getInputStream();
			  dout.write(("get").getBytes());
			  byte[] buffer=new byte[1024];
			  int index=din.read(buffer);
			  String receive=new String(buffer,0,index);
			  Message msg=new Message();
			  msg.what=0x123;
			  msg.obj=receive;
			  handler.sendMessage(msg);
			 
			 
			 
		 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 Message msg=new Message();
				
			    msg.what=-1;
			    handler.sendMessage(msg);
		 }
	 }
}
   
   
   
   
   
   
   
   
}
