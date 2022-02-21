package com.example.smile;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MyDraw extends SurfaceView implements SurfaceHolder.Callback {
    private MyThread myThread;

    public MyDraw(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        myThread=new MyThread(getContext(),surfaceHolder);
        myThread.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        myThread.stopping();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myThread.setXY((int)event.getX(),(int)event.getY());
        return false;
    }
}
