package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import id.co.mdd.databindingexcercise.R;

import static id.co.mdd.databindingexcercise.activities.SplashActivity.sharedPreference;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(SplashActivity.sharedPreference, Context.MODE_PRIVATE);
        showTerms();
    }

    private void showTerms(){
        View termsView;
        LayoutInflater inflater;


        inflater = getLayoutInflater();
        termsView = inflater.inflate(R.layout.terms_condition, null);

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("SYARAT DAN KETENTUAN");
        alertDialog.setView(termsView);

        alertDialog.show();
    }


    public void moveToSecondActivity(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }


    public void moveToFirstActivity(View view) {
        startActivity(new Intent(this, FirstActivity.class));
    }
}