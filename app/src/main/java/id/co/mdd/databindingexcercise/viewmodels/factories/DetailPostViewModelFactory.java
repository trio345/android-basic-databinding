package id.co.mdd.databindingexcercise.viewmodels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import id.co.mdd.databindingexcercise.models.posts.PostModel;
import id.co.mdd.databindingexcercise.viewmodels.BaseViewModel;

public class DetailPostViewModelFactory implements ViewModelProvider.Factory {
    private PostModel postModel;

    public DetailPostViewModelFactory(PostModel postModel) {
        this.postModel = postModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(PostModel.class).newInstance(postModel);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (T) new BaseViewModel(new PostModel());
    }
}
