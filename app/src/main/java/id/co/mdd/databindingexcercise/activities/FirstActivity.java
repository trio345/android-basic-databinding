package id.co.mdd.databindingexcercise.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import androidx.recyclerview.widget.LinearLayoutManager;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.adapters.UsersAdapter;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import id.co.mdd.databindingexcercise.repositories.user.UserRepository;
import id.co.mdd.databindingexcercise.repositories.user.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstActivity extends AppCompatActivity {

    ProgressBar pbLoading;
    TextView tvEmail, tvFullName;
    ImageView ivProfile;
    EditText etSearch;
    RecyclerView rvUsers;
    RecyclerView.Adapter adapter;
    UserService service;
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        pbLoading = findViewById(R.id.pbLoading);
        tvEmail = findViewById(R.id.tvEmail);
        tvFullName = findViewById(R.id.tvFullName);
        rvUsers = findViewById(R.id.rvUsers);
        ivProfile = findViewById(R.id.ivProfile);
        etSearch = findViewById(R.id.etSearch);

        usersAdapter = new UsersAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUsers.setLayoutManager(layoutManager);


        UserRepository userRepository = new UserRepository();

        searchData();

        userRepository.service.getUsers(1).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                showLoading(false);
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        String jsonResponse = new Gson().toJson(response.body());
                        usersAdapter.setUsersAdapter(FirstActivity.this, response.body().getData());
                        rvUsers.setAdapter(usersAdapter);

                    }
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                t.printStackTrace();
//                showLoading(false);

                Toast.makeText(FirstActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });




    }

    private void searchData() {

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usersAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usersAdapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                usersAdapter.getFilter().filter(editable);
            }
        });
    }


    private void showLoading(boolean isLoading){
        if (isLoading){
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }
}