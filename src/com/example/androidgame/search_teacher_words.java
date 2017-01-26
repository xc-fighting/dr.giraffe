package com.example.androidgame;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class search_teacher_words extends Activity
{
	private dbadapter db=new dbadapter(this);
	private String stu_name=new String();
	public  connector words[];
   protected void onCreate(Bundle savedInstanceState)
   {
	   super.onCreate(savedInstanceState);
	   
	   application.addActivity(this);
	   
	   setContentView(R.layout.search_student);
	   
	   db.open_database();
	   
	   Button btn1=(Button)this.findViewById(R.id.button65);
	   
	   btn1.setOnClickListener(new pressbtn());
	   
	   stu_name+=MainActivity.temp.get_username();
	   
	    words=db.get_relation_accord_to_name(stu_name);
	   if(words!=null)
	   {
			   String []twords=new String[words.length];
			   
			   for(int i=0;i<words.length;i++)
			   {
				   twords[i]=words[i].get_words();
			   }
			   
			   ListView lv=(ListView)this.findViewById(R.id.listView45);
			   
			   ArrayAdapter<String> simple=new ArrayAdapter<String>(this,R.layout.arrayitems,twords);
				  lv.setAdapter(simple); 
				  lv.setTextFilterEnabled(true);
				  lv.setOnItemClickListener(new press());
	   }
	   else
	   {
		   Toast.makeText(search_teacher_words.this, "暂无老师对该生的评论", Toast.LENGTH_SHORT).show();
		   Intent intent=new Intent(search_teacher_words.this,option.class);
		   search_teacher_words.this.startActivity(intent);
		  
		   
		   
	   }
		  
	   
	   
	   
   }
   private class pressbtn implements OnClickListener
   {

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.button65:
		{
			
			Intent intent=new Intent(search_teacher_words.this,option.class);
			search_teacher_words.this.startActivity(intent);
			
		}break;
		}
		
	}
	   
   }
   
   private class press implements OnItemClickListener
   {

 	@Override
 	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 		
 		//String tmp=words[position].get_Qi()+"\n"+words[position].get_Intro();
 		//Toast.makeText(search_teacher_words.this, tmp, Toast.LENGTH_SHORT).show();
 		alert(words[position]);
 		
 	}
 	  
   }
   
   public void alert(connector conn)
   {
	      final Dialog  dlg=new Dialog(this);
		  dlg.show();
		  Window window=dlg.getWindow();
		  window.setContentView(R.layout.timumiaoshu);
		  Button btn=(Button)window.findViewById(R.id.btn);
		  
		  String unit=conn.get_Qi();
		  String inreo=conn.get_Intro();
		  TextView v1=(TextView)window.findViewById(R.id.describe1);v1.setText(unit);
		  TextView v2=(TextView)window.findViewById(R.id.describe2);v2.setText(inreo);
		  btn.setOnClickListener(new OnClickListener()
		  {
			  

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v.getId()==R.id.btn)
				dlg.dismiss();
			}
			  
		  });
		 
	   
	   
   }
   
   
   protected void onDestroy()
   {
	   super.onDestroy();
	   db.close_database();
   }
   
   
   public void dialog()//当用户按下返回按钮的时候显示菜单提示项
	{
		AlertDialog.Builder builder=new Builder(search_teacher_words.this);
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
	
   public boolean onKeyDown(int keyCode,KeyEvent event)
	{
		
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
		{
			
			this.dialog();
			return true;
		}
		else return false;
	}
   
   
   
   
   
   
}
