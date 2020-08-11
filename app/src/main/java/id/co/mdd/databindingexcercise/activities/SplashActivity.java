package id.co.mdd.databindingexcercise.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 3000;
    SharedPreferences sharedPreferences;
    private static final String SESSION_TERMS = "session_terms";
    public static Boolean terms = false;
    public static Boolean session = false;
    public static final String sharedPreference  = "myshared";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(sharedPreference, Context.MODE_PRIVATE);

        session = sharedPreferences.getBoolean(SESSION_TERMS, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(SESSION_TERMS, true);
                editor.commit();


                Intent intent = new Intent();
                intent.setClass(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
