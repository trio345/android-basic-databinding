package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.adapters.PostsAdapter;
import id.co.mdd.databindingexcercise.databinding.ActivitySecondBinding;
import id.co.mdd.databindingexcercise.viewmodels.PostViewModel;

public class SecondActivity extends AppCompatActivity {
    PostViewModel postViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
       postViewModel.fetchUsers();
       postViewModel.
    }
}