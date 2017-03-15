package br.com.youse.redditfeed.models;

import java.io.Serializable;

public class Posts implements Serializable {

    private long orderList;
    private String id;
    private String image;
    private String title;
    private String url;
    private int score;

    public long getOrderList() {
        return orderList;
    }

    public void setOrderList(long orderList) {
        this.orderList = orderList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
