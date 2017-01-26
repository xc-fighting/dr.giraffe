
package com.example.androidgame;





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class dbadapter 
{
   private static final String db_name="game_db";
   private static final String db_table1="users_table";
   private static final String db_table2="questions_table";
   private static final String db_table3="quser_relation";
   private static final String db_table4="score_table";
   private static int db_version=1;
   private static SQLiteDatabase db;
   private static Context context;
   //userstable 主码
   private static final String key_name="username";
   private static final String key_pwd="userpassword";
   private static final String key_role="user_role";
   private static final String key_login="user_logintime";
   private static final String key_logout="user_logouttime";
   private static final String key1_others="others";
   private static final String key1_id="user_id";
   //question table主码
   private static final String key_title="question_title";
   private static final String key_intro="question_intro";
   private static final String key_answer="question_answer";
   private static final String key2_id="questions_id";
   private static final String key2_others1="others1";
   private static final String key2_others2="others2";
   private static final String key2_others3="others3";
   //question-user映射的表项
   private static final String key_userId="userid1";
   private static final String key_questionId="questionid1";
   private static final String key_id="tableid1";
   private static final String key_an="tableanswer1";
   private static final String key_word="teacherword";
   private static final String key_qI="questionintroduction";
   private static final String key_address="voice_address";
   //分数排行榜表项
   private static final String key_player="player_name";
   private static final String key_score="player_score";
   private static final String key4_id="table_id";
   private static class dbopenhelper extends SQLiteOpenHelper
   {
	 //调用父类的构造函数
		 public dbopenhelper(Context context,String name,CursorFactory factory,int version)
		 {
			 super(context,name,factory,version);
		 }
		 private static String create1="create table "+db_table1+"("+key_name+" text not null,"+key_pwd+" text not null,"
				 +key_role+" text not null,"+key_login+" text not null,"+key_logout+" text not null,"+key1_id+
				 " integer primary key autoincrement,"+key1_others+" text"+");";
		 private static String create2="create table "+db_table2+"("+key_title+" text not null,"+key_intro+" text not null," +
		 		key_answer+" text not null,"+key2_id+" integer primary key autoincrement,"+key2_others1+" text,"+key2_others2+" text,"
		 		+key2_others3+" text"+");";
		 private static String create3="create table "+db_table3+"("+key_userId+" text ,"+key_questionId+" text ,"
				 +key_id+" integer primary key autoincrement,"+key_an+" text ,"+key_word+" text ,"+key_qI+" text, "+key_address+" text"+");";
		 private static String create4="create table "+db_table4+"("+key_player+" text not null,"+key_score+" integer,"+key4_id
				 +" integer primary key autoincrement"+");";
		 public void onCreate(SQLiteDatabase _db)
		 {
			 _db.execSQL(create1);
			 _db.execSQL(create2);
			 _db.execSQL(create3);
			 _db.execSQL(create4);
			
		 }
		 public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
		 {
			 db.execSQL("DROP IF EXIST TABLE "+db_table1);
			 db.execSQL("DROP IF EXIST TABLE "+db_table2);
			 db.execSQL("DROP IF EXIST TABLE "+db_table3);
			 db.execSQL("DROP IF EXIST TABLE "+db_table4);
			 onCreate(db);
		 }
		 
   }
  
   
   
   
   
   
   
   
   public dbadapter(Context _context)
   {
	   this.context=_context;
   }
   public void open_database() throws SQLiteException//数据库打开函数
   {
  	 dbopenhelper openHelper=new dbopenhelper(context,db_name,null,db_version);
  	 try
  	 {
  		 db=openHelper.getWritableDatabase();//用继承来的openHelper打开当前的数据库
  		
  	 }
  	 catch(SQLiteException ex)
  	 {
  		 db=openHelper.getReadableDatabase();
  		
  	 }
   }
   public void close_database()//数据库关闭函数
   {
  	 if(db!=null)db.close();
  	 db=null;
  	
   }
/*-------------------------------------------------------------------------------------------------------------------------------*/
   public long insert_score(String name,int score)
   {	  
	   ContentValues value=new ContentValues();
	   value.put(key_player, name);
	   value.put(key_score, score);
	   return db.insert(db_table4, null, value);	  
   }
   public long insert_user_question(connector conn)
   {
	   ContentValues values=new ContentValues();
	  
	   values.put(key_userId,conn.get_userId());
	   values.put(key_questionId,conn.get_Qi());
	   values.put(key_an,conn.get_answer());
	   values.put(key_word, conn.get_words());
	   values.put(key_qI, conn.get_Intro());
	   values.put(key_address, conn.get_addr());
	   return db.insert(db_table3, null, values);
   }
   public long insert_user(user u)
   {
	   ContentValues values=new ContentValues();
	   values.put(key_name, u.get_username());
	   values.put(key_pwd, u.get_pwd());
	   values.put(key_role, u.get_role());
	   values.put(key_login, u.get_logintime());
	   values.put(key_logout, u.get_logouttime());
	  return  db.insert(db_table1, null, values);
	   
   }
   public long insert_question(question q)//向数据表中加入问题
   {
	   ContentValues values=new ContentValues();
	   values.put(key_title, q.get_title());
	   values.put(key_intro, q.get_intro());
	   values.put(key_answer, q.get_answer());
	   return db.insert(db_table2, null, values);
   }
/*---------------------------------------------------------------------------------------------------------------------------*/
   public long update_score(long id,String name,int score)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_player, name);
	   update.put(key_score,score);
	   return db.update(db_table4, update, key4_id+"="+id,null);
   }
   public long update_score(String name,int score)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_player, name);
	   update.put(key_score,score);
	   return db.update(db_table4, update, key_player+"="+"'"+name+"'", null);
	   
   }
   
   public long update_user_logintime(String login,String name)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_login,login);
	   return db.update(db_table1, update, key_name+"="+"'"+name+"'",null);
   }
   
   public long update_user_logout_time(String logout,String name)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_logout,logout);
	   return db.update(db_table1, update, key_name+"="+"'"+name+"'",null);
   }
   
   
   
   public long update_user(long id,user u)//更新用户信息
   {
	   ContentValues update=new ContentValues();
	   update.put(key_name, u.get_username());
	   update.put(key_pwd,u.get_pwd());
	   update.put(key_role, u.get_role());
	   update.put(key_login, u.get_logintime());
	   update.put(key_logout, u.get_logouttime());
	   return db.update(db_table1, update, key1_id+"="+id, null);
   }
   public long update_user_accordto_name(user u)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_name, u.get_username());
	   update.put(key_pwd, u.get_pwd());
	   update.put(key_role, u.get_role());
	   update.put(key_login, u.get_logintime());
	   update.put(key_logout, u.get_logouttime());
	   
	   return db.update(db_table1, update, key_name+"="+"'"+u.get_username()+"'",null);
   }
   public long update_quesiton(long id,question q)//更新数据表中的某个问题
   {
	   ContentValues updateValues = new ContentValues();	  
		updateValues.put(key_title, q.get_title());
		updateValues.put(key_intro,q.get_intro());
		updateValues.put(key_answer, q.get_answer());
		return db.update(db_table2, updateValues,  key2_id + "=" + id, null);
   }
   public long update_question_accordto_title(question q)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_title, q.get_title());
	   update.put(key_intro, q.get_intro());
	   update.put(key_answer, q.get_answer());
	   return db.update(db_table2, update, key_title+"="+"'"+q.get_title()+"'", null);
   }
   
   public long update_student_answer(connector t)
   {
	   ContentValues update=new ContentValues();
	   update.put(key_userId, t.get_userId());
	   update.put(key_questionId, t.get_Qi());
	   update.put(key_an, t.get_answer());
	   update.put(key_word,t.get_words());
	   update.put(key_qI,t.get_Intro());
	   update.put(key_address, t.get_addr());
	   return db.update(db_table3, update,
			   key_userId+"="+"'"+t.get_userId()+"'"+" and "+key_questionId+"="+"'"+t.get_Qi()+"'"+" and "+key_qI+"="+
	   "'"+t.get_Intro()+"'", null);
   }
   
   /*-----------------------------------------------------------------------------------------------------------------------*/
   public long delete_score(long id)
   {
	   return db.delete(db_table4, key4_id+"="+id, null);
   }
   public long deleteOneUser(long id)
   {
	   return db.delete(db_table1, key1_id+"="+id, null);
   }
   public long deleteAllUsers()
   {
	   return db.delete(db_table1, null, null);
   }
   public long deleteOneQuestion(long id)//根据id删除数据表中的一个问题
   {
	   return db.delete(db_table2, key2_id+ "=" + id, null);
   }
   public long deleteAllData() //删除所有的问题
   {
		return db.delete(db_table2, null, null);
  }
   public long deleteAllScore()
   {
	   return db.delete(db_table4, null, null);
   }
   public long deleteAllanswers()
   {
	   return db.delete(db_table3, null, null);
   }
   /*------------------------------------------------------------------------------------------------------------------------*/
   private ScoreBoard[] ConvertToScore(Cursor cursor)
   {
	   int result=cursor.getCount();
	   if(result==0||!cursor.moveToFirst())
	   {
		   return null;
	   }
	   ScoreBoard[] score=new ScoreBoard[result];
	   for(int i=0;i<result;i++)
	   {
		   score[i]=new ScoreBoard();
		   score[i].score=cursor.getInt(cursor.getColumnIndex(key_score));
		   score[i].name=cursor.getString(cursor.getColumnIndex(key_player));
		   cursor.moveToNext();
	   }
	   return score;
   }
   private connector[] ConvertToConnectors(Cursor cursor)
   {
	   int result=cursor.getCount();
	   if(result==0||!cursor.moveToFirst())
	   {
		   return null;
	   }
	   connector[] conn=new connector[result];
	   for(int i=0;i<result;i++)
	   {
		   conn[i]=new connector();
		   conn[i].set_answer(cursor.getString(cursor.getColumnIndex(key_an)));
		   conn[i].set_id(cursor.getInt(cursor.getColumnIndex(key_id)));
		   conn[i].set_QuestionId(cursor.getString(cursor.getColumnIndex(key_questionId)));
		   conn[i].set_username(cursor.getString(cursor.getColumnIndex(key_userId)));
		   conn[i].set_words(cursor.getString(cursor.getColumnIndex(key_word)));
		   conn[i].set_introduction(cursor.getString(cursor.getColumnIndex(key_qI)));
		   conn[i].set_address(cursor.getString(cursor.getColumnIndex(key_address)));
		 
		   cursor.moveToNext();
	   }
	   return conn;
   }
   private question[] ConvertToquestions(Cursor cursor){
		int resultCounts = cursor.getCount();
		if (resultCounts == 0 || !cursor.moveToFirst()){
		            return null;
		}
		question[] questions = new question[resultCounts];
		//cursor.moveToFirst();
		for (int i = 0 ; i<resultCounts; i++){
		            questions[i] = new question();
		            questions[i].set_id(cursor.getInt(cursor.getColumnIndex(key2_id)));
		            questions[i].set_title(cursor.getString(cursor.getColumnIndex(key_title)));
		            questions[i].set_quesintro(cursor.getString(cursor.getColumnIndex(key_intro)));
		            questions[i].set_answer(cursor.getString(cursor.getColumnIndex(key_answer)));
		            cursor.moveToNext();
		}
		return questions; 		
	}
   private user[] ConvertTousers(Cursor cursor)
   {
	   int resultcounts=cursor.getCount();
	   if(resultcounts==0||!cursor.moveToFirst())
		   return null;
	   user[] users=new user[resultcounts];
	   for(int i=0;i<resultcounts;i++)
	   {
		   users[i]=new user();
		   users[i].set_username(cursor.getString(cursor.getColumnIndex(key_name)));
		   users[i].set_userpwd(cursor.getString(cursor.getColumnIndex(key_pwd)));
		   users[i].set_logintime(cursor.getString(cursor.getColumnIndex(key_login)));
		   users[i].set_logouttime(cursor.getString(cursor.getColumnIndex(key_logout)));
		   users[i].set_role(cursor.getString(cursor.getColumnIndex(key_role)));
		   users[i].set_userId(cursor.getInt(cursor.getColumnIndex(key1_id)));
		   cursor.moveToNext();
	   }
	   return users;
   }
   public user[] getOneUser(long id)
   {
	   Cursor cursor;
	   cursor=db.query(db_table1,new String[]{key_name,key_pwd,key_role,key_login,key_logout,key1_id},key1_id+"="+id,null,null,null,null);
	   return this.ConvertTousers(cursor);
   }
   public user[] getAllUsers()
   {
	   Cursor cursor;
	   cursor=db.query(db_table1,new String[]{key_name,key_pwd,key_role,key_login,key_logout,key1_id},null,null,null,null,null);
	   return this.ConvertTousers(cursor);
   }
   public user[] getUsers_accord_to_name(String name)
   {
	   Cursor cursor;
	   cursor=db.query(db_table1,new String[]{key_name,key_pwd,key_role,key_login,key_logout,key1_id},key_name+"="+"'"+name+"'",null,null,null,null);
	   return this.ConvertTousers(cursor);
   }
   public String convert_to_string(Cursor cursor)
   {
	   if(cursor.getCount()==1&&cursor.moveToFirst())
	   {
		   return cursor.getString(cursor.getColumnIndex(key_address));
		   
	   }
	   else
	   {
		   return null;
	   }
   }
   boolean  getOneUser(String str1,String str2)
   {
	   Cursor cursor;
	   cursor=db.query(db_table1,new String[]{key_name,key_pwd,key_role,key_login,key_logout,key1_id},key_name+"="+"'"+str1+"'"+" and "+key_pwd+"="+"'"+str2+"'",null,null,null,null);
	  
	   if(cursor.getCount()==0){return false;}
	   else return true;
	  
   }
   
   public question[] getOnequestion(long id)
   {
	   Cursor cursor;
	   cursor=db.query(db_table2,new String[]{key_title,key_intro,key_answer,key2_id},key2_id+"="+id,null,null,null,null);
	   return this.ConvertToquestions(cursor);
   }
   public question[] getallquestion()
   {
	   Cursor cursor;
	   cursor=db.query(db_table2,new String[]{key_title,key_intro,key_answer,key2_id},null,null,null,null,null);
	   return this.ConvertToquestions(cursor);
   }
   public question[] getquestion_accord_to_title(String title)
   {
	   Cursor cursor;
	   cursor=db.query(db_table2,new String[]{key_title,key_intro,key_answer,key2_id},key_title+"="+"'"+title+"'",null,null,null,null);
	   return this.ConvertToquestions(cursor);
   }
   public connector[] getallrelations()
   {
	   Cursor cursor;
	   cursor=db.query(db_table3,new String[]{key_userId,key_questionId,key_an,key_id,key_word,key_qI,key_address},null,null,null,null,null);
	   return this.ConvertToConnectors(cursor);
   }
   public connector[] get_relation_accord_to_connector(connector t)
   {
	   Cursor cursor;
	   cursor=db.query(db_table3,new String[]{key_userId,key_questionId,key_an,key_id,key_word,key_qI,key_address},key_userId+"="+"'"+t.get_userId()+"'"+" and "
			   +key_questionId+"="+"'"+t.get_Qi()+"'",null,null,null,null);
	   return this.ConvertToConnectors(cursor);
   }
   
   public connector[] get_relation_accord_to_name(String name)
   {
	   Cursor cursor;
	   cursor=db.query(db_table3,new String[]{key_userId,key_questionId,key_an,key_id,key_word,key_qI,key_address},key_userId+"="+"'"+name+"'",null,null,null,null);
	   return this.ConvertToConnectors(cursor);
   }
   
   public String get_address(String a,String b,String c)
   {
	   Cursor cursor;
	  
	   cursor=db.query(db_table3,new String[]{key_userId,key_questionId,key_qI,key_address},
			   key_userId+"="+"'"+a+"'"+" and "+key_questionId+"="+"'"+b+"'"+" and "+key_qI+"="+"'"+c+"'"
			   ,null,null,null,null);
	return this.convert_to_string(cursor);
	
   }
   
   
   public ScoreBoard[] getallscore()
   {
	    Cursor cursor;
	    cursor=db.query(db_table4, new String[]{key_player,key_score,key4_id}, null, null, null, null,null);
	    return this.ConvertToScore(cursor);
	    
   }
   
   
   
   
}
