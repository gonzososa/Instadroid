package com.outlook.gonzasosa.instadroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.outlook.gonzasosa.instadroid.R;
import com.outlook.gonzasosa.instadroid.models.media.MediaElement;
import com.outlook.gonzasosa.instadroid.ui.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder>  {
    Context context;
    ArrayList<MediaElement> mediaElements;

    public MediaAdapter (Context context, ArrayList<MediaElement> mediaElements) {
        this.context = context;
        this.mediaElements = mediaElements;
    }

    @Override
    public MediaViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.media_item, parent, false);
        return new MediaViewHolder (view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        MediaElement element = mediaElements.get (position);
        if (element == null) return;

//        holder.setMediaTitle (element.getCaption() != null ?
//                                element.getCaption().getText () :
//                                "");

        Date date = new Date ();
        date.setTime (element.getCreateDateTime() * 1000);
        holder.setMediaDate (date);

        holder.setMediaImage (element.getImages().getLowResolution().getUrl ());
    }

    @Override
    public int getItemCount() {
        return mediaElements.size();
    }

    public class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMedia;
        TextView tvMediaDate;
        //TextView tvMediaTitle;

        public MediaViewHolder(View itemView) {
            super(itemView);

            imgMedia = (ImageView) itemView.findViewById (R.id.imgMedia);
            tvMediaDate = (TextView) itemView.findViewById (R.id.tvMediaDate);
            //tvMediaTitle = (TextView) itemView.findViewById (R.id.tvMediaTitle);
        }

       /* public void setMediaTitle (String title) {
            tvMediaTitle.setText (title);
        }*/

        public void setMediaDate (Date date) {
            tvMediaDate.setText (date.toString ());
        }

        public void setMediaImage (String url) {
            Picasso.with (context)
                    .load (url)
                    .into (imgMedia);
        }
    }
}
