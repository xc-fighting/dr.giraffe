package com.example.androidgame;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

public class Faller {
	private mysurface MySurface;// Faller�ĵ�ǰ����������SurfaceView,����Ϊ��ͼ�����ṩ����
	private Canvas c;// Faller���ڵĻ���
	private float xstart, ystart;// FallerͼƬ��ԭ�㣨���϶��㣩�ĺ������꣬���ڻ���λͼ
	private float speed;// �����ٶ�
	private int tracknumber;// Faller���ڵ��ܵ��ĺ�
	private int type;// �����������
	private boolean crushflag;//��ײ��־�����ڻ�ͼ�ж�
    public int function;//���õ����������ҽ�ɫ��Ӱ��
    private int screen_height,screen_width;
	public Faller(mysurface mysv, Canvas c, float x, float y,
			float sp, int tn, int type,boolean cf) {// ��ʼ��ʱ�����ȷ��ԭ�������xstart���ܵ���tracknumber��һ���ԣ�����ֵ�Ƿ�����Ӧ�ܵ��ķ�Χ��
												// �����������ݲ�һ�µĴ���
		MySurface = mysv;
		this.c = c;
		xstart = x;
		ystart = y;
		speed = sp;
		tracknumber = tn;
		this.type = type;
		switch(type)
		{
		case 0:{this.function=5;break;}//��ƻ��ʱ������ֵ��5;
		case 1:{this.function=-5;break;}//��ƻ��ʱ������ֵ-5;
		case 2:{this.function=-100;break;}//��������ͷ������ֵ-100ֱ�ӽ�����Ϸ
		}
		crushflag=cf;
		screen_height=gamerunActivity.screen_height;
		screen_width=gamerunActivity.screen_width;
		
	}
	public int get_function()
	{
		return this.function;//��ȡ�����������ҵĸı�ֵ;
	}
	public Faller()
	{
		screen_height=gamerunActivity.screen_height;
		screen_width=gamerunActivity.screen_width;
	}
    public void set_surface(mysurface a)
    {
    	this.MySurface=a;
    }
	public void setCanvas(Canvas can)
	{
		this.c=can;
	}
	
	public float getX() {
		return xstart;
	}

	public float getY() {
		return ystart;
	}
	
	public void setY(float y)
	{
		ystart=y;
	}
	public void move()
	{
		this.ystart+=this.speed;
	}


	public int getTracknumber() {
		return tracknumber;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float sp) {
		speed=sp;
	}
    public void settype(int type)
    {
    	this.type=type;
    }
    public void settracknumber(int t)
    {
    	this.tracknumber=t;
    }
	public void setx(int xx)
	{
		this.xstart=xx;
	}
	public int getType() {
		return type;
	}
	
	public void setCrushflag(boolean cf)
	{
		crushflag=cf;
	}
	

	
	public void draw() {// �����������
		if (ystart <= gamerunActivity.screen_height) {// �жϸõ������Ƿ��Ѿ�ȫ�����磬��δȫ�����������λͼ������ֹͣ����
			if (!crushflag) {// �жϸõ������Ƿ��ѷ�����ײ����δ���������λͼ������ֹͣ����
				Resources res;// ������Դ�����res������ͨ��id����ȡͼƬ
				res = MySurface.getResources();// ��ȡ��ǰӦ��(Application)����Դ
				InputStream is = null;
				switch (type) {// ���������������жϻ�������ͼƬ
				case 0:
					//is = res.openRawResource(R.drawable.red);
					is=res.openRawResource(R.drawable.red1);
					break;// ����ȡ��ͼƬ����������
				case 1:
					is = res.openRawResource(R.drawable.black1);
					break;// ����ȡ��ͼƬ����������
				case 2:
					is = res.openRawResource(R.drawable.skull1);
					break;// ����ȡ��ͼƬ����������
				}
				BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// ��������������Դ����������λͼ������
				Bitmap bmp2 = bmpDraw.getBitmap();// ͨ��λͼ������������λͼ
				RectF rect=new RectF(xstart,ystart,xstart+72*screen_width/480,ystart+72*screen_height/800);
				c.drawBitmap(bmp2,null,rect, null);
				
			}
		}
	}
}
