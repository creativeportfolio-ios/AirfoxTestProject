package com.airfox.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airfox.test.R;
import com.airfox.test.api.response.Album;
import com.airfox.test.ui.albumdetails.AlbumDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    List<Album> albumList;

    public AlbumAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_album_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumList.get(position);

        holder.txtAlbumName.setText("Album Title : " + album.getTitle());
    }

    @Override
    public int getItemCount() {
        return albumList != null ? albumList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtAlbumName)
        TextView txtAlbumName;
        @BindView(R.id.rootView)
        CardView rootView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AlbumDetailsActivity.class);
                    intent.putExtra(AlbumDetailsActivity.ARG_ALBUM, albumList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
