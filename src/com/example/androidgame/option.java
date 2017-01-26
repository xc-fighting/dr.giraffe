package com.example.androidgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class option extends Activity
{
	
  dbadapter db=new dbadapter(this);
  protected void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  
	  application.addActivity(this);
	  db.open_database();
	  
	  setContentView(R.layout.option);
	  Button btn=(Button)this.findViewById(R.id.update);btn.setOnClickListener(new pressbtn());
	  Button btn5=(Button)this.findViewById(R.id.button5);btn5.setOnClickListener(new pressbtn());
	  Button btn6=(Button)this.findViewById(R.id.scoreboard);btn6.setOnClickListener(new pressbtn());
	  Button btn7=(Button)this.findViewById(R.id.buttonback1);btn7.setOnClickListener(new pressbtn());
	  Button btn8=(Button)this.findViewById(R.id.teacherword);btn8.setOnClickListener(new pressbtn());
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
		AlertDialog.Builder builder=new Builder(option.this);
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

	
	  public void onClick(View v)
	  {
		  switch(v.getId())
		  {
		  case R.id.button5:
		  {
			  String role=MainActivity.temp.get_role();
			 // Toast.makeText(option.this,role, Toast.LENGTH_SHORT);
			  Intent intent=new Intent(option.this,settingActivity.class);
			  if(role.equals("teacher")==true)
			  {
			
			   option.this.startActivity(intent);
			   
			  }
			  else 
			  {
				  Toast.makeText(option.this, "学生无此权限", Toast.LENGTH_SHORT).show();
				  
			  }
		  }break;
		  case R.id.scoreboard:
		  {
			  if(db.getallscore()!=null)
			  {
			  Intent intent=new Intent(option.this,show_score.class);
			  option.this.startActivity(intent);
			  }
			  else 
			  {
				  Toast.makeText(option.this, "暂无学生成绩", Toast.LENGTH_SHORT).show();
			  }
		  }break;
		  case R.id.buttonback1:
		  {
			  Intent intent=new Intent(option.this,game2Activity.class);
			  option.this.startActivity(intent);
		  }break;
		  case R.id.teacherword:
		  {
			 Intent intent=new Intent(option.this,search_teacher_words.class); 
			 option.this.startActivity(intent);
		  }break;
		  case R.id.update:
		  {
			  if(MainActivity.temp.get_role().equals("student")==true)
			  {
				  Toast.makeText(option.this,"您是学生，无法查看答案",Toast.LENGTH_SHORT).show();
			  }
			  else
			  {
				 Intent intent=new Intent(option.this,show_student_answer.class);
				 option.this.startActivity(intent);
			  }
			 
		  }break;

		  }
	  }

	

  }
}
