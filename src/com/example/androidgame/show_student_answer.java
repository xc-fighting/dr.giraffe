package com.example.androidgame;



import com.example.androidgame.R;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;




import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class show_student_answer extends Activity
{
 private dbadapter db=new dbadapter(this);
 public static connector[] conn=null;
 public static int item;
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  
	  
	  application.addActivity(this);
	  
	  
	  setContentView(R.layout.demo);
	  db.open_database();
	  ListView lv=(ListView)this.findViewById(R.id.listView2);
	  conn=db.getallrelations();
	  if(conn!=null)
	  {
	  String []name=new String[conn.length];
	  for(int i=0;i<conn.length;i++)
	  {
		  name[i]=""+(i+1);
	  }
	
	  ArrayAdapter<String> simple=new ArrayAdapter<String>(this,R.layout.arraylist2,name);
	  lv.setAdapter(simple); 
	  lv.setTextFilterEnabled(true);
	  lv.setOnItemClickListener(new press());
	  
	  }
	  else 
	  {
		  Toast.makeText(show_student_answer.this, "暂无学生答案", Toast.LENGTH_SHORT).show();
		  Intent intent=new Intent(show_student_answer.this,option.class);
		  show_student_answer.this.startActivity(intent);
		  
	  }
	 Button btn=(Button)this.findViewById(R.id.buttonback3);
	 btn.setOnClickListener(new pressbtn());
	  
  }
  
  private class pressbtn implements OnClickListener
  {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.buttonback3:
		{
			Intent intent=new Intent(show_student_answer.this,settingActivity.class);
			show_student_answer.this.startActivity(intent);
		}break;
		}
		
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
		AlertDialog.Builder builder=new Builder(show_student_answer.this);
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
	
  
  
  
  
  
  
  
  
  
  
  
  private class press implements OnItemClickListener
  {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(show_student_answer.this,TeacherwordActivity.class);
		show_student_answer.item=position;
		Toast.makeText(show_student_answer.this,""+show_student_answer.item, Toast.LENGTH_SHORT).show();
		show_student_answer.this.startActivity(intent);
		
	}

	
	  
  }
}
