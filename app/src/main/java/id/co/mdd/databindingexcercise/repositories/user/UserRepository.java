package id.co.mdd.databindingexcercise.repositories.user;

import id.co.mdd.databindingexcercise.utils.ApiUtil;
import id.co.mdd.databindingexcercise.utils.ConstantsUtil;

public class UserRepository {
    public UserService service;

    public UserRepository() {
        service = ApiUtil.request(UserService.class, ConstantsUtil.BASEURL);
    }
}
