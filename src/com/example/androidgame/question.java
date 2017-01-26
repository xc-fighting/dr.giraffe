package com.example.androidgame;


import java.io.*;
import java.io.Serializable;
public class question implements Serializable
{
   private int question_id;
   private String question_intro=null;
   private String question_answer=null;
   private String question_title=null;
   public question()
   {
	   
   }
   public question(String title,String intro,String answer)
   {
	   this.question_answer=answer;
	   this.question_title=title;
	   this.question_intro=intro;
   }
   public String get_title()
   {
	   return this.question_title;
   }
   public String get_intro()
   {
	   return this.question_intro;
   }
   public String get_answer()
   {
	   return this.question_answer;
   }
   public int get_id()
   {
	   return this.question_id;
   }
   public void set_id(int id)
   {
	   this.question_id=id;
   }
   public void set_title(String title)
   {
	   this.question_title=title;
   }
   public void set_quesintro(String intro)
   {
	   this.question_intro=intro;
   }
   public void set_answer(String answer)
   {
	   this.question_answer=answer;
   }
}
