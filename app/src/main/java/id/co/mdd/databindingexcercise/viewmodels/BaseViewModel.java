package id.co.mdd.databindingexcercise.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.co.mdd.databindingexcercise.models.posts.PostModel;

public class BaseViewModel extends ViewModel {

   public ObservableField<PostModel> post = new ObservableField<>();

    public BaseViewModel(PostModel postModel) {
        post.set(postModel);
    }
}
