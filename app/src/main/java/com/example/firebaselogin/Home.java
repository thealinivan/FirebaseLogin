package com.example.firebaselogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firebaselogin.R;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    //Object instantiation
    private Button signOut;

    //FireBase authenticate object
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Get instance
        mAuth = FirebaseAuth.getInstance();

        signOut = findViewById(R.id.h_sign_out_btn);

        //Handle sign out
        signOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                Toast.makeText(Home.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, Login.class));
            }
        });

    }

}
