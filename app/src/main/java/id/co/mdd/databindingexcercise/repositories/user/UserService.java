package id.co.mdd.databindingexcercise.repositories.user;

import id.co.mdd.databindingexcercise.models.posts.PostModel;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("users")
    Call<UserModel> getUsers();

    @GET("posts")
    Call<PostModel> getPosts();
}
