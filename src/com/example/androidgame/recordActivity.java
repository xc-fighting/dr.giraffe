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
	
	
	SharedPreferences preferences;//��ȡ����
	SharedPreferences.Editor editor;//editor����
	public int counts;//����
	
	private dbadapter db=new dbadapter(this);
	public Handler handler=new Handler()//����handler����
	{
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if(msg.what==0x11)//������¼��״̬��ʱ��
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

 public void dialog()//���û����·��ذ�ť��ʱ����ʾ�˵���ʾ��
 	{
 		AlertDialog.Builder builder=new Builder(recordActivity.this);
 		builder.setMessage("��ȷ��Ҫ�˳���Ϸ��?");
 		builder.setTitle("��ʾ��");
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
							Toast.makeText(recordActivity.this,"�Ѿ���������¼��,��¼���ύ���ݿ�+"+temp1.get_addr()+temp1.get_Intro()+temp1.get_Qi()+temp1.get_userId(),Toast.LENGTH_SHORT).show();
							Toast.makeText(recordActivity.this,"�Ѿ���ȡ����",Toast.LENGTH_SHORT).show();
							Intent intent=new Intent(recordActivity.this,game4Activity.class);
							recordActivity.this.startActivity(intent);
						}
						else
						{
						
							Toast.makeText(recordActivity.this,"δ��⵽��¼������,�������¼���ش�",Toast.LENGTH_SHORT).show();
						
					
						}
					}break;
					case R.id.imagerecord:
					{
						if(!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
						{
							Toast.makeText(recordActivity.this,"�����豸������sd��������������", Toast.LENGTH_SHORT).show();
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
					    	
					    	mr=new MediaRecorder();//�½�һ��¼����
					    	mr.setAudioSource(MediaRecorder.AudioSource.MIC);//¼��������˷������
					    	mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//������������ĸ�ʽ
					    	mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					    	mr.setOutputFile(soundFile.getAbsolutePath());
					    	mr.prepare();
							timer=new Timer();
							 TimerTask timerTask = new TimerTask() { 
					                
					                int i = 0; 
					 
					               
					                public void run() { 
					                   
					                    // ����һ����Ϣ����ȥ  
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
































