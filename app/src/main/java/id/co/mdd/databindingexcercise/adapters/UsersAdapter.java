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
import id.co.mdd.databindingexcercise.models.user.DataItem;
import id.co.mdd.databindingexcercise.models.user.UserModel;
import retrofit2.Callback;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> implements Filterable {

    private List<DataItem> users = new ArrayList<>();
    private static Context context;
    private static List<DataItem> usersFiltered;


    public void setUsersAdapter(Context context, List users) {
        this.context = context;
        this.users = users;
        this.usersFiltered = users;
        notifyDataSetChanged();
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.bind(usersFiltered.get(position));
    }


    // filter
    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();
                if (str.isEmpty()){
                    usersFiltered = users;
                } else {
                    List<DataItem> filtered = new ArrayList<>();
                    for (DataItem data :users) {
                        if (data.getFirstName().toLowerCase().contains(str.toLowerCase()) || data.getLastName().toLowerCase().contains(str.toLowerCase())) {
                            filtered.add(data);
                        }
                    }
                    usersFiltered = filtered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usersFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usersFiltered = (ArrayList<DataItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    @Override
    public int getItemCount() {
        return usersFiltered.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFullName, tvEmail;
        ImageView ivProfile;
        CardView cardView;
        public ViewHolder(View itemView){
            super(itemView);
            initViews();

        }

        private void initViews() {
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            cardView = itemView.findViewById(R.id.card_view);

        }

        public void bind(DataItem data){
            String fullname = data.getFirstName() + " " + data.getLastName();
            tvFullName.setText(fullname);
            tvEmail.setText(data.getEmail());
            Picasso.get().load(data.getAvatar()).into(ivProfile);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent;
                    intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra("name_users", usersFiltered.get(position).getFirstName()+
                            " "+
                            usersFiltered.get(position).getLastName());
                    intent.putExtra("email_users", usersFiltered.get(position).getEmail());
                    intent.putExtra("image_users", usersFiltered.get(position).getAvatar());
                    context.startActivity(intent);
                }
            });
        }
    }
}
