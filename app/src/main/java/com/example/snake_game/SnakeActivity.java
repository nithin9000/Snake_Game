package com.example.snake_game;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class SnakeActivity extends Activity {
    SnakeGame  mSnakeGame;//instance of game

    @Override//setting up game
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();//pixel dimenison

        Point size = new Point();
        display.getSize(size);

        mSnakeGame = new SnakeGame(this,size);
        setCOntentView(mSnakeGame);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSnakeGame.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mSnakeGame.pause();
    }


    }