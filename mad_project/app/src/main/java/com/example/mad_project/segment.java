package com.example.mad_project;

import java.util.ArrayList;

public class segment {
    private String id;
    private String name;
    private String description;
    private Integer image;
    private int upvote;
    private int downvote;
    private int order_value;
    private ArrayList<String> upvote_user_list;
    private ArrayList<String> downvote_user_list;

    public segment(String id,String name,Integer image, String description, int upvote, int downvote) {
        this.id=id;
        this.name = name;
        this.image=image;
        this.description = description;
        this.upvote = upvote;
        this.downvote = downvote;
        this.order_value=upvote+downvote;
    }

    public ArrayList<String> getUpvote_user_list() {
        return upvote_user_list;
    }

    public void setUpvote_user_list(ArrayList<String> upvote_user_list) {
        this.upvote_user_list = upvote_user_list;
    }

    public ArrayList<String> getDownvote_user_list() {
        return downvote_user_list;
    }

    public void setDownvote_user_list(ArrayList<String> downvote_user_list) {
        this.downvote_user_list = downvote_user_list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrder_value() {
        return order_value;
    }

    public void setOrder_value() {
        this.order_value = getUpvote() + getDownvote();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public int getDownvote() {
        return downvote;
    }

    public void setDownvote(int downvote) {
        this.downvote = downvote;
    }
}
