package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
   public static ArrayList<String> recordlist=new ArrayList<String>();//����һ�����������¼������
   public void set_connection()throws Exception
   {
	   ServerSocket ss;
	
		ss = new ServerSocket(8008);
	    InetAddress addr=InetAddress.getLocalHost();
	    System.out.println(addr.toString());
	   System.out.println("�ȴ��ͻ��˽�������........");
	   while(true)
	   {
		  
		   System.out.println("---------------------------------------------");
		   Socket s=ss.accept();
		   System.out.println("��⵽�ͻ���"+s+"\n");	
			new ServerThread(s).start();
		   System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		   
	   }
   }
  
}
