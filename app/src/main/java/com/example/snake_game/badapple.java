package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;


public class badapple extends Food {
    badapple(Context context,Point sr,int s){
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location = new Point();
        this.location.x = -10;
        this.value = 0;
        this.mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.badapple);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
        this.spawn();
    }
    @Override
    public void applyMod(SnakeGame game,Snake snake){

        game.increaseScore(value);
        if(snake.getSpeed()>snake.getSegmentSize()/12f){
            snake.getSpeed(snake.getSpeed()*.9f);
        }
        this.spawn();
    }
}
