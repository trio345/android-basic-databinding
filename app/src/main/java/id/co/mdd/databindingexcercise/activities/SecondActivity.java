package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.adapters.PostsAdapter;
import id.co.mdd.databindingexcercise.databinding.ActivitySecondBinding;
import id.co.mdd.databindingexcercise.viewmodels.BaseViewModel;
import id.co.mdd.databindingexcercise.viewmodels.PostViewModel;

public class SecondActivity extends AppCompatActivity {

    public static final String DATA_POST = "post";
    
    ActivitySecondBinding binding;
    PostViewModel postViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        PostsAdapter postsAdapter = new PostsAdapter();
//        postsAdapter.postListener = postModel -> {
//            Intent intent = new Intent(
//                    SecondActivity.this,
//                    Pos
//            )
//        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);
        binding.rvPosts.setLayoutManager(new StaggeredGridLayoutManager(
                2,
                LinearLayoutManager.VERTICAL
        ));

        binding.rvPosts.setAdapter(postsAdapter);

        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getPosts().observe(this, postsAdapter::setPosts);

        postViewModel.isLoading().observe(this, isLoading -> {
            binding.pbLoadingPost.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.rvPosts.setVisibility(isLoading ? View.GONE : View.GONE);
        });


        postViewModel.getError().observe(this, error -> {
            if (error.length() > 0) Toast.makeText(this,error, Toast.LENGTH_SHORT).show();
        });

        binding.setPostModel(postViewModel);
    }
}