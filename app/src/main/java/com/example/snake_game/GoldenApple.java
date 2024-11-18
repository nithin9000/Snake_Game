package com.example.snake_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class GoldenApple extends Food{

    GoldenApple(Context context,Point sr,int s){
        this.location = new Point();
        this.mSpawnRange = sr;
        this.mSize = s;
        this.location.x = -10;
        this.value = 50;
        this.mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.golden);
        this.mBitmap = Bitmap.createScaledBitmap(this.mBitmap,s,s,false);
        this.spawn();
    }
    @Override

    public void applyMod(SnakeGame game,Snake snake){
        game.increaseScore(value);
        this.spawn();
    }
}
