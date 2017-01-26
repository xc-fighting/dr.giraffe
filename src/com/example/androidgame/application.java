package com.example.androidgame;
import java.util.ArrayList;  
import java.util.List;  
import android.app.Activity;  
import android.app.Application;



public class application extends Application
{
	 private static List<Activity> mainActivity = new ArrayList<Activity>(1);
	 public static List<Activity> MainActivity() 
	 {  
	        return mainActivity;  
	 }  
	    public static void addActivity(Activity act)
	    {  
	        mainActivity.add(act);  
	    }  
	    public static void finishAll() 
	    {  
	    	for(int i=0;i<mainActivity.size();i++)
	    	{
	    		mainActivity.get(i).finish();
	    	}
	        mainActivity.removeAll(mainActivity);
	    }  
}
