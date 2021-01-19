package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class topic_add_view_activity extends AppCompatActivity {

    EditText new_topic_name;
    Button  new_topic_add_btn;
    FirebaseAuth fauth;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    final private static String TOPIC="topic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_add_view);
        new_topic_name = findViewById(R.id.new_topic_name);
        new_topic_add_btn= findViewById(R.id.new_topic_add_btn);
        fauth = FirebaseAuth.getInstance();
        rootnode = FirebaseDatabase.getInstance();
        reference =rootnode.getReference(TOPIC);

        new_topic_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user= fauth.getCurrentUser();
                assert user != null;
                String user_id=user.getUid();
                String key_id=reference.push().getKey();
                String topic_name= new_topic_name.getText().toString().trim();
                create_topic(v,key_id,topic_name,user_id);
                startActivity(new Intent(topic_add_view_activity.this,topic_display_activity.class));

            }
        });






    }
    public void create_topic(View v,String key_id,String topic_name,String user_id){

        ArrayList<segment> topic_segment_list =new ArrayList<>();
        //segment segment1 = new segment("segment1", R.drawable.steinsgate, "image_description", 0, 0);
        //topic_segment_list.add(segment1);
        topic new_topic =new topic(key_id,topic_name,user_id,topic_segment_list);
        reference.child(key_id).setValue(new_topic).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Snackbar.make(v, "topic added successfully", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(v, "unsuccessfull", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
