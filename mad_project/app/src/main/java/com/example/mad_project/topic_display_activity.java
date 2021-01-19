package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class topic_display_activity  extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseDatabase rootnode;
    ListView topic_list_view;

    final private static String TOPIC="topic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list_view);
        topic_list_view = findViewById(R.id.topic_list_View);
        fauth = FirebaseAuth.getInstance();
        rootnode = FirebaseDatabase.getInstance();
        DatabaseReference reference;

       reference =rootnode.getReference(TOPIC);



        ArrayList<topic_list> topicArrayList= new ArrayList<>();
        topic_list topic_temp = new topic_list("topic name","topic_id","user_id");
        //topicArrayList.add(topic_temp);
        //topicArrayList.add(topic_temp);
         // go to each child in topic and get the data and add it to the topicArraylist

        //topic_list_view.setAdapter(topic_adapter);
        ArrayAdapter<topic_list> topic_adapter=new ArrayAdapter<>(topic_display_activity.this,R.layout.support_simple_spinner_dropdown_item,topicArrayList);
        topic_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String topic_id= topicArrayList.get(position).getTopic_id();
                String user_id=topicArrayList.get(position).getUser_id();
                String topic_name=topicArrayList.get(position).getTopic_name();
                Intent intent =new Intent(topic_display_activity.this ,segment_display_activity.class);
                intent.putExtra("topic_id",topic_id);
                intent.putExtra("user_id",user_id);
                intent.putExtra("topic_name",topic_name);
                startActivity(intent);
            }
        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                topicArrayList.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                   // topic t1=snapshot1.getValue(topic.class);
                    topic_list topic_each = new topic_list(snapshot1.child("topic_name").getValue().toString(),snapshot1.child("topic_id").getValue().toString(),snapshot1.child("user_id").getValue().toString());
                    //topic_list t1_list= new topic_list(t1.getTopic_name(),t1.getTopic_id());
                      topicArrayList.add(topic_each);
                }
                Toast.makeText(topic_display_activity.this,"database read",Toast.LENGTH_LONG).show();
                topic_list_view.setAdapter(topic_adapter);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(topic_display_activity.this,"error reading database",Toast.LENGTH_LONG).show();
            }
        });






        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(topic_display_activity.this,topic_add_view_activity.class));

            }
        });

    }



}
