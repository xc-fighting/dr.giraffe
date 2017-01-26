package com.example.androidgame;
import java.io.Serializable;
public class user implements Serializable
{
  private String user_name;
  private String user_pwd;
  private String role;
  private String login_time;
  private String logout_time;
  private int user_id;
  public user()
  {
	  
  }
  public user(String name,String pwd,String role,String login,String logout)
  {
	  this.user_name=name;
	  this.user_pwd=pwd;
	  this.role=role;
	  this.login_time=login;
	  this.logout_time=logout;
  }
  public String get_username()
  {
	  return this.user_name;
  }
  public String get_pwd()
  {
	  return this.user_pwd;
  }
  public String get_role()
  {
	  return this.role;
  }
  public String get_logintime()
  {
	  return this.login_time;
  }
  public String get_logouttime()
  {
	  return this.logout_time;
  }
  public int get_userId()
  {
	  return this.user_id;
  }
  public void set_username(String name)
  {
	  this.user_name=name;
  }
  public void set_userId(int id)
  {
	  this.user_id=id;
  }
  public void set_userpwd(String pwd)
  {
	  this.user_pwd=pwd;
  }
  public void set_logintime(String time)
  {
	  this.login_time=time;
  }
  public void set_logouttime(String time)
  {
	  this.logout_time=time;
  }
  public void set_role(String role)
  {
	  this.role=role;
  }
  
}
