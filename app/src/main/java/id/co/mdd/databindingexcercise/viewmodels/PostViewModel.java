package id.co.mdd.databindingexcercise.viewmodels;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.mdd.databindingexcercise.models.user.UserModel;

public class PostViewModel extends BaseViewModel{
    private MutableLiveData<ArrayList<UserModel>> data_users = new MutableLiveData<>();

    public void fetchUsers(){
        isLoading.setValue(true);
        

    }
}
