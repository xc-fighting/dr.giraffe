package com.example.androidgame;
import java.io.IOException;

import com.example.androidgame.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class TeacherwordActivity extends Activity
{
	public int index;
	private dbadapter db=new dbadapter(this);
	private MediaPlayer mplayer=new MediaPlayer();
	
   protected void onCreate(Bundle savedInstanceState)
   {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.show_studentinfo);
	   
	  application.addActivity(this);
		
		
	   db.open_database();
	   TextView view1=(TextView)this.findViewById(R.id.stuname);
	   TextView view2=(TextView)this.findViewById(R.id.qunit);
	   TextView view3=(TextView)this.findViewById(R.id.wen);
	   TextView view4=(TextView)this.findViewById(R.id.daan);
	  
	   index=show_student_answer.item;
	   view1.setText(show_student_answer.conn[index].get_userId());
	   view2.setText(show_student_answer.conn[index].get_Qi());
	   view3.setText(show_student_answer.conn[index].get_Intro());
	   view4.setText(show_student_answer.conn[index].get_answer());
	   Button btn=(Button)this.findViewById(R.id.submit);
	   btn.setOnClickListener(new press());
	   ImageButton btn1=(ImageButton)this.findViewById(R.id.imageButton1play);
	   btn1.setOnClickListener(new press());
	   Button btn2=(Button)this.findViewById(R.id.back666);
	   btn2.setOnClickListener(new press());
	   
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
		AlertDialog.Builder builder=new Builder(TeacherwordActivity.this);
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
	
   
   
   
   
   
   
   
   
   
   
   protected void onDestroy()
   {
	   super.onDestroy();
	   db.close_database();
   }
   private class press implements OnClickListener
   {
	
	   public void onClick(View v)
	   {
		 switch(v.getId())
		 {
		  
				  case R.id.submit:
				  {
				   EditText text1=(EditText)findViewById(R.id.pingjia);
				   String st=text1.getText().toString();
				   if(st.equals("")==false)
				   {
				   show_student_answer.conn[index].teacher_words=st;
				   if(db.get_relation_accord_to_connector(show_student_answer.conn[index])!=null)
				   db.update_student_answer(show_student_answer.conn[index]);
				   else
					   db.insert_user_question(show_student_answer.conn[index]);
				   Toast.makeText(TeacherwordActivity.this,"教师评价添加成功",Toast.LENGTH_SHORT).show();
				   Intent intent=new Intent(TeacherwordActivity.this,option.class);
				   TeacherwordActivity.this.startActivity(intent);
				  
				   }
				   else
				   {
					   Toast.makeText(TeacherwordActivity.this, "教师评论不能为空",Toast.LENGTH_SHORT).show();
				   }
				  }break;
				  case R.id.imageButton1play:
				  {
					 
					  String addr=show_student_answer.conn[index].get_addr();
					
					  if(addr.equals("")==false)
					 {
					    //Toast.makeText(TeacherwordActivity.this,addr,Toast.LENGTH_SHORT).show();
					    try {
							mplayer.setDataSource(addr);//这句话不对
							mplayer.prepare();
							mplayer.start();
							if(mplayer.isPlaying()==false)
							{
								mplayer.stop();
							    mplayer.release();
							}
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalStateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    
					  }
					  else
					  {
						  Toast.makeText(TeacherwordActivity.this,"不存在该生的音频",Toast.LENGTH_SHORT).show();
					  }
				  }break;
				  case R.id.back666:
				  {
					  Intent intent=new Intent(TeacherwordActivity.this,option.class);
					  TeacherwordActivity.this.startActivity(intent);
				  }break;
		 }
	   }
   }
}
