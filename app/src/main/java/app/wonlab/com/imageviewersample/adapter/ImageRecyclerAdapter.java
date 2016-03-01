package app.wonlab.com.imageviewersample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.wonlab.com.imageviewersample.R;
import app.wonlab.com.imageviewersample.client.model.Media;
import app.wonlab.com.imageviewersample.common.Consumer;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
* Created by wonlab on 2016/03/01.
*/
public class ImageRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<Media> items;

    private Consumer<Integer> itemClickCallback;

    public ImageRecyclerAdapter(Context context, List<Media> items, Consumer<Integer> itemClickCallback) {
        this.context = context;
        this.items = items;
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_image, null);
        return new ImageViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Media item = items.get(position);
        ImageViewHolder imageViewHolder = (ImageViewHolder)viewHolder;
        imageViewHolder.itemTextView.setVisibility(View.GONE);
        Picasso.with(context).load(item.getImages().getThumbnail().getUrl()).error(R.drawable.noimage).into(imageViewHolder.itemImageView, new Callback() {
            @Override
            public void onSuccess() {
                imageViewHolder.itemTextView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onError() {
            }
        });
        imageViewHolder.itemTextView.setText(item.getCaption().getText());
        imageViewHolder.viewGroup.setTag(position);
        imageViewHolder.viewGroup.setOnClickListener(this::onClick);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void onClick(View view) {
        itemClickCallback.apply((Integer) view.getTag());
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.group_image)
        ViewGroup viewGroup;

        @Bind(R.id.image_item)
        ImageView itemImageView;

        @Bind(R.id.label_item)
        TextView itemTextView;

        public ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
