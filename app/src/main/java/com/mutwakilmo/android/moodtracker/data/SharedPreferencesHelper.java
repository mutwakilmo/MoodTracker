package com.mutwakilmo.android.moodtracker.data;

import android.content.SharedPreferences;

/**
 * Created by Mutwakil Mo on ${Date}
 */
public class SharedPreferencesHelper {
    private static final String TAG = "SharedPreferencesHelper";

    public static final String KEY_CURRENT_DAY = "KEY_CURRENT_DAY";

    public static final String KEY_CURRENT_MOOD = "KEY_CURRENT_MOOD";

    public static final String KEY_CURRENT_COMMENT = "KEY_CURRENT_COMMENT";

    //for mood
    public static final String KEY_MOOD0 = "KEY_MOOD0";
    public static final String KEY_MOOD1 = "KEY_MOOD1";
    public static final String KEY_MOOD2 = "KEY_MOOD2";
    public static final String KEY_MOOD3 = "KEY_MOOD3";
    public static final String KEY_MOOD4 = "KEY_MOOD4";
    public static final String KEY_MOOD5 = "KEY_MOOD5";
    public static final String KEY_MOOD6 = "KEY_MOOD6";


    // for comment
    public static final String KEY_COMMENT0 = "KEY_COMMENT0";
    public static final String KEY_COMMENT1 = "KEY_COMMENT1";
    public static final String KEY_COMMENT2 = "KEY_COMMENT2";
    public static final String KEY_COMMENT3 = "KEY_COMMENT3";
    public static final String KEY_COMMENT4 = "KEY_COMMENT4";
    public static final String KEY_COMMENT5 = "KEY_COMMENT5";
    public static final String KEY_COMMENT6 = "KEY_COMMENT6";

    //Save Mood Method

    public static void saveMood(int moodIndex, int currentDay, SharedPreferences preferences) {
        preferences.edit().putInt(KEY_CURRENT_MOOD, moodIndex).apply();
        switch (currentDay) {
            case 1:
                preferences.edit().putInt(KEY_MOOD0, moodIndex).apply();
                break;
            case 2:
                preferences.edit().putInt(KEY_MOOD1, moodIndex).apply();
                break;
            case 3:
                preferences.edit().putInt(KEY_MOOD2, moodIndex).apply();
                break;
            case 4:
                preferences.edit().putInt(KEY_MOOD3, moodIndex).apply();
                break;
            case 5:
                preferences.edit().putInt(KEY_MOOD4, moodIndex).apply();
                break;
            case 6:
                preferences.edit().putInt(KEY_MOOD5, moodIndex).apply();
                break;
            case 7:
                preferences.edit().putInt(KEY_MOOD6, moodIndex).apply();
                break;
            default:
                preferences.edit().putInt(KEY_MOOD0, preferences.getInt(KEY_MOOD1, 3)).apply();
                preferences.edit().putInt(KEY_MOOD1, preferences.getInt(KEY_MOOD2, 3)).apply();
                preferences.edit().putInt(KEY_MOOD2, preferences.getInt(KEY_MOOD3, 3)).apply();
                preferences.edit().putInt(KEY_MOOD3, preferences.getInt(KEY_MOOD4, 3)).apply();
                preferences.edit().putInt(KEY_MOOD4, preferences.getInt(KEY_MOOD5, 3)).apply();
                preferences.edit().putInt(KEY_MOOD5, preferences.getInt(KEY_MOOD6, 3)).apply();
                preferences.edit().putInt(KEY_MOOD6, moodIndex).apply();
        }
    }


    //Save comment Method
    public static void saveComment(String comment, int currentDay, SharedPreferences preferences) {
        preferences.edit().putString(KEY_CURRENT_COMMENT, comment).apply();
        switch (currentDay) {
            case 1:
                preferences.edit().putString(KEY_COMMENT0, comment).apply();
                break;
            case 2:
                preferences.edit().putString(KEY_COMMENT1, comment).apply();
                break;
            case 3:
                preferences.edit().putString(KEY_COMMENT2, comment).apply();
                break;
            case 4:
                preferences.edit().putString(KEY_COMMENT3, comment).apply();
                break;
            case 5:
                preferences.edit().putString(KEY_COMMENT4, comment).apply();
                break;
            case 6:
                preferences.edit().putString(KEY_COMMENT5, comment).apply();
                break;
            case 7:
                preferences.edit().putString(KEY_COMMENT6, comment).apply();
                break;
            default:
                preferences.edit().putString(KEY_COMMENT0, preferences.getString(KEY_COMMENT1, "")).apply();
                preferences.edit().putString(KEY_COMMENT1, preferences.getString(KEY_COMMENT2, "")).apply();
                preferences.edit().putString(KEY_COMMENT2, preferences.getString(KEY_COMMENT3, "")).apply();
                preferences.edit().putString(KEY_COMMENT3, preferences.getString(KEY_COMMENT4, "")).apply();
                preferences.edit().putString(KEY_COMMENT4, preferences.getString(KEY_COMMENT5, "")).apply();
                preferences.edit().putString(KEY_COMMENT5, preferences.getString(KEY_COMMENT6, "")).apply();
                preferences.edit().putString(KEY_COMMENT6, comment).apply();


        }
    }

}
