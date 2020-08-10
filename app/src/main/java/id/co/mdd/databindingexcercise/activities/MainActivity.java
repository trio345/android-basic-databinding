package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import id.co.mdd.databindingexcercise.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        showTerms();
    }

    public void FirstActivity(View view) {

    }

    private void showTerms(){
        View termsView;
        LayoutInflater inflater;
        inflater = getLayoutInflater();
        termsView = inflater.inflate(R.layout.terms_condition, null);
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setView(termsView);
    }


    public void SecondActivity(View view) {
    }



}