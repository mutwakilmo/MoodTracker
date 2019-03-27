package com.mutwakilmo.android.moodtracker;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private ImageView moodImageView;
    private ImageButton moodHistoryButton;
    private ImageButton addCommentButton;
    private GestureDetectorCompat mDetector;
    private RelativeLayout parentRelativeLayout;

    private SharedPreferences mPreferences;
    private int currentDay;
    private int currentMoodIndex = 3;
    private String currentComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        moodImageView = findViewById(R.id.my_mood);
        parentRelativeLayout = findViewById(R.id.parent_relative_layout);
        addCommentButton = findViewById(R.id.btn_add_comment);
        moodHistoryButton = findViewById(R.id.btn_mood_history);

        mDetector = new GestureDetectorCompat(this,this);

        changeUiForMood(currentMoodIndex);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // Not needed for this project

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // Not needed for this project
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // Not needed for this project
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // Not needed for this project

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // Swiping up
        if (e1.getY() - e2.getY() > 50){
            if (currentMoodIndex < 4){
                currentMoodIndex++;
                changeUiForMood(currentMoodIndex);
                //ToDo SharedPreferences

            }

        }

        // Swiping down
        else if (e1.getY() - e2.getY() < 50){
            if (currentMoodIndex > 0){
                currentMoodIndex--;
                changeUiForMood(currentMoodIndex);
                //ToDo SharedPreferences
            }
        }
        return true;
    }

    // change mood methods
    private void changeUiForMood(int currentMoodIndex) {
        moodImageView.setImageResource(Constants.moodImagesArray[currentMoodIndex]);
        parentRelativeLayout.setBackgroundResource(Constants.moodColorsArray[currentMoodIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Constants.moodSoundsArray[currentMoodIndex]);
        mediaPlayer.start();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

}
