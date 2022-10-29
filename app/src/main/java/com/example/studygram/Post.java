package com.example.studygram;

public class Post {

    private String username;
    private String postdate;
    private int usericon_id;
    private String likecount;
    private int postImage_id;
    private String postContent;

    public Post() {
    }

    public Post(String username, String postdate, int usericon_id, String likecount, int postImage_id, String postContent) {
        this.username = username;
        this.postdate = postdate;
        this.usericon_id = usericon_id;
        this.likecount = likecount;
        this.postImage_id = postImage_id;
        this.postContent = postContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public int getUsericon_id() {
        return usericon_id;
    }

    public void setUsericon_id(int usericon_id) {
        this.usericon_id = usericon_id;
    }

    public String getLikecount() {
        return likecount;
    }

    public void setLikecount(String likecount) {
        this.likecount = likecount;
    }

    public int getPostImage_id() {
        return postImage_id;
    }

    public void setPostImage_id(int postImage_id) {
        this.postImage_id = postImage_id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
