package com.example.mad_project;

import java.util.ArrayList;

public class user {
    String user_name;
    String user_email;
    ArrayList<String> user_topic_list;

    public user(String user_name, String user_email, ArrayList<String> user_topic_list) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_topic_list = user_topic_list;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public ArrayList<String> getUser_topic_list() {
        return user_topic_list;
    }

    public void setUser_topic_list(ArrayList<String> user_topic_list) {
        this.user_topic_list = user_topic_list;
    }
}
