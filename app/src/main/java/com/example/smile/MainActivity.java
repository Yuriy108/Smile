package com.example.smile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyDraw(this));
    }
}
class MyThread extends Thread{
    SurfaceHolder surfaceHolder;
    private Bitmap bitmap;
    Paint p;
    private int x;
    private int y;
    private volatile boolean isRuning=true;

    public MyThread(Context context,SurfaceHolder surfaceHolder) {
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.smile1);
        this.surfaceHolder = surfaceHolder;
        p=new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
    }
    public void stopping(){
        isRuning=false;

    }

    @Override
    public void run() {
        int smileX=0;
        int smileY=0;
        super.run();
        while (isRuning){
            Canvas canvas=surfaceHolder.lockCanvas();
            if (canvas!=null){
                try {
                    canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),p);
                    canvas.drawBitmap(bitmap,smileX,smileY,p);
                    if (smileX + bitmap.getWidth() / 2 < x) smileX+=10;
                    if (smileX + bitmap.getWidth() / 2 > x) smileX-=10;
                    if (smileY + bitmap.getHeight() / 2 < y) smileY+=10;
                    if (smileY + bitmap.getHeight() / 2 > y) smileY-=10;

                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    public void setXY(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}