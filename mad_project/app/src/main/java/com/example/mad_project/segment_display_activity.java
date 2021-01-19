package com.example.mad_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class segment_display_activity extends AppCompatActivity {

    FirebaseDatabase rootnode;
    FirebaseAuth fauth;
   com.baoyz.swipemenulistview.SwipeMenuListView  segment_listview;
    TextView textview_topic_name;
    FloatingActionButton segment_add_btn;
    String topic_id,user_id,topic_name;
    String s;
    final private static String TOPIC="topic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segment_list_view);
        fauth =FirebaseAuth.getInstance();
        segment_listview = findViewById(R.id.segment_list_View);
        segment_add_btn=findViewById(R.id.segment_add_btn);
        textview_topic_name=findViewById(R.id.textView);
        rootnode=FirebaseDatabase.getInstance();
        DatabaseReference reference=rootnode.getReference(TOPIC);
        ArrayList<segment>  segmentArrayList=new ArrayList<>();

//        segment segment1 = new segment("segment1", R.drawable.steinsgate, "image_description", 0, 0);
//        segment segment2 = new segment("segment2", R.drawable.steinsgate, "image_description", 0, 0);
//        segment segment3 = new segment("segment3", R.drawable.steinsgate, "image_description", 0, 0);
//        segment segment4 = new segment("segment4", R.drawable.steinsgate, "image_description", 0, 0);
//        segment segment5 = new segment("segment5", R.drawable.steinsgate, "image_description", 0, 0);
       // segment segment6 = new segment("1","segment6", R.drawable.steinsgate, "image_description", 0, 0);

//        segmentArrayList.add(segment1);
//        segmentArrayList.add(segment2);
//        segmentArrayList.add(segment3);
//        segmentArrayList.add(segment4);
//        segmentArrayList.add(segment5);
       //segmentArrayList.add(segment6);


        SegmentListAdapter adapter= new SegmentListAdapter(this, R.layout.segment_list_view_template, segmentArrayList);
        // getting data from database
        Intent intent =getIntent();
        topic_id=intent.getStringExtra("topic_id");
        user_id=intent.getStringExtra("user_id");
        topic_name=intent.getStringExtra("topic_name");


        Toast.makeText(segment_display_activity.this,topic_id,Toast.LENGTH_LONG).show();

        textview_topic_name.setText(topic_name);
        reference.child(topic_id).child("topic_segment_list").orderByChild("order_value").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                   segmentArrayList.clear();
                for(DataSnapshot snapshot1 :snapshot.getChildren()){

                    String id= Objects.requireNonNull(snapshot1.child("id").getValue()).toString();
                    String name = Objects.requireNonNull(snapshot1.child("name").getValue()).toString();
                    String description = Objects.requireNonNull(snapshot1.child("description").getValue()).toString();
                    Integer image =Integer.valueOf(Objects.requireNonNull(snapshot1.child("image").getValue()).toString());
                    int upvote=Integer.parseInt(Objects.requireNonNull(snapshot1.child("upvote").getValue()).toString());
                    int downvote=Integer.parseInt(Objects.requireNonNull(snapshot1.child("downvote").getValue()).toString());
                      // s=s.concat(id + "1");
                    segmentArrayList.add( new segment(id,name,image, description, upvote, downvote));
                }
//                ArrayList<segment> segmentArrayList_temp =t1.getTopic_segment_list();
//                for(segment seg : segmentArrayList_temp){
//                    segmentArrayList.add(seg);
//                    Toast.makeText(segment_display_activity.this,seg.getName(),Toast.LENGTH_LONG).show();
//
//                }

                segment_listview.setAdapter(adapter);
                Toast.makeText(segment_display_activity.this, "segment list",Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        //adding onclick function to segment_add_btn

        segment_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(segment_display_activity.this,segment_add_view_activity.class);
                intent.putExtra("topic_id",topic_id);
                intent.putExtra("user_id",user_id);
                intent.putExtra("topic_name",topic_name);
                startActivity(intent);
                Toast.makeText(segment_display_activity.this,"clicked segment add btn",Toast.LENGTH_LONG).show();
            }
        });
        if(user_id.equals(Objects.requireNonNull(fauth.getCurrentUser()).getUid())){
            segment_add_btn.setVisibility(View.VISIBLE);
            SwipeMenuCreator creator = new SwipeMenuCreator() {

                @Override
                public void create(SwipeMenu menu) {
                    // create "open" item


                    // create "delete" item
                    SwipeMenuItem deleteItem = new SwipeMenuItem(
                            getApplicationContext());
                    // set item background
                    deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                            0x3F, 0x25)));
                    // set item width
                    deleteItem.setWidth(170);
                    // set a icon
                    deleteItem.setIcon(R.drawable.delete_btn_asset);
                    // add to menu
                    menu.addMenuItem(deleteItem);
                }
            };

// set creator
            segment_listview.setMenuCreator(creator);


            // adding menu click listener

            segment_listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    if (index == 0) {//Toast.makeText(segment_display_activity.this, "position" + segmentArrayList.get(position).getId(), Toast.LENGTH_LONG).show();

                        String id = segmentArrayList.get( segmentArrayList.size()-1-position).getId();
                        //segmentArrayList.remove(position);
                        rootnode.getReference("topic/" + id).removeValue();
//                        finish();
//                        startActivity(getIntent());
                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });

            segment_listview.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        }













    }
    // adding swipe to delete menu
//    public void get_swipe_delete(ArrayList<segment> segmentArrayList){
//
//    }
}
