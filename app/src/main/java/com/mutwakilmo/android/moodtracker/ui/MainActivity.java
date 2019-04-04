package com.mutwakilmo.android.moodtracker.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mutwakilmo.android.moodtracker.R;
import com.mutwakilmo.android.moodtracker.data.SharedPreferencesHelper;
import com.mutwakilmo.android.moodtracker.receiver.UpdateDayReceiver;
import com.mutwakilmo.android.moodtracker.util.Constants;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private static final String TAG = "MainActivity";

    private ImageView moodImageView;
    private ImageButton moodHistoryButton;
    private ImageButton addCommentButton;
    private ImageButton shareAppButton;
    private GestureDetectorCompat mDetector;
    private RelativeLayout parentRelativeLayout;

    private SharedPreferences mPreferences;
    private int currentDay;
    private int currentMoodIndex = 3;
    private String currentComment;

    // [START declare_analytics]
    private FirebaseAnalytics mFirebaseAnalytics;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        moodImageView = findViewById(R.id.my_mood);
        parentRelativeLayout = findViewById(R.id.parent_relative_layout);
        addCommentButton = findViewById(R.id.btn_add_comment);
        moodHistoryButton = findViewById(R.id.btn_mood_history);
        shareAppButton = findViewById(R.id.imageButton);

        mDetector = new GestureDetectorCompat(this,this);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        currentDay = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_DAY, 1);
        currentMoodIndex = mPreferences.getInt(SharedPreferencesHelper.KEY_CURRENT_MOOD, 3);
        currentComment = mPreferences.getString(SharedPreferencesHelper.KEY_CURRENT_COMMENT, "");
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        changeUiForMood(currentMoodIndex);


        //*****************************Add comment to the Mood********************************/

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final EditText editText = new EditText(MainActivity.this);


                builder.setMessage("Comment\uD83E\uDD14 \uD83D\uDCDD").setView(editText)
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!editText.getText().toString().isEmpty()) {
                                    SharedPreferencesHelper.saveComment(editText.getText().toString(), currentDay, mPreferences);
                                }



                                dialog.dismiss();

                                Toast.makeText(MainActivity.this, "Comment Saved", Toast.LENGTH_SHORT).show();

                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        Toast.makeText(MainActivity.this, "Comment Canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                        .create().show();



            }
        });


        //* History Button to view Mood history screen*/
        moodHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MoodHistoryActivity.class);
                startActivity(intent);
            }
        });

        //*Share your mood Button*/
        shareAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello! I would like to share with you my mood of the day from MoodTracker App.Today my Mood is... ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_To)));
            }
        });

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
                SharedPreferencesHelper.saveMood(currentMoodIndex, currentDay, mPreferences);

            }

        }

        // Swiping down
        else if (e1.getY() - e2.getY() < 50){
            if (currentMoodIndex > 0){
                currentMoodIndex--;
                changeUiForMood(currentMoodIndex);
                SharedPreferencesHelper.saveMood(currentMoodIndex, currentDay, mPreferences);
            }
        }
        return true;
    }

    //************************* change mood methods***************************************/
    private void changeUiForMood(int currentMoodIndex) {
        moodImageView.setImageResource(Constants.moodImagesArray[currentMoodIndex]);
        parentRelativeLayout.setBackgroundResource(Constants.moodColorsArray[currentMoodIndex]);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Constants.moodSoundsArray[currentMoodIndex]);
        mediaPlayer.start();
    }



    //* Scheduling alarm to save mood everyday
    private void scheduleAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, UpdateDayReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

}
