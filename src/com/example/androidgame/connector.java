package com.example.androidgame;
import java.io.Serializable;
public class connector implements Serializable
{
  private String UserName;
  private String QuestionTitle;
  private String questionIntro=new String();
  private String answer;
  public String teacher_words;
  private String address;
  private int id;
  public connector()
  {
	  
  }
  public connector(String un,String qt,String ans,String word,String intro,String addr)
  {
	  this.UserName=un;
	  this.QuestionTitle=qt;
	  this.answer=ans;
	  this.teacher_words=word;
	  this.questionIntro+=intro;
	  this.address=addr;
  }
  public void set_address(String addr)
  {
	  this.address=addr;
  }
  public String get_addr()
  {
	  return this.address;
  }
  public void set_introduction(String aa)
  {
	  this.questionIntro=aa;
  }
  public void set_words(String w)
  {
	  this.teacher_words=w;
  }
  
 public  void set_username(String u)
  {
	  this.UserName=u;
  }
 public void set_QuestionId(String q)
  {
	  this.QuestionTitle=q;
  }
  public void set_id(int i)
  {
	  this.id=i;
  }
 public void set_answer(String an)
  {
	  this.answer=an;
  }
 public String get_userId()
  {
	  return this.UserName;
  }
 public String get_Qi()
  {
	  return this.QuestionTitle;
  }
 public String get_answer()
  {
	  return this.answer;
  }
 public int getId()
  {
	  return this.id;
  }
 
  public String get_words()
  {
	  return this.teacher_words;
  }
  public String get_Intro()
  {
	  return this.questionIntro;
  }
  
}
