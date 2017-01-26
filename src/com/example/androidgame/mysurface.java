package com.example.androidgame;



import android.content.Context;

import android.graphics.Canvas;//画布类
import android.media.MediaPlayer;


import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class mysurface extends SurfaceView implements Callback 
{
	//各种属性的声明
	private MyThread thread;	
	private Context context;
	private SurfaceHolder holder;
	public MediaPlayer mp;	
	public boolean isover=false;
	
	
	
	public mysurface(Context context)
	{// 构造函数
		
		super(context);
		this.context = context;		
		 holder = getHolder();// SurfaceHolder类对象holder的初始化， SurfaceHolder类用来帮助SurfaceView进行一些操作
		holder.addCallback(this); // 为SurfaceView添加回调函数
		thread = new MyThread(this, holder, context);
		mp=MediaPlayer.create(context, R.raw.background);
		mp.setLooping(true);	
		
		
		
	}
	
		

	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) 
	{
		
	}
	public void surfaceCreated(SurfaceHolder holder)
	{
		mp.start();
		thread.isRunning = true;
		thread.start();

	}
	
	
	

	public void surfaceDestroyed(SurfaceHolder holder)
	{
		this.mp.stop();
		this.mp.release();
		thread.isRunning = false;
		try 
		{
			thread.join();
			
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

	}

	public MyThread getthread()
	{
		return thread;
	}

	
	
	
	class MyThread extends Thread
	{// 自定义线程类
		
		private SurfaceHolder surfaceHolder;
		private Context context;
		private boolean isRunning;
		private mysurface mysurface;
		private Canvas cc=null;
		public gamerule rule;//游戏规则对象
		
		private boolean longNeckFlag = false;

		private int countdown = 0;//用于延长伸长脖子状态持续的帧数，便于观察
		
		public boolean pauseflag=false;

		
		
		public MyThread(mysurface mysv, SurfaceHolder surfaceHolder,Context context)//整个游戏过程在这里完成 
		{
			mysurface = mysv;
			this.surfaceHolder = surfaceHolder;
			this.context = context;		
			isRunning = false;			
			rule=new gamerule(mysurface,cc,context);
			rule.generate_player(2);//设置初始位置为2号跑道
			rule.generate_enemy();//产生第一批敌人
			rule.generate_goods();//根据那个保存商品信息的表产生一组商品
			
		}
		
		
		
		
		public void setLongneckflag(boolean lnf)// 修改长脖子标志
		{
			longNeckFlag = lnf;
		}

		public void testLongneckflag()// 恢复长脖子标志为false，从而实现伸长脖子再在下一帧缩回来的效果
		{
			if (longNeckFlag)
				longNeckFlag = false;
		}
		
		
		//对外的接口
		public int get_total_score()//获取总的分数
		{
			return rule.get_score();
		}
		public int getTracknumberofAvoider()//获取轨道的号码
		{
			return rule.get_track();
		}
		public void setTracknumberofAvoider(int num)//设置轨道的号码
		{
			rule.set_track(num);
		}
		
		
		public void run() 
		{				
			
            rule.set_canvas(cc);             
			while (isRunning)
			{
				try 
				{
					synchronized (surfaceHolder) 
					{
						
						surfaceHolder.setKeepScreenOn(true);// 设置屏幕常亮
						cc=surfaceHolder.lockCanvas(null);
						
						if(pauseflag==false) 
                       {
						rule.set_canvas(cc);
						if (cc != null) 
						{	
							
									if(rule.continue_generate(4))rule.generate_enemy();//判断产生敌人
									
									rule.setLongneckflag(longNeckFlag);
									
									rule.draw(this.getTracknumberofAvoider());//画掉落物和玩家
									
									Thread.sleep(1000/60);// 60fps
									rule.add_sec();//时间增加
													
									rule.draw_title();	//画标题栏
									rule.drawshopping();//画商品
									
									
									countdown++;
									if (countdown ==10)
									{
										testLongneckflag();// 恢复长脖子标志为false
										countdown = 0;//延时器清零
									}
									
							
							
							
							
									
									if(rule.isgameover())
									{
									rule.draw_end();
									this.isRunning=false;
									mysurface.isover=true;
									mp.stop();
									
									}//if
									//判断游戏是否结束
									rule.moveall();//改变所有掉落物的坐标信息
						}//if
					}//else
				}//synthronized 
				}//try
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				} //catch
				finally 
				{
					try 
					{
						
						surfaceHolder.unlockCanvasAndPost(cc);// 解锁画布，提交画好的图像
						
					} //try
					catch (IllegalArgumentException exception) 
					{
						exception.printStackTrace();// 不一致异常的处理
					}//catch

				}//finally
				

			}//while
			
		}//run
		
	}
/*---------------------------------------------------------------------------------------------------------*/
	
}

	
		



