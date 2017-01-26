package com.example.androidgame;
import java.io.Serializable;
public class goods implements Serializable
{
  public static int []pic={R.drawable.bang,R.drawable.cloth1,R.drawable.watch};
  public int type=-1;//0-bomb,1-armor,2-clock,index of goods
  public static String []name={"bomb","armor","clock"};
  public int value;//时钟的时延以及护甲增加的生命值
  public int flag=0;//针对破坏类道具的属性,1返回产生爆炸，0返回不产生爆炸
  public int endurance;//针对延时类道具的延时时长
  public boolean isusable;
  public int numbers=0;//商品的数量
  public goods(int type)//通过类型索引设置物体类型
  {
	  this.type=type;
  }
   public void auto_set()//通过物品的类型设置其属性
   {
	  if(type==0)this.flag=1;
	  if(type==1)this.value=56;
	  if(type==2)this.value=20;
   } 
}
