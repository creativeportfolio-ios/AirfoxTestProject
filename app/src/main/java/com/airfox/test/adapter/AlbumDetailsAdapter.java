package com.airfox.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airfox.test.R;
import com.airfox.test.api.response.AlbumDetails;
import com.airfox.test.ui.fullalbumdetails.FullAlbumDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailsAdapter extends RecyclerView.Adapter<AlbumDetailsAdapter.ViewHolder> {

    Context context;
    List<AlbumDetails> albumDetailsList;

    public AlbumDetailsAdapter(Context context, List<AlbumDetails> albumDetailsList) {
        this.context = context;
        this.albumDetailsList = albumDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album_details_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumDetails albumDetails = albumDetailsList.get(position);

        holder.txtAlbumName.setText("Album Title : " +albumDetails.getTitle());
        holder.txtAlbumId.setText("Album Id : " + albumDetails.getAlbumId());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .load(albumDetails.getThumbnailUrl())
                .apply(requestOptions)
                .into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() {
        return albumDetailsList != null ? albumDetailsList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtAlbumId)
        TextView txtAlbumId;
        @BindView(R.id.txtAlbumName)
        TextView txtAlbumName;
        @BindView(R.id.imgAlbum)
        ImageView imgAlbum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FullAlbumDetailsActivity.class);
                    intent.putExtra(FullAlbumDetailsActivity.ARG_ALBUM_DETAILS, albumDetailsList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
