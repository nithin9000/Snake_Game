package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class Raspberry extends Food{

    Raspberry(Context context,Point sr,int s){
        this.location = new Point();
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location.x = -10;
        this.value = 10;
        this.mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.raspberry);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
        this.spawn();
    }
    @Override
    public void applyMod(SnakeGame game,Snake snake){
        if(Snake.getSpeed()<2*snake.getSegmentSize()){
            snake.setSpeed(snake.getSpeed()*1.1f);
        }
        game.increaseScore(value);
        this.spawn();
    }
}
