package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class segment_add_view_activity extends AppCompatActivity {

    String topic_id,user_id,topic_name;
    EditText name,image_description;
    ImageView image;
    Button add_image_btn,save_btn;
    FirebaseDatabase rootnode;
    DatabaseReference reference;
    final private static String TOPIC="topic";
    final private static String topic_segment_list="topic_segment_list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segment_add_view);
        name=findViewById(R.id.name_edittext);
        image_description=findViewById(R.id.image_description_edittext);
        image=findViewById(R.id.imageView2);
        add_image_btn=findViewById(R.id.image_add_btn);
        save_btn=findViewById(R.id.segment_save_btn);

        rootnode=FirebaseDatabase.getInstance();
        reference=rootnode.getReference(TOPIC);


        //segment segment6 = new segment("segment6", R.drawable.steinsgate, "image_description", 0, 0);



       Intent intent=getIntent();
        topic_id=intent.getStringExtra("topic_id");
        user_id=intent.getStringExtra("user_id");
        topic_name=intent.getStringExtra("topic_name");





        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segment seg=new segment("1",name.getText().toString(), R.drawable.steinsgate, image_description.getText().toString(), 0, 0);
                save_segment(seg);
                Intent intent =new Intent(segment_add_view_activity.this,segment_display_activity.class);
                intent.putExtra("topic_id",topic_id);
                intent.putExtra("user_id",user_id);
                intent.putExtra("topic_name",topic_name);
                startActivity(intent);

            }
        });





    }
    public  void save_segment(segment seg){
        String key_id=reference.child(topic_id).child(topic_segment_list).push().getKey();
        seg.setId(topic_id+"/"+topic_segment_list+"/"+key_id);
        assert key_id != null;
        reference.child(topic_id).child(topic_segment_list).child(key_id).setValue(seg);

    }

}
