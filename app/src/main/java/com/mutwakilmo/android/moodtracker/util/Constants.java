package com.mutwakilmo.android.moodtracker.util;

import com.mutwakilmo.android.moodtracker.R;

/**
 * Created by Mutwakil Mo on ${Date}
 */
public class Constants {
    //* moods array images*
    public static int[] moodImagesArray = {R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};
    /** moods array colors */

    public static int[] moodColorsArray = {R.color.colorSad,
            R.color.colorDisappointed,
            R.color.colorNormal,
            R.color.colorHappy,
            R.color.colorSuperHappy};

    /** The sounds id array for the moods */
    public static int[] moodSoundsArray = {R.raw.note_sad,
            R.raw.note_disappointed,
            R.raw.note_normal,
            R.raw.note_happy,
            R.raw.note_super_happy};
}
