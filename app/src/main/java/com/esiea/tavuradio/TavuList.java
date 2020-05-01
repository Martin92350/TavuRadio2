package com.esiea.tavuradio;


import android.app.DownloadManager;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.io.InputStream;


import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TavuList extends RecyclerView.Adapter<TavuList.ViewHolder> {
    private List<Actu> values;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
         TextView txtHeader;
         TextView txtFooter;
         TextView txtOnTheSide;
         ImageView imgUrl ;
         View layout;

         ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtOnTheSide =(TextView) v.findViewById(R.id.dateEtHeure);
            imgUrl = (ImageView) v.findViewById(R.id.icon) ;




         }
    }

    public void add(int position, Actu item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TavuList(List<Actu> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TavuList.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Actu currentActu = values.get(position);

        holder.txtOnTheSide.setText(currentActu.getDateEtHeure());


        holder.txtHeader.setText(currentActu.getTitre());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });

        holder.txtFooter.setText(currentActu.getDescription());

        Glide.with(holder.itemView.getContext()).load(currentActu.getImageUrl()).into(holder.imgUrl);




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}
