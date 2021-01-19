package com.example.mad_project;

public class topic_list {
    String topic_name;
    String topic_id;
    String user_id;

    @Override
    public String toString() {
        return getTopic_name();
    }

    public topic_list(String topic_name, String topic_id,String user_id) {
        this.topic_name = topic_name;
        this.topic_id = topic_id;
        this.user_id=user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
}
