package com.example.androidgame;
import java.io.Serializable;
public class goods implements Serializable
{
  public static int []pic={R.drawable.bang,R.drawable.cloth1,R.drawable.watch};
  public int type=-1;//0-bomb,1-armor,2-clock,index of goods
  public static String []name={"bomb","armor","clock"};
  public int value;//ʱ�ӵ�ʱ���Լ��������ӵ�����ֵ
  public int flag=0;//����ƻ�����ߵ�����,1���ز�����ը��0���ز�������ը
  public int endurance;//�����ʱ����ߵ���ʱʱ��
  public boolean isusable;
  public int numbers=0;//��Ʒ������
  public goods(int type)//ͨ����������������������
  {
	  this.type=type;
  }
   public void auto_set()//ͨ����Ʒ����������������
   {
	  if(type==0)this.flag=1;
	  if(type==1)this.value=56;
	  if(type==2)this.value=20;
   } 
}
