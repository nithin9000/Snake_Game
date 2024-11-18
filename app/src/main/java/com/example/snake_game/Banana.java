package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;


public class Banana extends Food {
    Banana(Context context,Point sr,int s){
        this.location = new Point();
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location.x = -10;
        this.value = 10;

        this.mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.banana);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
    this.spawn();
    }

    @Override
    public void appyMod(SnakeGame game,Snake snake){
        if(snake.getLives()<3)
            snake.setLives(snake.getLives()+1);
        game.increaseScore(value);
        this.spawn();
    }
}
