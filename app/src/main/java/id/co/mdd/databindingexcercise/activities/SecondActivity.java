package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.databinding.ActivitySecondBinding;
import id.co.mdd.databindingexcercise.viewmodels.PostViewModel;

public class SecondActivity extends AppCompatActivity {
    ActivitySecondBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(SecondActivity.this, R.layout.post_layout);

        PostViewModel postModel = new ViewModelProvider(this).get(PostViewModel.class);
        postModel.fetchUsers();
        postModel.getPosts().observe(this, postsModel -> {

        });
    }
}