package com.example.firebaselogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaselogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {

    //Objects instantiation
    private EditText email;
    private Button reset;

    //FireBase authenticate object
    private FirebaseAuth mAuth;

    private static final String TAG = "PASS_RESET-FIREBASE: ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        //Links
        email = findViewById(R.id.ps_email);
        reset = findViewById(R.id.ps_button);

        //Get instance
        mAuth = FirebaseAuth.getInstance();

        //On click listeners
        reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Send password reset link via mail
                mAuth.sendPasswordResetEmail(email.getText().toString().toLowerCase().trim())
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    //Feedback on complete
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        Log.d(TAG, "Email sent");
                        Toast.makeText(PasswordReset.this, "Check your email for password reset link", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    //feedback on failure
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(PasswordReset.this, "If this email is matching an existing user you will receive a password reset link in your mail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
