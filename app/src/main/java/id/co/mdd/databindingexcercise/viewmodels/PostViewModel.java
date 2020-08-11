package id.co.mdd.databindingexcercise.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.mdd.databindingexcercise.models.posts.PostModel;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import id.co.mdd.databindingexcercise.repositories.posts.PostRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends BaseViewModel{
    private MutableLiveData<PostModel> data_post = new MutableLiveData<>();

    public void fetchUsers(){
        isLoading.setValue(true);
        PostRepository postRepository = new PostRepository();
        postRepository.service.getPosts().enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        data_post.setValue(response.body());
                    } else {
                        error.setValue("Data Kosong!");
                    }
                } else {
                    error.setValue(response.message());
                }

                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                t.printStackTrace();

                error.setValue(t.getMessage());
                isLoading.setValue(false);
            }
        });

    }

    public MutableLiveData<PostModel> getPosts(){
        return data_post;
    }
}
