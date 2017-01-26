package com.example.androidgame;



import android.content.Context;

import android.graphics.Canvas;//������
import android.media.MediaPlayer;


import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class mysurface extends SurfaceView implements Callback 
{
	//�������Ե�����
	private MyThread thread;	
	private Context context;
	private SurfaceHolder holder;
	public MediaPlayer mp;	
	public boolean isover=false;
	
	
	
	public mysurface(Context context)
	{// ���캯��
		
		super(context);
		this.context = context;		
		 holder = getHolder();// SurfaceHolder�����holder�ĳ�ʼ���� SurfaceHolder����������SurfaceView����һЩ����
		holder.addCallback(this); // ΪSurfaceView��ӻص�����
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
	{// �Զ����߳���
		
		private SurfaceHolder surfaceHolder;
		private Context context;
		private boolean isRunning;
		private mysurface mysurface;
		private Canvas cc=null;
		public gamerule rule;//��Ϸ�������
		
		private boolean longNeckFlag = false;

		private int countdown = 0;//�����ӳ��쳤����״̬������֡�������ڹ۲�
		
		public boolean pauseflag=false;

		
		
		public MyThread(mysurface mysv, SurfaceHolder surfaceHolder,Context context)//������Ϸ������������� 
		{
			mysurface = mysv;
			this.surfaceHolder = surfaceHolder;
			this.context = context;		
			isRunning = false;			
			rule=new gamerule(mysurface,cc,context);
			rule.generate_player(2);//���ó�ʼλ��Ϊ2���ܵ�
			rule.generate_enemy();//������һ������
			rule.generate_goods();//�����Ǹ�������Ʒ��Ϣ�ı����һ����Ʒ
			
		}
		
		
		
		
		public void setLongneckflag(boolean lnf)// �޸ĳ����ӱ�־
		{
			longNeckFlag = lnf;
		}

		public void testLongneckflag()// �ָ������ӱ�־Ϊfalse���Ӷ�ʵ���쳤����������һ֡��������Ч��
		{
			if (longNeckFlag)
				longNeckFlag = false;
		}
		
		
		//����Ľӿ�
		public int get_total_score()//��ȡ�ܵķ���
		{
			return rule.get_score();
		}
		public int getTracknumberofAvoider()//��ȡ����ĺ���
		{
			return rule.get_track();
		}
		public void setTracknumberofAvoider(int num)//���ù���ĺ���
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
						
						surfaceHolder.setKeepScreenOn(true);// ������Ļ����
						cc=surfaceHolder.lockCanvas(null);
						
						if(pauseflag==false) 
                       {
						rule.set_canvas(cc);
						if (cc != null) 
						{	
							
									if(rule.continue_generate(4))rule.generate_enemy();//�жϲ�������
									
									rule.setLongneckflag(longNeckFlag);
									
									rule.draw(this.getTracknumberofAvoider());//������������
									
									Thread.sleep(1000/60);// 60fps
									rule.add_sec();//ʱ������
													
									rule.draw_title();	//��������
									rule.drawshopping();//����Ʒ
									
									
									countdown++;
									if (countdown ==10)
									{
										testLongneckflag();// �ָ������ӱ�־Ϊfalse
										countdown = 0;//��ʱ������
									}
									
							
							
							
							
									
									if(rule.isgameover())
									{
									rule.draw_end();
									this.isRunning=false;
									mysurface.isover=true;
									mp.stop();
									
									}//if
									//�ж���Ϸ�Ƿ����
									rule.moveall();//�ı����е������������Ϣ
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
						
						surfaceHolder.unlockCanvasAndPost(cc);// �����������ύ���õ�ͼ��
						
					} //try
					catch (IllegalArgumentException exception) 
					{
						exception.printStackTrace();// ��һ���쳣�Ĵ���
					}//catch

				}//finally
				

			}//while
			
		}//run
		
	}
/*---------------------------------------------------------------------------------------------------------*/
	
}

	
		



