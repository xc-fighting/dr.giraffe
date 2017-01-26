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
  
  
   public ArrayList<goods> shops=new ArrayList<goods>(3);//����ܹ�����3�����ߵ�list
   private Avoider avoider;//������Ϸ��
   private ArrayList<Faller> faller=new ArrayList<Faller>(6);//���õ�����
 
   private mysurface msv;
   private Canvas c;
   
   private int score;//��Ϸ��ɫ�ķ�������ʼֵΪ0,Ĭ�ϳԵ�ƻ����ʱ�������1
   private int life;//��Ϸ��ɫ������ֵ��Ĭ��Ϊ10
   public int time;//��Ϸ��ʣ��ʱ��,Ĭ�ϵ�������ʱ����ʱ��ʱ��Ϊ60s
   private int peroid;
   private Bitmap bmp1=null;  //����ͼƬ������
   private Bitmap bang;//��ըЧ����ͼƬ
   private Bitmap dingdong;//�Ե�ƻ����ͼƬ
   
   Context context;
   
   private boolean LongNeckFlag;//�����Ƿ���ʾ�����ӵı�־

   public int scwidth;
   public int scheight;
   
   
   //�߼���
   
   public int gamelogic_num()//����������������߼�
   {
	   int faller_num=0;
	   if(time>60){faller_num=this.get_random(1, 4);}//����60s��ʱ�����1-3������
	   if(time<=60&&time>=30){faller_num=this.get_random(1, 5);}
	   if(time<30){faller_num=6;}
	   return faller_num;
   }
   public int gamelogic_size()
   {
	   int num=0;
	   if(time>60){num=2;}//����60s��ʱ�����1-3������
	   if(time<=60&&time>=30){num=4;}
	   if(time<30){num=6;}
	   return num;
   }
   public int gamelogic_speed()//ÿ����������ٶȲ����߼�
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
   
   
   public boolean continue_generate(int num)//�ж��Ƿ����������Ϸ����ĺ���
   {
	   if(faller.size()<num)return true;
	   else return false;
   }
   
   
  
   public void add_sec()//��ʼ����ʱ����
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
		InputStream is = res.openRawResource(R.drawable.end1);// ����ȡ��ͼƬ����������
		BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// ��������������Դ����������λͼ������
		Bitmap bmp1 = bmpDraw.getBitmap();// ͨ��λͼ������������λͼ
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
   
   
   
   public void set_surface(mysurface m)//����surfaceview���
   {
	   this.msv=m;
   }
   public void set_canvas(Canvas s)//���û�������
   {
	   this.c=s;
   }
 
   
   
   
   public void generate_track()//������������
   {	
	   Rect rect=new Rect(0,0,scwidth,scheight);
		c.drawBitmap(bmp1,null,rect, null);
		c.translate(0, 0);// ���û�����ԭ�㣬x����ˮƽ����Ϊ������y������ֱ����Ϊ������			
   }
   
   
   
   
   
   
   public void generate_enemy()//���������﷽��,6���ܵ��ֱ����
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
   
   
   
   
   
   public void generate_goods()//������Ʒ,ͨ����Ʒ��Ϣ��list����
   {
			  int size=game4Activity.info.size();
			  int type;
			 if(size>0)
			 {
				  for(int i=0;i<size;i++)
				  {
					 type=game4Activity.info.get(i).type;
				     goods shopping=new goods(type);
				     shopping.auto_set();//������Ʒ����������Ʒ������
				     shops.add(shopping);//��ǰ����Ʒ�б�������
				     
	              }
				  game4Activity.info.removeAll(game4Activity.info);
				 
			 }
			 
	   
   }
   
   
   
 
   public boolean is_goods_left()//�ж��Ƿ�����Ʒ
   {
	   if(this.shops.size()>0)return true;
	   else return false;
   }
   
   
   
   public void drawshopping()//�ڻ������滭��Ʒ
   {
	   if(this.is_goods_left()==true)
	   {
	    int type=shops.get(shops.size()-1).type;
	    if(type!=-1)
	    {
	   Resources res=msv.getResources();
	   InputStream is = res.openRawResource(goods.pic[type]);// ����ȡ��ͼƬ����������
	   BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// ��������������Դ����������λͼ������
		Bitmap bmp1 = bmpDraw.getBitmap();// ͨ��λͼ������������λͼ
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
   public Canvas get_canvas()//��ȡ��������
   {
	   return this.c;
   }
   public mysurface get_surface()//��ȡsurfaceview���
   {
	   return this.msv;
   }
   public void generate_player(int num)//������ҷ���
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
   public int get_random(int min,int max)//�����������min��max֮�����������
   {
	   Random j=new Random();
	   int number;
	   do
	   {
	   number=j.nextInt(max);
	   }while(number<min);
	   return number;
	   
   }

   public Avoider get_player()//��ȡ���
   {
	   return this.avoider;
   }
   public ArrayList<Faller> get_faller()//��ȡ�������б�
   {
	   return this.faller;
   }
   public int get_score()//��ȡ����
   {
	   return this.score;
   }
   public int get_life()//��ȡ��ǰ����ֵ
   {
	   return this.life;
   }
   public int get_time()//��ȡ��ǰʱ��
   {
	   return this.time;
   }

   
   
   public void setLongneckflag(boolean lnf) {
		LongNeckFlag = lnf;
	}
   
   public void set_score(int s)//���õ�ǰ����
   {
	   this.score+=s;
   }
   public void set_time(int l)//���õ�ǰʱ��
   {
	   this.time+=l;
   }
   public void set_life(int l)//����ʣ������
   {
	   this.life+=l;
   }

   public boolean isgameover()//�ж���Ϸ�Ƿ����
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
	   c.drawText("�������ֵ:"+text, 0, 20, p1);
	   Paint p2=new Paint();
	   p2.setTextSize(20f);
	   p2.setStyle(Paint.Style.FILL);
	   p2.setColor(Color.RED);
	  text=""+this.time;
	   c.drawText("ʣ���ʱ��:"+text, 150, 20, p2);
	   text=""+this.score;
	   Paint p3=new Paint();
	   p3.setColor(Color.GREEN);
	   p3.setTextSize(20f);
	   p3.setStyle(Style.FILL);
	   c.drawText("��ҷ���"+text, 0,60 , p3);
	   
   }
   public void draw(int num)
   {
	 
	  this.generate_track();//���ܵ�
       this.generate_player(num);//�����
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
   
   
 
   
   
   
   

   public void moveall()//�����е�faller�����ƶ�
   {
	   int n=this.faller.size();
	   for(int i=0;i<n;i++)
	   {
		   faller.get(i).move();
	   }
   }
   public void set_track(int n)//���ù����
   {
	   avoider.setTracknumber(n);
   }
   public int get_track()//��ȡ�����
   {
	   return avoider.getTracknumber();
   }
   public boolean listen_over_the_border(int i)//�����������Ƿ���˻���
   {
	   
		   if(faller.get(i).getY()>=gamerunActivity.screen_height)
		   {faller.remove(i);return true;}
		   else return false;
		   
	   
   }
   public boolean crush(int i)//��ײ���
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
