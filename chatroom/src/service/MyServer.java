package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
   public static ArrayList<String> recordlist=new ArrayList<String>();//构造一个保存聊天记录的数组
   public void set_connection()throws Exception
   {
	   ServerSocket ss;
	
		ss = new ServerSocket(8008);
	    InetAddress addr=InetAddress.getLocalHost();
	    System.out.println(addr.toString());
	   System.out.println("等待客户端进行连接........");
	   while(true)
	   {
		  
		   System.out.println("---------------------------------------------");
		   Socket s=ss.accept();
		   System.out.println("检测到客户端"+s+"\n");	
			new ServerThread(s).start();
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   
	   }
   }
  
}
