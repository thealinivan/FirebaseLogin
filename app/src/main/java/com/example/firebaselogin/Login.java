package com.example.firebaselogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class Login extends AppCompatActivity {

    //Objects instantiation
    private EditText email, password;
    private TextView forgotPass;
    private Button login;

    //Firebase DataBase
    private FirebaseDatabase database;
    private DatabaseReference dbRef;

    //FireBase authenticate object
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "LOGIN-WITH-FIREBASE: ";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get instance
        mAuth = FirebaseAuth.getInstance();

        //Links
        email = findViewById(R.id.l_email);
        password = findViewById(R.id.l_password);
        login = findViewById(R.id.l_button);
        forgotPass = findViewById(R.id.l_forgot_password);

        //On click listeners
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String _email = email.getText().toString().toLowerCase().trim();
                String _pass = password.getText().toString().toLowerCase().trim();
                if(_email.length() > 0 && _pass.length() > 0)
                {
                    //Try to sign in
                    mAuth.signInWithEmailAndPassword(_email, _pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        //On task completion
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(Login.this, "Failed sign in", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(Login.this, "Successfully signed in", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, Home.class));
                            }
                        }
                    })

                    //Handle failure
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e) 
                        {
                            Toast.makeText(Login.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Login.this, PasswordReset.class));
            }
        });

        //Authentication listener
        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                //Instatiate a FirebaseUser object and get the current user
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user !=null)
                {
                    //If user is signed Home activity will be inititated
                    Log.d(TAG, "User signed in");
                    if(user!=null) {
                        startActivity(new Intent(Login.this, Home.class));
                        finish();
                    }
                } else
                    {
                        //User is signed out
                        Log.d(TAG, "User signed out!");
                }
            }
        };

    }

    //Setting up the authentication listener
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }


}
