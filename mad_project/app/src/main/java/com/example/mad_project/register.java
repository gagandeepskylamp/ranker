package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class register extends AppCompatActivity {

    FirebaseAuth fauth;
    FirebaseDatabase rootnode;
     DatabaseReference reference;
    EditText RName, RPass, REmail;
    Button regis;
    user new_user;
   final private static String USER="user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RName = findViewById(R.id.Rname);
        RPass = findViewById(R.id.Rpass);
        REmail = findViewById(R.id.Remail);
        regis = findViewById(R.id.reg);

        fauth = FirebaseAuth.getInstance();
        rootnode = FirebaseDatabase.getInstance();
        reference =rootnode.getReference(USER);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = REmail.getText().toString().trim();
                String pass = RPass.getText().toString().trim();
                if (TextUtils.isEmpty(emailid)) {
                    REmail.setError("Email-ID is required.");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    RPass.setError("Password is required.");
                    return;
                }
                ArrayList<String> topic_id_list =new ArrayList<>();
                topic_id_list.add("default");
                topic_id_list.add("3123");
                 new_user=new user(RName.getText().toString().trim(),REmail.getText().toString().trim(),topic_id_list);
                registerUser(emailid, pass);

            }


        });
    }
        public void registerUser( String emailid , String pass ){
            fauth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    System.out.print("hi");
                    if (task.isSuccessful()) {
//
                        Toast.makeText(register.this, "user created", Toast.LENGTH_SHORT).show();
                        FirebaseUser user= fauth.getCurrentUser();
                        updateUI(user);
                        //startActivity(new Intent(register.this,MainActivity.class));
                    } else {
                        Toast.makeText(register.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void updateUI(FirebaseUser currentuser){
          //String key_id=reference.push().getKey();
          reference.child(currentuser.getUid()).setValue(new_user);
            startActivity(new Intent(register.this,MainActivity.class));

        }

    }




