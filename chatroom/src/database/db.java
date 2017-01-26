package database;
import java.sql.*;

import service.MyServer;
public class db {
	private static String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static String dbURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=db1.mdb";
    public void drop_table()
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  try {
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String drop="drop table stu";
    		String drop1="drop table user";
    		String drop2="drop table records";
    		s.executeUpdate(drop);
    		s.executeUpdate(drop1);
    		s.executeUpdate(drop2);
    		s.close();
    		conn.close();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    
    public void create_tables()
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  try {
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String create="create table stu (name char(20),shijian char(20),grade integer)";
    		String create1="create table user (name char(20),password char(20),role char(20))";
    		String create2="create table record(jilu char(40))";
    		s.executeUpdate(create);
    		s.executeUpdate(create1);
    		s.executeUpdate(create2);
    		s.close();
    		conn.close();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
    
    public void Update_infos(grade temp)
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	 String name=null;
		   
    	 Connection conn;
		try {
			conn = DriverManager.getConnection(dbURL,"","");
			Statement s=conn.createStatement();
			
				int mark=temp.grade;
				name=temp.name;
				String update="update stu"+" set grade="+mark+" where name="+"'"+name+"'";
				s.executeUpdate(update);
				
			
			conn.close();
			s.close();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
 		
    	 
    }
    
    
    public void deleteAll()
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  try {
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String del="delete * from stu";
    		
    		s.executeUpdate(del);
    		s.close();
    		conn.close();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    public boolean search(String name,String pwd,String role) throws Exception
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String query="select name from user where name="+"'"+name+"'"+" and "
    		+"password="+"'"+pwd+"'"+" and "+"role="+"'"+role+"'";
    		ResultSet result=s.executeQuery(query);
    		s.close();
    		conn.close();
    		if(result==null)return true;
    		else return false;
    		
    	
    }
    public void getallrecord()
    {
    	
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  try {
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String sel="select * from record";
    		
    		ResultSet sc=s.executeQuery(sel);
    		if(MyServer.recordlist.size()>0)MyServer.recordlist.removeAll(MyServer.recordlist);
    		while(sc.next())
    		{
    			String record=sc.getString("jilu");
    			MyServer.recordlist.add(record);
    			
    		}
    		s.close();
    		conn.close();
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    }
    public void get_all()
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  try {
    		Connection conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String sel="select * from stu";
    		
    		ResultSet sc=s.executeQuery(sel);
    		grade.stu_grade.removeAll(grade.stu_grade);
    		while(sc.next())
    		{
    			String name=sc.getString("name");
    			int g=sc.getInt("grade");
    			String d=sc.getString("shijian");
    			grade temp=new grade(g,name,d);
    			grade.stu_grade.add(temp);
    		}
    		s.close();
    		conn.close();
    	} catch (SQLException e) {
    		
    		e.printStackTrace();
    	}
    }
    
    public void insert_info(String name,String password,String role)
    {
    	try
    	{
    		Class.forName(driverName);
    		    		
    	}
    	catch(java.lang.ClassNotFoundException e)
    	{
    		e.printStackTrace();
    	}
    	Connection conn;
    	try
    	{
    		conn=DriverManager.getConnection(dbURL,"","");
    		Statement s=conn.createStatement();
    		String insert="insert into user(name,password,role) values"+"("+"'"+name+"',"+"'"+password+"',"+"'"+role+"'"+");";
    		s.execute(insert);
    		conn.close();
    		s.close();
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    }
   public void insert_record(String record)
   {
	   try
	   {
		   Class.forName(driverName);
	   }
	   catch(java.lang.ClassNotFoundException e)
	   {
		   e.printStackTrace();
	   }
	   Connection conn;
	   try
	   {
		   conn=DriverManager.getConnection(dbURL,"","");
		   Statement s=conn.createStatement();
		   String insert="insert into record (jilu) values"+"("+"'"+record+"'"+")";
		   s.executeUpdate(insert);
		   conn.close();
		   s.close();
	   }
	   catch(SQLException e)
	   {
		   e.printStackTrace();
	   }
	   
   }
   public int getnum() throws Exception
   {
	  
  		 Class.forName(driverName);
  		 
  	 
  	 
  		
  	 
  	  
  	 Connection conn;
		
			conn = DriverManager.getConnection(dbURL,"","");
			Statement s=conn.createStatement();
			
			
				String insert="select * from record";
				int count=s.executeUpdate(insert);
				
			
			conn.close();
			s.close();
			return count;
		
		
   }
    public void delete_record()
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	  
    	 Connection conn;
		try {
			conn = DriverManager.getConnection(dbURL,"","");
			Statement s=conn.createStatement();
			
			
				String insert="delete from record";
				s.executeUpdate(insert);
				
			
			conn.close();
			s.close();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    public void insert(grade temp)
    {
    	 try
    	 {
    		 Class.forName(driverName);
    		 
    	 }
    	 catch(java.lang.ClassNotFoundException e)
    	 {
    		 System.out.println(e.getMessage());
    	 }
    	 String name=null;
		 String riqi=null;   
    	 Connection conn;
		try {
			conn = DriverManager.getConnection(dbURL,"","");
			Statement s=conn.createStatement();
			
				int mark=temp.grade;
				name=temp.name;
				riqi=temp.date;
				String insert="insert into stu(name,shijian,grade)values"+"('"+name+"'"+","+"'"+riqi+"'"+","+mark+")";
				s.executeUpdate(insert);
				
			
			conn.close();
			s.close();
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
 		
    }
    
    
     
}
