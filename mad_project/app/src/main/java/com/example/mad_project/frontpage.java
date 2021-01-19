
package com.example.mad_project;
import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
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

public class frontpage extends AppCompatActivity {

    FirebaseAuth fauth;
    EditText LPass, LEmail;
    Button log;
    Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);
        fauth = FirebaseAuth.getInstance();
        LEmail = findViewById(R.id.e1);
        LPass = findViewById(R.id.text2);
        log = findViewById(R.id.login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String emailid = LEmail.getText().toString().trim();
                String pass = LPass.getText().toString().trim();
                if (TextUtils.isEmpty(emailid))
                {
                    LEmail.setError("Email-ID is required.");
                    return;
                }
                if (TextUtils.isEmpty(pass))
                {
                    LPass.setError("Password is required.");
                    return;
                }
                fauth.signInWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            System.out.print("hello");
                            Toast.makeText(frontpage.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(frontpage.this, topic_display_activity.class));
                        } else {
                            Toast.makeText(frontpage.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        sign = findViewById(R.id.signup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(frontpage.this, register.class));
            }
        });


    }
}

