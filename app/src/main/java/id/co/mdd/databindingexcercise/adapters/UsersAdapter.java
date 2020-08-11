package id.co.mdd.databindingexcercise.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.mdd.databindingexcercise.R;
import id.co.mdd.databindingexcercise.databinding.ItemUserBinding;
import id.co.mdd.databindingexcercise.models.user.DataItem;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import retrofit2.Callback;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<DataItem> users = new ArrayList<>();
    private Context context;


    public UsersAdapter(Context context, List users) {
        this.context = context;
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName, tvEmail;
        ImageView ivProfile;
        public ViewHolder(View itemView){
            super(itemView);
            initViews();

        }

        private void initViews() {
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivProfile = itemView.findViewById(R.id.ivProfile);
        }

        public void bind(DataItem data){
            String fullname = data.getFirstName() + " " + data.getLastName();
            tvFullName.setText(fullname);
            tvEmail.setText(data.getEmail());
            Picasso.get().load(data.getAvatar()).into(ivProfile);
        }
    }
}
