package com.example.snake_game;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;
import java.util.ArrayList;

class SnakeGame extends SurfaceView implements Runnable {
    private Thread mThread=null;
    private Long mNextFrameTime;

    //Paused or playing
    private volatile boolean mPlaying = false;
    private volatile GAME_STATE CURRENT_STATE = GAME_STATE.START_SCREEN;
    private Bitmap startScreen;
    private Bitmap helpScreen;
    private Bitmap pauseScreen;
    private Bitmap gameOverScreen;
    private Bitmap backGround;
    private Bitmap border;

    //Sound
    private SoundPool mSP;
    private int mEat_ID = -1;
    private int mCrashID = -1;
    private int mSongID = -1;

    //Playarea
    private final int NUM_BLOCKS_WIDE = 18;
    private int mNumBlocksHigh;
    private int mScore;
    private int highScore;
    private final SurfaceHolder mSurfaceHolder;
    private final paint mPaint;
    private final Snake mSnake;
    private FoodFactory foodfactory;
    private ArrayList<Food> FOOD;
    private final int FOOD_TYPES = 5;
    private final long TARGET_FPS = 30;
    final long MILLIS_PER_SECOND = 1000;
    private int blockSize;
    private final point screenSize;
    private final Context gameContext;
    private PauseButton pauseButton


    public SnakeGame(Context context,Point size){
        super(context);
        screensize = size;
        gameContext = context;
        highScore = 0;


    }
}
