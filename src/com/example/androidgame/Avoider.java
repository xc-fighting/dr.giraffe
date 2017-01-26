package com.example.androidgame;
import java.io.InputStream;



import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
public class Avoider {
	private mysurface MySurface;// Avoider的当前对象所处的SurfaceView,用于为绘图函数提供参数
	private Canvas c;// Avoider所在的画布
	private int tracknumber;// Avoider所在的跑道的号
	private int imagelength;// Avoider图片的长度（单位：像素），暂定为正方形
	private int xstart, ystart;// Avoider图片的原点（左上顶点）的横纵坐标，用于绘制位图
	private int screen_height, screen_width;
	private int imagewidth;
   
	public Avoider()
	{
	  this.screen_height=gamerunActivity.screen_height;
	  this.screen_width=gamerunActivity.screen_width;
	}
	public Avoider(mysurface mysv, Canvas c, int iml,int imd, int x, int y,
			int tn) {//初始化时，务必确保原点横坐标xstart与跑道号tracknumber的一致性，即该值是否在相应跑道的范围内
					//否则会出现数据不一致的错误（本构造方法并未设计逻辑判断）
		this.MySurface = mysv;
		this.c = c;
		this.imagelength = iml;
		this.imagewidth=imd;
		this.xstart = x;
		this.ystart = y;
		this.tracknumber = tn;
		 this.screen_height=gamerunActivity.screen_height;
		  this.screen_width=gamerunActivity.screen_width;
	}

	public int getX() {
		return xstart;
	}

	public int getY() {
		return ystart;
	}

	public int getImagelength() {
		return imagelength;
	}

	public int getTracknumber() {
		return tracknumber;
	}
	public void setx(int xx)
	{
		this.xstart=xx;
	}
	public void sety(int yy)
	{
		this.ystart=yy;
	}

	public void setTracknumber(int tn) {
		tracknumber = tn;
	}
    public void setcanvas(Canvas c)
    {
    	this.c=c;
    }
    public void setsurface(mysurface s)
    {
    	this.MySurface=s;
    }
	public void draw()// 在画布上绘制Avoider
	{
		Rect rect;
		Resources res;// 建立资源类对象res，用于通过id来获取图片
		res = MySurface.getResources();// 获取当前应用(Application)的资源
		InputStream is = res.openRawResource(R.drawable.avoiding);// 将获取的图片传给输入流
		if (imagelength < imagewidth)
		{
			is = res.openRawResource(R.drawable.avoiding_long_neck);// 将获取的图片传给输入流
		  rect = new Rect(xstart, ystart, xstart + 72 * screen_width/ 480, ystart + 144 * screen_width / 480);
		//	rect=new Rect(xstart,ystart,xstart+72,ystart+144);
		}
		else
		{
			is = res.openRawResource(R.drawable.avoiding);
			rect = new Rect(xstart, ystart, xstart + 72 * screen_width/ 480, ystart + 72 * screen_width/ 480);
			//rect=new Rect(xstart,ystart,xstart+72,ystart+72);
		}
		
		BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// 利用输入流和资源对象来创建位图绘制类
		Bitmap bmp1 = bmpDraw.getBitmap();// 通过位图绘制类来绘制位图
		c.drawBitmap(bmp1, null, rect, null);// 在画布上画位图，变量二是位图左端坐标，
													// 变量三是位图顶端坐标，
													// 变量四为画笔，可为空
	}
	
	
	
	
	public void setImagelength(int iml) {
		imagelength = iml;
	}

	public int getImagewidth() {
		return imagewidth;
	}

	public void setImagewidth(int imw) {
		imagewidth = imw;
	}

}
