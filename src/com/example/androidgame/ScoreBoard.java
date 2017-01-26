package com.example.androidgame;
import java.io.Serializable;
public class ScoreBoard implements Serializable
{
  public String name="";
  public int score;
  public ScoreBoard(String name,int score)
  {
	  this.score=score;
	  this.name+=name;
  }
  public ScoreBoard()
  {
	  
  }
 
}
