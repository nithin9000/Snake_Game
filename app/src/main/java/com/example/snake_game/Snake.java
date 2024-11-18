package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Snake {

    private float Speed;
    int lives;

    private final ArrayList<Point> segmentLocations;
    private final int mSegmentSize;
    private final Point mMoveRange;

    private final int halfWayPoint;
    private enum Heading{
        UP,RIGHT,DOWN,LEFT
    }

    private Heading heading = Heading.RIGHT;
    private Heading nextHeading = Heading.RIGHT;

    private Bitmap mBitmapHeadRight; //Direction
    private Bitmap mBitmapHeadLeft;
    private Bitmap mBitmapHeadUp;
    private Bitmap mBitmapHeadDown;

    private Bitmap mBitmapBody;
    private Bitmap mBitmapBody2;

    Snake(Context context,Point mr,int ss){
        segmentLocations = new ArrayList<>();

        mSegmentSize = ss;
        mMoveRange = mr;
        this.Speed = ss/5f;
        this.lives = 3;

        mBitmapHeadRight = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        mBitmapHeadLeft = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        mBitmapHeadUp = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);
        mBitmapHeadDown = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.head);

        mBitmapHeadRight = Bitmap
                .createScaledBitmap(mBitmapHeadRight,
                        ss,ss,false);

        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);

        mBitmapHeadLeft = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0,0,ss,ss,matrix,true);

        matrix.preRotate(180);
        mBitmapHeadDown = Bitmap
                .createBitmap(mBitmapHeadRight,
                        0,0,ss,ss,matrix,true);

        mBitmapBody = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.body);

        mBitmapBody2 = BitmapFactory
                .decodeResource(context.getResources(),
                        R.drawable.body2);

        mBitmapBody = Bitmap
                .createScaledBitmap(mBitmapBody,
                        ss,ss,false);

        mBitmapBody2 = Bitmap
                .createScaledBitmap(mBitmapBody2,
                        ss,ss,false);

        halfWayPoint = mr.x / 2;

    }
    //Reset method
    void reset(){
        heading = Heading.RIGHT;//Reset the heading
        nextHeading = heading;
        lives = 3;
        segmentLocations.clear(); //Deleting old contents

        segmentLocations.add(new Point(mSegmentSize,mSegmentSize));
        Speed = mSegmentSize / 8f;
    }

    //Move

    void move(){
        for(int i = segmentLocations.size() - 1;i>0;i--){
            segmentLocations.get(i).x = segmentLocations.get(i-1).x;
            segmentLocations.get(i).y = segmentLocations.get(i-1).y;
        }

        Point p = segmentLocations.get(0);

        switch(heading){
            case UP:
                if(p.y%mSegmentSize <= Speed && nextHeading != heading){
                    p.y -= p.y%mSegmentSize;
                    heading = nextHeading;
                }else{
                    p.y –= Speed;
                }
                break;
            case RIGHT:
                if(p.x%mSegmentSize>=mSegmentSize - Speed && nextHeading != heading){
                }else{
                    p.x+=Speed;
                }
                break;
            case DOWN:
                if(p.y%mSegmentSize >= mSegmentSize - Speed && nextHeading != heading ){
                    p.y+=mSegmentSize - p.y%mSegmentSize;
                    heading = nextHeading;
                }else{
                    p.y+=Speed;
                }
                break;
            case LEFT:
                if(p.x%mSegmentSize<=Speed && nextHeading != heading){
                    p.x-=p.x%mSegmentSize;
                    heading = nextHeading;
                }else{
                    p.x-=Speed;
                }
                break;
        }
    }
    boolean detectDeath(){
        boolean dead = false;

        if(this.lives <= 0){
            dead = true;
        }

        //Hitting screen edge
        if(segmentLocations.get(0).x <= mSegmentSize-1 ||
                segmentLocations.get(0).x > mMoveRange.x - mSegmentSize * 2 ||
                segmentLocations.get(0).y <= mSegmentSize - 1 ||
                segmentLocations.get(0).y > mMoveRange.y - mSegmentSize*2){
            dead = true;
        }

        if (segmentLocations.size() > 9) {
            for(int i = segmentLocations.size()-1;i>8;i--){
                if(Math.abs(segmentLocations.get(0).x-segmentLocations.get(i).x) <= Speed/2 &&
                        Math.abs(segmentLocations.get(0).y - segmentLocations.get(i).y) <= Speed/2){
                    dead = true;
                }
            }
        }
        return dead;
    }
    boolean chickenDinner(Point l,Food food){
        if(Math.abs(segmentLocations.get(0).x-l.x)<= mSegmentSize/2 &&
                Math.abs(segmentLocations.get(0).y-l.y)<=mSegmentSize/2){
            for(int i = 0;i<food.getValue();i++){
                segmentLocations.add(new Point(-100,-100));
            }
            return true;
        }
        return false;
    }
    void draw(Canvas canvas,Paint paint){
        int colorLength = 0;
        for(int i = 1;i<segmentLocations.size();i++){
            if(colorLength<4){
                canvas.drawBitmap(mBitmapBody,segmentLocations.get(i).x,segmentLocations.get(i).y,paint);
            }else{
                canvas.drawBitmap(mBitmapBody2,segmentLocations.get(i).x,segmentLocations.get(i).y,paint);
            }
            colorLength ++;
            if(colorLength>7){
                colorLength = 0 ;
            }
        }
        if(!segmentLocations.isEmpty()){
            switch(heading){
                case RIGHT:
                    canvas.drawBitmap(mBitmapHeadRight,
                            segmentLocations.get(0).x
                                    ,
                            segmentLocations.get(0).y
                                    ,paint);
                    break;

                case LEFT:
                    canvas.drawBitmap(mBitmapHeadLeft,
                            segmentLocations.get(0).x
                                    ,
                            segmentLocations.get(0).y
                                    ,paint);
                    break;
                case UP:
                    canvas.drawBitmap(mBitmapHeadUp,
                            segmentLocations.get(0).x
                                    ,
                            segmentLocations.get(0).y
                                    ,paint);
                    break;
                case DOWN:
                    canvas.drawBitmap(mBitmapHeadDown,
                            segmentLocations.get(0).x
                                    ,
                            segmentLocations.get(0).y
                                    ,paint);
                    break;
            }
        }
    }
    void switchHeading(MotionEvent motionEvent){
        if(motionEvent.getX() >= halfWayPoint){
            switch(heading){
                case UP:
                    nextHeading = Heading.RIGHT;
                    break;
                case RIGHT:
                    nextHeading = heading.DOWN;
                    break;
                case DOWN:
                    nextHeading = Heading.LEFT;
                    break;
                case LEFT:
                    nextHeading = Heading.UP;
                    break;
            }
        }else{
            switch(heading){
                case UP:
                    nextHeading = Heading.LEFT;
                    break;
                case LEFT:
                    nextHeading = Heading.DOWN;
                    break;
                case DOWN:
                    nextHeading = Heading.RIGHT;
                    break;
                case RIGHT:
                    nextHeading = Heading.UP:
                    break;
            }
        }
    }
    public void setSpeed(float speed){this.Speed = speed;}
    public float getSpeed(){return this.Speed;}
    public int getSegmentSize(){return this.mSegmentSize;}
    public int getLives(){return this.lives;}
    public void setLives(int add){this.lives = add;}
    public ArrayList<Point> getSegmentLocations(){return this.segmentLocations;}
}
