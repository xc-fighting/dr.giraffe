package com.example.androidgame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class network_order extends Activity{
 
	private String[] infos;
	
	@SuppressLint("ShowToast")
	private Handler handler=new Handler()
	{
		
		public void handleMessage(Message msg)
		{
			if(msg.what==0x234)
			{
				Toast.makeText(network_order.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
				infos=msg.obj.toString().split(",");  
				ListView lv=(ListView)findViewById(R.id.listorders);
				  ArrayAdapter<String> simple=new ArrayAdapter<String>(network_order.this,R.layout.arrayitems,infos);
				  lv.setAdapter(simple); 
				  lv.setTextFilterEnabled(true);
				  lv.setOnItemClickListener(new press());
			}
		}
	};
	public int new_grades;
	  public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.order_in_the_web);
		  
		  application.addActivity(this);
		  	  
		 
		  
		  new Mythread().start();
		  
		 
	  }
	  private class press implements OnItemClickListener
	  {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			String[] tmp=infos[position].split("-");
			int mark=Integer.parseInt(tmp[1]);
			String name=tmp[0];
			String time=tmp[2];
			//Toast.makeText(network_order.this,""+mark,Toast.LENGTH_SHORT).show();
			connAlert(mark,time,name);
			
		}

	
		  
	  }
	  
	 
	  private void connAlert(int mark,String time,String name)
	  {
	  	  final Dialog  dlg=new Dialog(this);
	  	  dlg.show();
	  	  Window window=dlg.getWindow();
	  	  window.setContentView(R.layout.networkorder123);
	  	  TextView v1=(TextView)window.findViewById(R.id.network1);v1.setText(name);
	  	  TextView v2=(TextView)window.findViewById(R.id.network2);v2.setText(time);
	  	  TextView v3=(TextView)window.findViewById(R.id.network3);v3.setText(mark+"");
	  	  Button btn=(Button)window.findViewById(R.id.netbutton);
	  	  btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId()==R.id.netbutton)
				{
					dlg.dismiss();
				}
				
			}
	  		  
	  	  });
	  		  
	  	  
	  	  
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  private class Mythread extends Thread
	  {
		  private Socket mysocket=null;
		  private InputStream din=null;
		  private OutputStream dout=null;
		  public void run()
		  {
			  try {
				
				  mysocket=new Socket(game2Activity.ip,8008);
				din=mysocket.getInputStream();
				dout=mysocket.getOutputStream();
				
				String send="get_grades";
				
				dout.write(send.getBytes());
				
				byte[] buffer=new byte[1024];
				
				int index=din.read(buffer);				
				String receive=new String(buffer,0,index);				
				Message msg=new Message();
				msg.what=0x234;
				msg.obj=receive;
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
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
