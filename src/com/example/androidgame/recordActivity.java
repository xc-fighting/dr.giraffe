package com.example.androidgame;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class recordActivity extends Activity
{
	public static int index_voice=0;

	private Timer timer;
	private TextView view1;
	private MediaRecorder mr;
	File soundFile=null;
	
	
	SharedPreferences preferences;//获取对象
	SharedPreferences.Editor editor;//editor对象
	public int counts;//计数
	
	private dbadapter db=new dbadapter(this);
	public Handler handler=new Handler()//定义handler对象
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if(msg.what==0x11)//当处于录音状态的时候
			{
				view1.setText(""+msg.obj+"seconds");
			}
		}
	};

	public void onDestroy()
	{
		if(soundFile!=null&&soundFile.exists())
		{
			mr.stop();
			mr.release();
			mr=null;
		}
		db.close_database();
		super.onDestroy();
	}
	
	
	
  protected void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  
	  setContentView(R.layout.record);
	  
	  application.addActivity(this);
	  	 
	  preferences=getSharedPreferences("fileindex",MODE_PRIVATE);
	  editor=preferences.edit();
	  
	  db.open_database();
	  
	  Button btn=(Button)this.findViewById(R.id.endrecord1);
	  
	  btn.setOnClickListener(new press());
	  
	  view1=(TextView)this.findViewById(R.id.textViewtime);
	  
	  ImageButton btn1=(ImageButton)this.findViewById(R.id.imagerecord); btn1.setOnClickListener(new press());
	  
	 
  }
  
/*--------------------------------------------------------------------------------------------------------------------*/ 
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
 		AlertDialog.Builder builder=new Builder(recordActivity.this);
 		builder.setMessage("你确定要退出游戏吗?");
 		builder.setTitle("提示框");
 		builder.setPositiveButton("YES", new android.content.DialogInterface.OnClickListener() {
 			
 			@Override
 			public void onClick(DialogInterface dialog, int which) {
 				// TODO Auto-generated method stub
 				
 				dialog.dismiss();
 				application.finishAll();
 				
 				
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
 	
/*--------------------------------------------------------------------------------------------------------*/ 
  
  
  
  
  
  
  private class press implements OnClickListener
  {

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
					case R.id.endrecord1:
					{
						if(soundFile!=null)
						{
							timer.cancel();
							mr.stop();
							mr.release();
							Intent tmp=getIntent();
							connector temp1=(connector)tmp.getSerializableExtra("information0");
							temp1.set_address(soundFile.getAbsolutePath());
							 if(db.get_relation_accord_to_connector(temp1)==null) db.insert_user_question(temp1);								
							 else db.update_student_answer(temp1);
							Toast.makeText(recordActivity.this,"已经结束您的录制,将录音提交数据库+"+temp1.get_addr()+temp1.get_Intro()+temp1.get_Qi()+temp1.get_userId(),Toast.LENGTH_SHORT).show();
							Toast.makeText(recordActivity.this,"已经获取道具",Toast.LENGTH_SHORT).show();
							Intent intent=new Intent(recordActivity.this,game4Activity.class);
							recordActivity.this.startActivity(intent);
						}
						else
						{
						
							Toast.makeText(recordActivity.this,"未检测到您录制声音,请你进行录音回答",Toast.LENGTH_SHORT).show();
						
					
						}
					}break;
					case R.id.imagerecord:
					{
						if(!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
						{
							Toast.makeText(recordActivity.this,"您的设备不存在sd卡，请文字作答", Toast.LENGTH_SHORT).show();
							Intent intent=new Intent(recordActivity.this,game5Activity.class);
							recordActivity.this.startActivity(intent);
						}
						else
						{
							
					      try
					      {
					    	counts=preferences.getInt("xuhao", 0);
					    	String mulu="/"+counts+"_records.amr";
					    	
					    	soundFile=new File(Environment.getExternalStorageDirectory().getCanonicalFile()+mulu);
					    	
					    	editor.putInt("xuhao", ++counts);
					    	editor.commit();
					    	
					    	mr=new MediaRecorder();//新建一个录音器
					    	mr.setAudioSource(MediaRecorder.AudioSource.MIC);//录制来自麦克风的声音
					    	mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//设置输出声音的格式
					    	mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					    	mr.setOutputFile(soundFile.getAbsolutePath());
					    	mr.prepare();
							timer=new Timer();
							 TimerTask timerTask = new TimerTask() { 
					                
					                int i = 0; 
					 
					               
					                public void run() { 
					                   
					                    // 定义一个消息传过去  
					                    Message msg = new Message(); 
					                    msg.what =0x11; 
					                    msg.obj=i++;
					                    handler.sendMessage(msg); 
					                } 
					            }; 
					            timer.schedule(timerTask, 0,1000);
					            mr.start();
						}
					      catch(Exception e)
					      {
					    	  e.printStackTrace();
					      }
						}
						
					}break;
					
		}
		
	}
	  
  }
}
































