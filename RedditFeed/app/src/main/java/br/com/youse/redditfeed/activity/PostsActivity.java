package br.com.youse.redditfeed.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.youse.redditfeed.adapter.PostsAdapter;
import br.com.youse.redditfeed.api.UpdatePostsTask;
import br.com.youse.redditfeed.converter.PostsConverter;
import br.com.youse.redditfeed.interfaces.AsyncResponse;
import br.com.youse.redditfeed.models.Posts;
import br.com.youse.redditfeed.utils.Utility;

public class PostsActivity extends AppCompatActivity implements AsyncResponse {

    private ListView listViewPosts;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        listViewPosts = (ListView) findViewById(R.id.list_posts);
        listViewPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                if (Utility.isConnected(getApplicationContext())) {
                    Posts posts = (Posts) listViewPosts.getItemAtPosition(position);
                    Intent intent = new Intent(PostsActivity.this, PostDetailActivity.class);
                    intent.putExtra("posts", posts);
                    startActivity(intent);
                }else {
                    displayDialog();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadPostList();
    }

    private void downloadPostList() {
        if (Utility.isConnected(getApplicationContext())) {
            UpdatePostsTask asyncTask = new UpdatePostsTask(this);
            asyncTask.delegate = this;
            asyncTask.execute();
        } else {
            displayDialog();
        }
    }

    @Override
    public void processBeforeAsyncTask() {
        progressDialog = ProgressDialog.show(this, "Please Wait", "Loading...", true, true);
    }

    @Override
    public void processFinish(String result) {
        PostsConverter postsConverter = new PostsConverter();
        if (result != null) {
            updateAdapter(postsConverter.convertJSON(result));
        }
    }

    public void updateAdapter(List<Posts> postsList){
        PostsAdapter adapter = new PostsAdapter(this, postsList);
        listViewPosts.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (progressDialog != null)  progressDialog.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_posts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_update:
                downloadPostList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayDialog() {
        Utility.alertSimpleDialog(PostsActivity.this,
                "Connection Error",
                "Please check your Internet connection");
    }
}
