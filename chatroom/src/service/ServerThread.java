package service;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


import database.db;
import database.grade;

public class ServerThread extends Thread{

    public  boolean flag=true;
    public  boolean isget=false;
    public String yanzheng="";
	Socket s=null;
	InputStream din=null;
	OutputStream dout=null;
	db db1=new db();
	public ServerThread(Socket s)throws IOException
	{
		this.s=s;
	    din=s.getInputStream();
		dout=s.getOutputStream();
	}
	public void run()  
	{
		
		try 
		{
		
			
			byte []buffer=new byte[1024];
			int index=din.read(buffer);
			String receive=new String(buffer,0,index);	
			if(receive.equals("get"))
				{
			//	db db1=new db();
				//db1.insert_record(receive);
			//	if(db1.getnum()==10)db1.delete_record();
				this.isget=true;
				}
		    if(receive.equals("get_grades"))this.flag=false;
		    if(receive.contains("~"))
		    {
		    	System.out.print(receive+"\n");
		    	String []b=new String[3];
		    	b=receive.split("~");
		    	String name=b[0];
		    	System.out.print(name+"\n");
		    	String password=b[1];
		    	String role=b[2];
		    	if(db1.search(name, password, role)==false)
		    		{
		    		db1.insert_info(name, password, role);
		    		yanzheng="yes";
		    		}
		    	else
		    	{
		    		yanzheng="no";
		    	}
		    	
		    	
		    }
			if(receive.contains("-"))
			{
				 String []a=receive.split("-");
					 String name=a[0];
					 int mark=Integer.parseInt(a[1]);
					 String d=a[2];
				  grade tmp=new grade(mark,name,d);
				  db1.insert(tmp);
				   this.flag=false;
			}
			System.out.println("从客户端接收的信息为"+receive);
			String send="";		
			if(flag==true&&isget==false&&yanzheng.equals(""))
			//if(flag==true)
			{
			MyServer.recordlist.add(receive);
				for(int i=0;i<MyServer.recordlist.size();i++)
				{
				
					send+=MyServer.recordlist.get(i)+"\n";
				}
			//	db db1=new db();
				//db1.insert_record(receive);
			}
			if(flag==false&&isget==false&&yanzheng.equals(""))
			//if(flag==false)
			{
				db db1=new db();
				db1.get_all();
				grade.sort_down();
				
				for(int i=0;i<grade.stu_grade.size();i++)
				{
					send+=grade.stu_grade.get(i).name+"-"+grade.stu_grade.get(i).grade+
					"-"+grade.stu_grade.get(i).date+",";
				}
				flag=true;
			}
			if(isget==true&&yanzheng.equals(""))
			{
				//db db1=new db();
				//db1.getallrecord();
				if(MyServer.recordlist.size()>0)
				{
					for(int i=0;i<MyServer.recordlist.size();i++)
					{
					
						send+=MyServer.recordlist.get(i)+"\n";
					}
				}
				isget=false;
			}
			if(yanzheng!=null)
			{
				send+=yanzheng;
				yanzheng=null;
			}
				System.out.println("发送到客户信息是"+send);			
				dout.write(send.getBytes());
			    if(MyServer.recordlist.size()>=20)MyServer.recordlist.removeAll(MyServer.recordlist);
				din.close();
				dout.close();
			    this.s.close();			
			System.out.println("客户端线程完成\n");
			
		
			
	}
		catch (IOException e)
        {
			
			e.printStackTrace();
			System.out.println("链路上读写出现错误\n");
			try {
				din.close();
				dout.close();
		        s.close();
		        System.out.println("套接字已经关闭\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("线程出现错误\n");
			try {
				din.close();
				dout.close();
		        s.close();
		        System.out.println("套接字已经关闭\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
			
		
		
		
		
	}
	

}
