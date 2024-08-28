package com.example.ft_hangouts.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ft_hangouts.Activities.MainActivity;
import com.example.ft_hangouts.R;

public class ThemeUtils {
    private static final String PREFERENCES_FILE = "app_preferences";
    private static final String KEY_THEME = "key_theme";
    private static final int THEME_LIGHT = 1;
    private static final int THEME_DARK = 2;

    public static void applyTheme(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        int theme = preferences.getInt(KEY_THEME, THEME_LIGHT);
        if (theme == THEME_DARK) {
            activity.setTheme(R.style.DarkTheme);
        } else {
            activity.setTheme(R.style.LightTheme);
        }
    }

    public static void saveTheme(Context context, boolean isDarkTheme) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE).edit();
        editor.putInt(KEY_THEME, isDarkTheme ? THEME_DARK : THEME_LIGHT);
        editor.apply();

        // Restart the app
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public static int getCurrentTheme(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return preferences.getInt(KEY_THEME, THEME_LIGHT);
    }
}
