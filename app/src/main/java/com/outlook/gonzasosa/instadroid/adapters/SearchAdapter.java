package com.outlook.gonzasosa.instadroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.outlook.gonzasosa.instadroid.R;
import com.outlook.gonzasosa.instadroid.models.search.SearchUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<SearchUser> listUsers;

    public SearchAdapter (Context context, ArrayList<SearchUser> users) {
        this.context = context;
        this.listUsers = users;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.search_item, parent, false);
        return new SearchViewHolder (view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchUser user = listUsers.get (position);

        holder.setTvUsername (user.getUsername ());
        holder.setTvLastname (user.getLastName ());
        holder.setImgProfile (user.getProfilePicture ());
    }

    @Override
    public int getItemCount() {
        return listUsers.size ();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProfile;
        private TextView tvUsername;
        private TextView tvLastname;

        public SearchViewHolder(View itemView) {
            super(itemView);

            imgProfile = (ImageView) itemView.findViewById (R.id.imgProfile);
            tvUsername = (TextView) itemView.findViewById (R.id.tvUsername);
            tvLastname = (TextView) itemView.findViewById (R.id.tvLastname);
        }

        public void setTvUsername (String username) {
            this.tvUsername.setText (username);
        }

        public void setTvLastname (String lastname) {
            this.tvLastname.setText (lastname);
        }

        public void setImgProfile (String url) {
            Picasso.with (context)
                    .load (url)
                    .into (this.imgProfile);
        }
    }
}
