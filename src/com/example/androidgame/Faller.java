package com.example.androidgame;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

public class Faller {
	private mysurface MySurface;// Faller的当前对象所处的SurfaceView,用于为绘图函数提供参数
	private Canvas c;// Faller所在的画布
	private float xstart, ystart;// Faller图片的原点（左上定点）的横纵坐标，用于绘制位图
	private float speed;// 下落速度
	private int tracknumber;// Faller所在的跑道的号
	private int type;// 掉落物的类型
	private boolean crushflag;//碰撞标志，用于绘图判断
    public int function;//设置掉落物对于玩家角色的影响
    private int screen_height,screen_width;
	public Faller(mysurface mysv, Canvas c, float x, float y,
			float sp, int tn, int type,boolean cf) {// 初始化时，务必确保原点横坐标xstart与跑道号tracknumber的一致性，即该值是否在相应跑道的范围内
												// 否则会出现数据不一致的错误
		MySurface = mysv;
		this.c = c;
		xstart = x;
		ystart = y;
		speed = sp;
		tracknumber = tn;
		this.type = type;
		switch(type)
		{
		case 0:{this.function=5;break;}//红苹果时候生命值加5;
		case 1:{this.function=-5;break;}//黑苹果时候生命值-5;
		case 2:{this.function=-100;break;}//遭遇骷髅头是生命值-100直接结束游戏
		}
		crushflag=cf;
		screen_height=gamerunActivity.screen_height;
		screen_width=gamerunActivity.screen_width;
		
	}
	public int get_function()
	{
		return this.function;//获取掉落物对于玩家的改变值;
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
	

	
	public void draw() {// 画掉落的物体
		if (ystart <= gamerunActivity.screen_height) {// 判断该掉落物是否已经全部出界，若未全部出界则绘制位图，否则停止绘制
			if (!crushflag) {// 判断该掉落物是否已发生碰撞，若未发生则绘制位图，否则停止绘制
				Resources res;// 建立资源类对象res，用于通过id来获取图片
				res = MySurface.getResources();// 获取当前应用(Application)的资源
				InputStream is = null;
				switch (type) {// 按掉落物的类别来判断绘制哪张图片
				case 0:
					//is = res.openRawResource(R.drawable.red);
					is=res.openRawResource(R.drawable.red1);
					break;// 将获取的图片传给输入流
				case 1:
					is = res.openRawResource(R.drawable.black1);
					break;// 将获取的图片传给输入流
				case 2:
					is = res.openRawResource(R.drawable.skull1);
					break;// 将获取的图片传给输入流
				}
				BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// 利用输入流和资源对象来创建位图绘制类
				Bitmap bmp2 = bmpDraw.getBitmap();// 通过位图绘制类来绘制位图
				RectF rect=new RectF(xstart,ystart,xstart+72*screen_width/480,ystart+72*screen_height/800);
				c.drawBitmap(bmp2,null,rect, null);
				
			}
		}
	}
}
