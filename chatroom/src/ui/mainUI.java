package ui;

import java.util.Scanner;

import service.MyServer;

import database.db;
import database.grade;

public class mainUI {
      public static void main(String[] args) throws Exception
      {
    	 
    	  System.out.println("---------------------------------------------------------\n");   	  
    	  System.out.println("--------------1.连接服务器--------------------------------\n");
    	  System.out.println("--------------2.清空数据库--------------------------------\n");
    	  System.out.println("--------------3.清除数据表--------------------------------\n");
    	  System.out.println("--------------4.建立数据库--------------------------------\n");
    	  System.out.println("---请输入-------------------------------------------------\n");
    	  Scanner sc=new Scanner(System.in);
    	  int choice=sc.nextInt();
	    	  switch(choice)
	    	  {
		         	  case 1:
		    	     {
		    		   new MyServer().set_connection();
		    	     }break;
		    	     case 2:
		    	    {
		    		     db db1=new db();
		    	    	 db1.deleteAll();
		    		  
		    	    }break;  
		    	     case 3:
		    	     {
		    	    	 db db1=new db();
		    	    	 db1.drop_table();
		    	     }break;
		    	     case 4:
		    	     {
		    	    	 db db1=new db();
		    	    	 db1.create_tables();
		    	     }break;
		    	     case 5:
		    	     {
		    	    	// db db1=new db();
		    	    	// db1.insert_info("xuchen","1234","teacher");
		    	    	 db db1=new db();
		    	    	 db1.delete_record();
		    	     }
	    	  }
    	}
    	  
      
}
