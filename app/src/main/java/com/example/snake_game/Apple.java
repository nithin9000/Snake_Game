package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

class Apple extends Food {
    Apple(Context context,Point sr,int s){
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location = new Point();
        this.location.x = -10;
        this.value = 10;
        this.mBitmap = BitmapFactory = BitmapFactory.decodeResource(context.getResources(),R.drawable.apple);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
        this.spawn();
    }

    @Override
    public void applyMod(SnakeGame game, Snake snake){game.increaseScore(value);}
}
