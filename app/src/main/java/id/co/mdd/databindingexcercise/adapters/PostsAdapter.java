package id.co.mdd.databindingexcercise.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.activities.UserDetailActivity;
import id.co.mdd.databindingexcercise.databinding.ItemUserBinding;
import id.co.mdd.databindingexcercise.databinding.PostLayoutBinding;
import id.co.mdd.databindingexcercise.models.posts.PostModel;
import id.co.mdd.databindingexcercise.models.user.DataItem;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import retrofit2.Callback;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> implements Filterable {

    private List<PostModel> posts = new ArrayList<>();
    private static Context context;
    private static List<PostModel> postsFiltered;


    public void setUsersAdapter(Context context, List posts) {
        this.context = context;
        this.posts = posts;
        this.postsFiltered = posts;
        notifyDataSetChanged();
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.post_layout,
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        holder.bind(postsFiltered.get(position));
    }


    // filter
    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();
                if (str.isEmpty()){
                    postsFiltered = posts;
                } else {
                    List<PostModel> filtered = new ArrayList<>();
                    for (PostModel data :posts) {
                        if (data.getTitle().toLowerCase().contains(str.toLowerCase()) || data.getBody().toLowerCase().contains(str.toLowerCase())) {
                            filtered.add(data);
                        }
                    }
                    postsFiltered = filtered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = postsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                postsFiltered = (ArrayList<PostModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    @Override
    public int getItemCount() {
        return postsFiltered.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        PostLayoutBinding binding;
        public ViewHolder(@NonNull PostLayoutBinding binding){
            super(binding.getRoot());

            this.binding = binding;

        }
        public void bind(PostModel data){
            binding.setPostModel(data);
        }
    }
}
