package database;

import java.util.ArrayList;

public class grade {
   public static ArrayList<grade> stu_grade=new ArrayList<grade>();
   public int grade;
   public String name;
   public String date;
   public grade(int grade,String name,String d)
   {
	   this.name=name;
	   this.grade=grade;
	   this.date=d;
   }
   public static void insert_grade(grade tmp)
   {
	   stu_grade.add(tmp);
   }
   public static boolean deleteOne(int index)
   {
	   if(index<=stu_grade.size()-1)
	   {
		 stu_grade.remove(index);
		 return true;
	   }
	   else
	   {
		   return false;
	   }
   }
   public static void deleteAll()
   {
	   stu_grade.removeAll(stu_grade);
   }
   public static grade getOne(int index)
   {
	   if(index<=stu_grade.size()-1)return stu_grade.get(index);
	   else
	   return null;
   }
   public static void sort_up()
   {
	   grade tmp=null;
	   for(int i=stu_grade.size()-1;i>0;i--)
	   {
		   for(int j=0;j<i;j++)
		   {
			   if(stu_grade.get(j).grade>stu_grade.get(j+1).grade)
			   {
				   tmp=stu_grade.get(j);
				   stu_grade.set(j, stu_grade.get(j+1));
				   stu_grade.set(j+1, tmp);
			   }
		   }
	   }
   }
   public static void sort_down()
   {
	   grade tmp=null;
	   for(int i=stu_grade.size()-1;i>0;i--)
	   {
		   for(int j=0;j<i;j++)
		   {
			   if(stu_grade.get(j).grade<=stu_grade.get(j+1).grade)
			   {
				   tmp=stu_grade.get(j);
				   stu_grade.set(j, stu_grade.get(j+1));
				   stu_grade.set(j+1, tmp);
			   }
		   }
	   }
   }
   
}




