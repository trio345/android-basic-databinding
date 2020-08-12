package id.co.mdd.databindingexcercise.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.mdd.databindingexcercise.models.posts.PostModel;
import id.co.mdd.databindingexcercise.repositories.posts.PostRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends BaseViewModel{
    private MutableLiveData<ArrayList<PostModel>> data_post = new MutableLiveData<ArrayList<PostModel>>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public MutableLiveData<Boolean> isLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    private MutableLiveData<String> error = new MutableLiveData<>();
    private ArrayList<PostModel> temp = new ArrayList<>();

    public PostViewModel(PostModel postModel) {
        super(postModel);
    }

    public void fetchPosts(){
        isLoading.setValue(true);
        PostRepository postRepository = new PostRepository();

        postRepository.service.getPosts().enqueue(new Callback<ArrayList<PostModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
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
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                t.printStackTrace();

                error.setValue(t.getMessage());
                isLoading.setValue(false);
            }
        });

    }

    public MutableLiveData<ArrayList<PostModel>> getPosts(){
        return data_post;
    }
}
