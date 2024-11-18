package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class Bomb extends Food {
    Bomb(Context context,Point sr,int s){
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location = new Point();
        this.location.x = -10;
        this.value = 0;
        this.mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
        this.spawn();
    }

    @Override
    public void applyMod(SnakeGame game,Snake snake){
        snake.setLives(snake.getLives()-1);
    }
}
