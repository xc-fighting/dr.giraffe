package com.example.androidgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;


import java.io.InputStream;

import java.util.ArrayList;
import java.util.Random;
public class gamerule
{
  
  
   public ArrayList<goods> shops=new ArrayList<goods>(3);//最多能够保存3件道具的list
   private Avoider avoider;//引用游戏者
   private ArrayList<Faller> faller=new ArrayList<Faller>(6);//引用掉落物
 
   private mysurface msv;
   private Canvas c;
   
   private int score;//游戏角色的分数，初始值为0,默认吃到苹果的时候分数加1
   private int life;//游戏角色的生命值，默认为10
   public int time;//游戏的剩余时间,默认当不加延时道具时候时间为60s
   private int peroid;
   private Bitmap bmp1=null;  //背景图片的引用
   private Bitmap bang;//爆炸效果的图片
   private Bitmap dingdong;//吃到苹果的图片
   
   Context context;
   
   private boolean LongNeckFlag;//设置是否显示长脖子的标志

   public int scwidth;
   public int scheight;
   
   
   //逻辑层
   
   public int gamelogic_num()//产生掉落物的数量逻辑
   {
	   int faller_num=0;
	   if(time>60){faller_num=this.get_random(1, 4);}//大于60s的时候产生1-3个敌人
	   if(time<=60&&time>=30){faller_num=this.get_random(1, 5);}
	   if(time<30){faller_num=6;}
	   return faller_num;
   }
   public int gamelogic_size()
   {
	   int num=0;
	   if(time>60){num=2;}//大于60s的时候产生1-3个敌人
	   if(time<=60&&time>=30){num=4;}
	   if(time<30){num=6;}
	   return num;
   }
   public int gamelogic_speed()//每个掉落物的速度产生逻辑
   {
	   int speed=0;
	   if(time>60)speed=this.get_random(2, 4);
	   if(time<=60&&time>=45)speed=this.get_random(4, 6);
	   if(time<45&&time>=30)speed=this.get_random(6, 9);
	   if(time<30&&time>=15)speed=this.get_random(5,14);
	   if(time<15&&time>=10)speed=this.get_random(7,15);
	   if(time<10&&time>=0)speed=this.get_random(8, 19);
	   return speed;
   }
   
   
   public boolean continue_generate(int num)//判断是否继续产生游戏对象的函数
   {
	   if(faller.size()<num)return true;
	   else return false;
   }
   
   
  
   public void add_sec()//开始倒计时方法
   {
	   if(peroid<=50)
	  
	   peroid++;
	   else {peroid=0;time--;}
	   
   }
   
   
   
   public void draw_end()
   {
	   int width=gamerunActivity.screen_width;
	   int height=gamerunActivity.screen_height;
	   Rect rect=new Rect(0,0,1280*(width/480),1280*(height/480));
	   Resources res;
		res=msv.getResources();
		InputStream is = res.openRawResource(R.drawable.end1);// 将获取的图片传给输入流
		BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// 利用输入流和资源对象来创建位图绘制类
		Bitmap bmp1 = bmpDraw.getBitmap();// 通过位图绘制类来绘制位图
		c.drawBitmap(bmp1,null,rect,null);
   }

   
   
   public gamerule(mysurface msv,Canvas cc,Context context)
   {
	     this.msv=msv;
	     this.c=cc;
	     this.score=0;
	     this.time=70;
	     this.life=100;
	     this.peroid=0;
	     
	     this.scheight=gamerunActivity.screen_height;
	     this.scwidth=gamerunActivity.screen_width;
	     
	     
	     this.context=context;	
	     Resources res=msv.getResources();
	     /*------------------------------------------------------------------------------------------------*/
		 InputStream is = res.openRawResource(R.drawable.back);
		 BitmapDrawable bmpDraw = new BitmapDrawable(res, is);
		 bmp1 = bmpDraw.getBitmap();	 
		 /*------------------------------------------------------------------------------------------------*/
		 bang=BitmapFactory.decodeResource(msv.getResources(), R.drawable.explode);
		InputStream is1=res.openRawResource(R.drawable.goodthing);
		 BitmapDrawable bmp3=new BitmapDrawable(res,is1);
		 dingdong=bmp3.getBitmap();	     
   }
   
   
   
