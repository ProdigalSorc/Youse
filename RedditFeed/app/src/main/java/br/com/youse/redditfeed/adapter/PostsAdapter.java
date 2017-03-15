package br.com.youse.redditfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.youse.redditfeed.activity.R;
import br.com.youse.redditfeed.api.DownloadImageTask;
import br.com.youse.redditfeed.models.Posts;

public class PostsAdapter extends BaseAdapter {

    private final List<Posts> postsList;
    private final Context context;

    public PostsAdapter(Context context, List<Posts> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int position) {
        return postsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postsList.get(position).getOrderList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Posts posts = postsList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        if (posts.getImage() != "self") {
            new DownloadImageTask((ImageView) view.findViewById(R.id.item_image))
                    .execute(posts.getImage());
        } else {
            ImageView imageView = (ImageView) view.findViewById(R.id.item_image);
            imageView.setImageResource(R.mipmap.ic_unknown);
        }

        TextView itemTitle = (TextView) view.findViewById(R.id.item_title);
        itemTitle.setText(posts.getTitle());

        TextView itemScore = (TextView) view.findViewById(R.id.item_score);
        itemScore.setText("Votes: " + String.valueOf(posts.getScore()));

        return view;
    }
}
