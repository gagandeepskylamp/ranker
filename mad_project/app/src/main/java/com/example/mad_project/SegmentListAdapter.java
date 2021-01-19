package com.example.mad_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class SegmentListAdapter  extends ArrayAdapter<segment> {

    private static final String TAG = "SegmentListAdapter";
    final private Context mContext;
     final private int mResource;
     FirebaseAuth fauth;
     FirebaseDatabase rootnode=FirebaseDatabase.getInstance();
     DatabaseReference reference=rootnode.getReference("topic");
    public SegmentListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<segment> objects) {
        super(context, resource, objects);
        mResource=resource;
        mContext=context;


    }

    @Nullable
    @Override
    public segment getItem(int position) {
        return super.getItem(getCount()-1-position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id=getItem(position).getId();
        String name=getItem(position).getName();
        String image_description=getItem(position).getDescription();
        Integer image=getItem(position).getImage();
        final  int upvote=getItem(position).getUpvote();
         final int downvote=getItem(position).getDownvote();


        // segment segment_default= new segment(name,image,image_description,upvote,downvote);

        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView tv_name=(TextView) convertView.findViewById(R.id.name);
        ImageView iv_image_view=(ImageView) convertView.findViewById(R.id.image_view);
        TextView tv_image_description=(TextView) convertView.findViewById(R.id.description);
           Button bt_upvote=(Button) convertView.findViewById(R.id.up_button);
           Button bt_downvote=(Button) convertView.findViewById(R.id.down_button);
         tv_name.setText(name);
         iv_image_view.setImageResource(image);
         tv_image_description.setText(image_description);
         bt_upvote.setText(Integer.toString(upvote));
         bt_downvote.setText(Integer.toString(downvote));
         fauth=FirebaseAuth.getInstance();
         bt_upvote.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String user_id=fauth.getCurrentUser().getUid();

                 rootnode.getReference("topic/" + id + "/upvote_user_list").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull  DataSnapshot snapshot) {
                         boolean upvote_ctr=true;
                         for(DataSnapshot snapshot1: snapshot.getChildren()){
                             if (user_id.equals(snapshot1.getValue().toString())) {
                                 upvote_ctr = false;
                             }
                         }
                         if(upvote_ctr) {
                             String key_id=rootnode.getReference("topic/" + id + "/upvote_user_list").push().getKey();
                             rootnode.getReference("topic/" + id + "/upvote_user_list").child(key_id).setValue(user_id);
                             getItem(position).setUpvote(upvote + 1);
                             final int new_upvote = getItem(position).getUpvote();

                             Utils u = new Utils();
                             //u.showToast(mContext, id);
                             String new_upvote_s = Integer.toString(new_upvote);
                             bt_upvote.setText(new_upvote_s);
                             // reference.child(id).child("upvote").setValue(new_upvote_s);
                             rootnode.getReference("topic/" + id + "/upvote").setValue(new_upvote);
                             change_order_value(position, id);
//                             bt_upvote.setEnabled(false);
//                             bt_upvote.setVisibility(View.INVISIBLE);
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull  DatabaseError error) {

                     }
                 });


             }
         });
         bt_downvote.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String user_id=fauth.getCurrentUser().getUid();

                 rootnode.getReference("topic/" + id + "/downvote_user_list").addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull  DataSnapshot snapshot) {
                         boolean downvote_ctr=true;
                         for(DataSnapshot snapshot1: snapshot.getChildren()){
                             if (user_id.equals(snapshot1.getValue().toString())) {
                                 downvote_ctr = false;
                             }
                         }
                         if(downvote_ctr) {
                             String key_id=rootnode.getReference("topic/" + id + "/downvote_user_list").push().getKey();
                             rootnode.getReference("topic/" + id + "/downvote_user_list").child(key_id).setValue(user_id);
                             getItem(position).setDownvote(downvote - 1);
                             final int new_downvote = getItem(position).getDownvote();
                             Utils u = new Utils();
                     //  u.showToast(mContext, Integer.toString(new_downvote));
                             String new_downvote_s = Integer.toString(new_downvote);
                              bt_downvote.setText(new_downvote_s);
                     //reference.child(id).child("downvote").setValue(new_downvote_s);
                             rootnode.getReference("topic/" + id + "/downvote").setValue(new_downvote);
                             change_order_value(position, id);
//                             bt_upvote.setEnabled(false);
//                             bt_upvote.setVisibility(View.INVISIBLE);
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull  DatabaseError error) {

                     }
                 });



             }
         });
         return convertView;












    }
    public void change_order_value(int position,String id){
        getItem(position).setOrder_value();
        rootnode.getReference("topic/"+id+"/order_value").setValue(getItem(position).getOrder_value());
    }
}