   public void set_surface(mysurface m)//设置surfaceview组件
   {
	   this.msv=m;
   }
   public void set_canvas(Canvas s)//设置画布对象
   {
	   this.c=s;
   }
 
   
   
   
   public void generate_track()//产生背景函数
   {	
	   Rect rect=new Rect(0,0,scwidth,scheight);
		c.drawBitmap(bmp1,null,rect, null);
		c.translate(0, 0);// 设置画布的原点，x轴以水平向右为正方向，y轴以竖直向下为正方向			
   }
   
   
   
   
   
   
   public void generate_enemy()//产生掉落物方法,6个跑道分别产生
   {
	   int num=0;
	   int total=this.gamelogic_num();
	   
	  while(num<=total)
	  {
	  int tn=this.get_random(0, 6);
	  float x=4+tn*(scwidth/6);
	  float y=this.get_random(0, 144);	   
	  int speed=this.gamelogic_speed(); 
	  int type=this.get_random(0, 3);
	  Faller temp=new Faller(msv,c,x,y,speed,tn,type,false);
	  faller.add(temp);	  
	  num++;
	
	  
	  }
   }
   
   
   
   
   
   public void generate_goods()//产生商品,通过商品信息的list产生
   {
			  int size=game4Activity.info.size();
			  int type;
			 if(size>0)
			 {
				  for(int i=0;i<size;i++)
				  {
					 type=game4Activity.info.get(i).type;
				     goods shopping=new goods(type);
				     shopping.auto_set();//根据商品类型设置商品的属性
				     shops.add(shopping);//向当前的商品列表进行添加
				     
	              }
				  game4Activity.info.removeAll(game4Activity.info);
				 
			 }
			 
	   
   }
   
   
   
 
   public boolean is_goods_left()//判断是否还有商品
   {
	   if(this.shops.size()>0)return true;
	   else return false;
   }
   
   
   
   public void drawshopping()//在画布上面画商品
   {
	   if(this.is_goods_left()==true)
	   {
	    int type=shops.get(shops.size()-1).type;
	    if(type!=-1)
	    {
	   Resources res=msv.getResources();
	   InputStream is = res.openRawResource(goods.pic[type]);// 将获取的图片传给输入流
	   BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// 利用输入流和资源对象来创建位图绘制类
		Bitmap bmp1 = bmpDraw.getBitmap();// 通过位图绘制类来绘制位图
	   c.drawBitmap(bmp1, 300, 0, null);
	    }
	   
	   }
	   
	   
   }
   
   public void listen_goods()
   {
	 if(this.is_goods_left()==true)
	  {
		   int index=shops.size()-1;
		   int type=shops.get(index).type;
	    switch(type)
	    {
	    	case 0:
	    	{
	    		for(int i=0;i<faller.size();i++)
	    		{
	    			float xx=faller.get(i).getX();
	    			float yy=faller.get(i).getY();    			
	    			explosion e=new explosion(msv,bang,xx,yy);
	    			e.drawself(c);
	    		}
	    		
	    		faller.removeAll(faller);
	    		
	    	}break;
	    	case 1:
	    	{
	    		int lifeadd=shops.get(index).value;
	    		this.set_life(lifeadd);
	    	}break;
	    	case 2:
	    	{
	    		int timeadd=shops.get(index).value;
	    		this.set_time(timeadd);
	    	}break;
	    }
	    shops.remove(index);
	   }
   }
   /*-----------------------------------------------------------------------------------------------------*/
   public Canvas get_canvas()//获取画布对象
   {
	   return this.c;
   }
   public mysurface get_surface()//获取surfaceview组件
   {
	   return this.msv;
   }
   public void generate_player(int num)//产生玩家方法
   {
	   this.avoider=new Avoider();
	   avoider.setcanvas(c);
	   avoider.setsurface(msv);
	   avoider.setTracknumber(num);
	   int x=4+(scwidth/6)*num;
	   avoider.setx(x);
	   
	   
	   
	   avoider.setImagelength(72*scwidth/480);
		if (LongNeckFlag) {
			avoider.setImagewidth(144*scwidth/480);
			avoider.sety(scheight-avoider.getImagewidth());
		} else {
			avoider.setImagewidth(72*scwidth/480);
			avoider.sety(scheight-avoider.getImagewidth());
		} 
	  /* avoider.setImagelength(72);
		if (LongNeckFlag) {
			avoider.setImagewidth(144);
			avoider.sety(gamerunActivity.screen_height-144);
		} else {
			avoider.setImagewidth(72);
			avoider.sety(gamerunActivity.screen_height-72);
		} */
	   
	   
	   
   }
   public int get_random(int min,int max)//产生随机数在min到max之间随机数方法
   {
	   Random j=new Random();
	   int number;
	   do
	   {
	   number=j.nextInt(max);
	   }while(number<min);
	   return number;
	   
   }

