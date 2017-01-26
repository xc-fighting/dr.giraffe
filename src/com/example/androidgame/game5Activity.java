package com.example.androidgame;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import java.util.Random;

import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;






public class game5Activity extends Activity
{
	private dbadapter db=new dbadapter(this);
	
	
	
	
  public void onCreate(Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	
	  application.addActivity(this);
		
	  setContentView(R.layout.five);
	  
	  db.open_database();	  //打开数据库
	    
	  TextView v1=(TextView)findViewById(R.id.question);  
	  
	  question[] q=db.getquestion_accord_to_title(game3Activity.titles[game3Activity.index]);//通过刚才选择的单元号寻找对应的题目,并且将其临时存放在问题的数组中
	 
	  Random j=new Random();    //在其中产生一个随机数
	  
	  int i=j.nextInt(q.length);//在随机数组中挑选题目
	  
	  String str1=""; 
	  
	  str1="\t题目名称\n"+"\t"+str1+q[i].get_title()+"\n"+"\t题目描述\n"+"\t"+q[i].get_intro()+"\n"+
	  q[i].get_answer()+"\n";	
	  
	  v1.setText(str1);
	  
	  
	  
	  Button bt=(Button)this.findViewById(R.id.bt1);
	  
	  bt.setOnClickListener(new press(q[i]));
	  
	  Button bt1=(Button)this.findViewById(R.id.recordhaha);
	  
	  bt1.setOnClickListener(new press(q[i]));
	  
	 
	 
	
	 
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
		AlertDialog.Builder builder=new Builder(game5Activity.this);
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
	  db.close_database();//在进行销毁的时候关闭数据库
  }
  
  
  
  
  
  private class press implements OnClickListener
  {
	 
	  private question q=new question();
	 
	  
	 
	 
	  public press(question q)
	  {
		  
		  this.q=q;
		  
		 
	  }
	
	  
	  public void onClick(View v)
	  {
		 
		  EditText t1=(EditText)findViewById(R.id.answer);	
		 
		 
		  switch(v.getId())
		  {
		  //当点击答题的时候
		  case R.id.bt1:
		  {
			  int index=game4Activity.info.size()-1;
			  int type=game4Activity.info.get(index).type;
			  
			    if(t1.getText().toString().equals("")==false)
				  {
					  if(t1.getText().toString().equals(q.get_answer()))
					  {
						 
						  Toast.makeText(game5Activity.this, "回答正确,获得道具"+goods.name[type],Toast.LENGTH_SHORT).show();	
						  //将玩家的答案收入数据库之中
					  }
					  else
					  {
						   game4Activity.info.remove(index);
						   Toast.makeText(game5Activity.this,"回答错误无法获得道具",Toast.LENGTH_SHORT).show();
					  }
							 String qi=q.get_title();
							 
							 String ui=MainActivity.temp.get_username();
							 
							 
							 String intro=q.get_intro();
							 
							 
							
							 
							 String str=t1.getText().toString();		
							 
							 connector conn=new connector(ui,qi,str,"",intro,"");			 
							 
							 if(db.get_relation_accord_to_connector(conn)==null)
								 
							 db.insert_user_question(conn);
							 
							 else db.update_student_answer(conn);
							 
							 Intent intent=new Intent(game5Activity.this,game4Activity.class);
							 game5Activity.this.startActivity(intent);
							 game5Activity.this.finish();
			  }
			else
				{
				  Toast.makeText(game5Activity.this,"回答不可为空",Toast.LENGTH_SHORT).show();
					  
				}
			  
			 
			
			
			  
		
			
			 
		  }break;
		  
		  
		  case R.id.recordhaha:
		  {
			     String qi=q.get_title();
				 String ui=MainActivity.temp.get_username();
				 String intro=q.get_intro();
				 String str=t1.getText().toString();
				 connector conn1=new connector(ui,qi,str,"",intro,"");
			  Bundle mes=new Bundle();
			  mes.putSerializable("information0", conn1);
			  Intent intent=new Intent(game5Activity.this,recordActivity.class);
			  intent.putExtras(mes);
			  game5Activity.this.startActivity(intent);
		  }break;
		 
		  }
	  }
  }
}




