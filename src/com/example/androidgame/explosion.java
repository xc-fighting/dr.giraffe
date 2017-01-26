package com.example.androidgame;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class explosion 
{
     mysurface gameview;
     private Bitmap bitmaps;
     
     private float x;
     private float y;
     public explosion(mysurface s,Bitmap b,float x,float y)
     {
    	 this.gameview=s;
    	 this.bitmaps=b;
    	 this.x=x;
    	 this.y=y;
     }
     public void drawself(Canvas cc)
     {
    		 cc.drawBitmap(bitmaps, x, y, null);  	 
     }
     
}
