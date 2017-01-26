package com.example.androidgame;
import java.io.InputStream;



import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
public class Avoider {
	private mysurface MySurface;// Avoider�ĵ�ǰ����������SurfaceView,����Ϊ��ͼ�����ṩ����
	private Canvas c;// Avoider���ڵĻ���
	private int tracknumber;// Avoider���ڵ��ܵ��ĺ�
	private int imagelength;// AvoiderͼƬ�ĳ��ȣ���λ�����أ����ݶ�Ϊ������
	private int xstart, ystart;// AvoiderͼƬ��ԭ�㣨���϶��㣩�ĺ������꣬���ڻ���λͼ
	private int screen_height, screen_width;
	private int imagewidth;
   
	public Avoider()
	{
	  this.screen_height=gamerunActivity.screen_height;
	  this.screen_width=gamerunActivity.screen_width;
	}
	public Avoider(mysurface mysv, Canvas c, int iml,int imd, int x, int y,
			int tn) {//��ʼ��ʱ�����ȷ��ԭ�������xstart���ܵ���tracknumber��һ���ԣ�����ֵ�Ƿ�����Ӧ�ܵ��ķ�Χ��
					//�����������ݲ�һ�µĴ��󣨱����췽����δ����߼��жϣ�
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
	public void draw()// �ڻ����ϻ���Avoider
	{
		Rect rect;
		Resources res;// ������Դ�����res������ͨ��id����ȡͼƬ
		res = MySurface.getResources();// ��ȡ��ǰӦ��(Application)����Դ
		InputStream is = res.openRawResource(R.drawable.avoiding);// ����ȡ��ͼƬ����������
		if (imagelength < imagewidth)
		{
			is = res.openRawResource(R.drawable.avoiding_long_neck);// ����ȡ��ͼƬ����������
		  rect = new Rect(xstart, ystart, xstart + 72 * screen_width/ 480, ystart + 144 * screen_width / 480);
		//	rect=new Rect(xstart,ystart,xstart+72,ystart+144);
		}
		else
		{
			is = res.openRawResource(R.drawable.avoiding);
			rect = new Rect(xstart, ystart, xstart + 72 * screen_width/ 480, ystart + 72 * screen_width/ 480);
			//rect=new Rect(xstart,ystart,xstart+72,ystart+72);
		}
		
		BitmapDrawable bmpDraw = new BitmapDrawable(res, is);// ��������������Դ����������λͼ������
		Bitmap bmp1 = bmpDraw.getBitmap();// ͨ��λͼ������������λͼ
		c.drawBitmap(bmp1, null, rect, null);// �ڻ����ϻ�λͼ����������λͼ������꣬
													// ��������λͼ�������꣬
													// ������Ϊ���ʣ���Ϊ��
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
