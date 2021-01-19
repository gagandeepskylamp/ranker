package com.example.mad_project;

import java.util.ArrayList;
import java.util.List;

public class topic {
    String topic_id;
    String topic_name;
    String user_id;
    ArrayList<segment> topic_segment_list;


    public topic(String topic_id, String topic_name,String user_id, ArrayList<segment> topic_segment_list) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.topic_segment_list = topic_segment_list;
        this.user_id=user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return getTopic_name();
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public ArrayList<segment> getTopic_segment_list() {
        return topic_segment_list;
    }

    public void setTopic_segment_list(ArrayList<segment> topic_segment_list) {
        this.topic_segment_list = topic_segment_list;
    }
}
