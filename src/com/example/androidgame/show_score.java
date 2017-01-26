package com.example.androidgame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.View.OnClickListener;


public class show_score extends Activity
{
  private dbadapter db=new dbadapter(this);
 
  
  public int []mark;
  protected void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  
	  
	  application.addActivity(this);
	  
	  
	  setContentView(R.layout.markboard);
	  db.open_database();
	  ListView lv=(ListView)this.findViewById(R.id.listView1);
	  
	  
	  ScoreBoard[] scores=db.getallscore();
	  String []name=new String[scores.length];
	  mark=new int [scores.length];
	  for(int i=0;i<scores.length;i++)
	  {
		  name[i]=scores[i].name;
		  
	  }
	  for(int j=0;j<scores.length;j++)
	  {
		  mark[j]=scores[j].score;
	  }
	  ArrayAdapter<String> simple=new ArrayAdapter<String>(this,R.layout.arrayitems,name);
	  lv.setAdapter(simple); 
	  lv.setTextFilterEnabled(true);
	  lv.setOnItemClickListener(new press());
	  Button btn=(Button)this.findViewById(R.id.buttonback2);
	  btn.setOnClickListener(new pressbtn());
	  Button btn1=(Button)this.findViewById(R.id.networkpaiming);
	  btn1.setOnClickListener(new pressbtn());
	  
  }
  private class pressbtn implements OnClickListener
  {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.buttonback2:
		{
			Intent intent=new Intent(show_score.this,option.class);
			show_score.this.startActivity(intent);
			
		}break;
		case R.id.networkpaiming:
		{
			Intent intent=new Intent(show_score.this,network_order.class);
			show_score.this.startActivity(intent);
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
		AlertDialog.Builder builder=new Builder(show_score.this);
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
  
  private class press implements OnItemClickListener
  {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		//Toast.makeText(show_score.this,"该玩家的得分"+mark[position], Toast.LENGTH_SHORT).show();
		alertdialog(mark[position]);
	}
	  
  }
  
  public void alertdialog(int mark)
  {
	  AlertDialog.Builder builder=new Builder(show_score.this);
	  builder.setMessage(""+mark);
	  builder.setTitle("该玩家的得分是");
	  builder.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener()
	  {

		@Override
		public void onClick(DialogInterface dialog, int arg1) {
			// TODO Auto-generated method stub
			dialog.dismiss();
		}
		  
	  }
	    ).show();
  }
  
	  
  
  
  
  
  
  
  
  
}
