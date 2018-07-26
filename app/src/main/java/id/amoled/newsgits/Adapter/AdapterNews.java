package id.amoled.newsgits.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.text.ParseException;
import java.util.Date;

import id.amoled.newsgits.Common.ISO8601DateParser;
import id.amoled.newsgits.activity.DetailNewsActivity;
import id.amoled.newsgits.Interface.ItemClickListener;
import id.amoled.newsgits.Model.WebSite;
import id.amoled.newsgits.R;

class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;
    final TextView title, desc, source;
    ImageView image;
    RelativeTimeTextView published;

    public NewsViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.list2_tv_title);
        desc = itemView.findViewById(R.id.list2_tv_desc);
        image = itemView.findViewById(R.id.list2_img_article);
        source = itemView.findViewById(R.id.list2_tv_source);
        published = itemView.findViewById(R.id.list2_tv_time);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}

public class AdapterNews extends RecyclerView.Adapter<NewsViewHolder> {
    private final Context context;
    private final WebSite webSite;

    public AdapterNews(Context ctx, WebSite webSite) {
        this.context = ctx;
        this.webSite = webSite;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_article, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, int position) {

        final String judul = webSite.getArticles().get(position).getTitle();
        final String desc = webSite.getArticles().get(position).getDescription();
        final String urlImage = webSite.getArticles().get(position).getUrlToImage();

        Date date;

        try {
            date = ISO8601DateParser.parse(webSite.getArticles().get(position).getPublishedAt());
            holder.published.setReferenceTime(date.getTime());
        }catch (ParseException e){
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (desc == null)
            holder.source.setText(R.string.unknown_author);
        else {
            holder.source.setText(webSite.getArticles().get(position).getAuthor());
        }

        if (judul.length() > 60)
            holder.title.setText(judul.substring(0, 60) + "...");
        else
            holder.title.setText(judul);

        if (desc == null)
            holder.desc.setText(R.string.no_desc);
        else {
            if (desc.length() > 40)
                holder.desc.setText(desc.substring(0, 40) + "...");
            else
                holder.desc.setText(desc);
        }

        if (urlImage == null) {
            Glide
                    .with(this.context)
                    .load(R.drawable.img_nothing)
                    .apply(RequestOptions.centerCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.img_loading))
                    .into(holder.image);
        } else {
            Glide
                    .with(this.context)
                    .load(urlImage)
                    .apply(RequestOptions.centerCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.img_loading))
                    .into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailNewsActivity.class);
                intent.putExtra("webUrl", webSite.getArticles().get(holder.getAdapterPosition()).getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return webSite.getArticles().size();
    }
}