   public Avoider get_player()//获取玩家
   {
	   return this.avoider;
   }
   public ArrayList<Faller> get_faller()//获取掉落物列表
   {
	   return this.faller;
   }
   public int get_score()//获取分数
   {
	   return this.score;
   }
   public int get_life()//获取当前生命值
   {
	   return this.life;
   }
   public int get_time()//获取当前时间
   {
	   return this.time;
   }

   
   
   public void setLongneckflag(boolean lnf) {
		LongNeckFlag = lnf;
	}
   
   public void set_score(int s)//设置当前分数
   {
	   this.score+=s;
   }
   public void set_time(int l)//设置当前时间
   {
	   this.time+=l;
   }
   public void set_life(int l)//设置剩余生命
   {
	   this.life+=l;
   }

   public boolean isgameover()//判断游戏是否结束
   {
	   Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextSize(25f);
	   if(this.life<=0||this.time<=0)
		   
	   {
		 //  int width=gamerunActivity.screen_width;
		 //  int height=gamerunActivity.screen_height;
		 //  Rect rect=new Rect(0,0,1280*(width/480),1280*(height/480));
		   
		   
		   
		   c.drawText("game over", 0, 200, paint);return true;
		   
	   }
	   else return false;
   }

   public void draw_title()
   {
	   Paint p1=new Paint();
	   p1.setTextSize(20f);
	   p1.setStyle(Paint.Style.FILL);
	   p1.setColor(Color.YELLOW);
	   String text=""+this.life;
	   c.drawText("玩家生命值:"+text, 0, 20, p1);
	   Paint p2=new Paint();
	   p2.setTextSize(20f);
	   p2.setStyle(Paint.Style.FILL);
	   p2.setColor(Color.RED);
	  text=""+this.time;
	   c.drawText("剩余的时间:"+text, 150, 20, p2);
	   text=""+this.score;
	   Paint p3=new Paint();
	   p3.setColor(Color.GREEN);
	   p3.setTextSize(20f);
	   p3.setStyle(Style.FILL);
	   c.drawText("玩家分数"+text, 0,60 , p3);
	   
   }
   public void draw(int num)
   {
	 
	  this.generate_track();//画跑道
       this.generate_player(num);//画玩家
	   avoider.draw();
	   for(int j=0;j<faller.size();j++)
	   {
		   if(this.crush(j)||this.listen_over_the_border(j))continue;
	   }
	   for(int i=0;i<faller.size();i++)
	   {   
		  
		   float speed=faller.get(i).getSpeed();
		   float x=faller.get(i).getX();
		   float y=faller.get(i).getY();
		   int n=faller.get(i).getTracknumber();
		   int type=faller.get(i).getType();
		   Faller a=new Faller(msv,c,x,y,speed,n,type,false);
		   a.draw();	 
		   
	   
	   }
	   
	  
   }
   
   
 
   
   
   
   

   public void moveall()//将所有的faller进行移动
   {
	   int n=this.faller.size();
	   for(int i=0;i<n;i++)
	   {
		   faller.get(i).move();
	   }
   }
   public void set_track(int n)//设置轨道号
   {
	   avoider.setTracknumber(n);
   }
   public int get_track()//获取轨道号
   {
	   return avoider.getTracknumber();
   }
   public boolean listen_over_the_border(int i)//监听掉落物是否出了画布
   {
	   
		   if(faller.get(i).getY()>=gamerunActivity.screen_height)
		   {faller.remove(i);return true;}
		   else return false;
		   
	   
   }
   public boolean crush(int i)//碰撞组件
   {
	  
		   if(faller.get(i).getTracknumber()==avoider.getTracknumber())
		   {
			   if(faller.get(i).getY()+72>=this.avoider.getY())
			   {
				   explosion aoh=null;
				   
				   int t=faller.get(i).getType();
				   if(t==0)this.set_score(1);
				   int hurt=faller.get(i).function;
				   this.set_life(hurt);
				  
				   float bx=faller.get(i).getX();
				   float by=faller.get(i).getY();
				   int tt=faller.get(i).getType();
				   if(tt==1||tt==2)
				   {
				   aoh=new explosion(msv,bang,bx,by);
				   }
				   else if(tt==0)aoh=new explosion(msv,dingdong,bx,by);
				   faller.remove(i);
				   
				   aoh.drawself(c);
				   
				   return true;
				   
			   }
			   else return false;
		   }
		   else return false;	   
   }
  
   
}
