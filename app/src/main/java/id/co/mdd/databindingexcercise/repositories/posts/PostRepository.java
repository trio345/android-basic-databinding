package id.co.mdd.databindingexcercise.repositories.posts;

import id.co.mdd.databindingexcercise.repositories.user.UserService;
import id.co.mdd.databindingexcercise.utils.ApiUtil;
import id.co.mdd.databindingexcercise.utils.ConstantsUtil;

public class PostRepository {
    public UserService service;
    public PostRepository() {
        service = ApiUtil.request(UserService.class, ConstantsUtil.BASE_URL);
    }
}
