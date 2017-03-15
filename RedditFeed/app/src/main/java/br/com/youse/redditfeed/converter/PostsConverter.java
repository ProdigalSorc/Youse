package br.com.youse.redditfeed.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.youse.redditfeed.models.Posts;

public class PostsConverter {
    public List<Posts> convertJSON(String json) {
        List<Posts> postsList = new ArrayList<>();
        Posts postItem;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++) {
                postItem = new Posts();
                JSONObject dataItem = children.getJSONObject(i).getJSONObject("data");
                postItem.setOrderList(i);
                postItem.setId(dataItem.getString("id"));
                postItem.setImage(dataItem.getString("thumbnail"));
                postItem.setTitle(dataItem.getString("title"));
                postItem.setUrl(dataItem.getString("url"));
                postItem.setScore(dataItem.getInt("score"));
                postsList.add(postItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postsList;
    }
}
