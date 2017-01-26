package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class receiveThread extends Thread{
	
	private Socket s=null;
	private InputStream din=null;
	private OutputStream dout=null;
	public receiveThread(Socket ss)
	{
		this.s=ss;
	}
   public void run()
   {
	   
	   while(true)
	   {
		   
		  try {
			din=s.getInputStream();
			dout=s.getOutputStream();
			if(MyServer.recordlist.size()>0)
			{
				String send="";
				for(int i=0;i<MyServer.recordlist.size();i++)
					{
						
							send+=MyServer.recordlist.get(i)+"\n";
					}
				dout.write(send.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			break;
		}
		 
		  
	   }
	   
   }
}
