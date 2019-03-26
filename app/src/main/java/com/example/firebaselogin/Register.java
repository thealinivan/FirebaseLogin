package com.example.firebaselogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    //Objects instantiation
    private EditText email, password;
    private Button register;

    //FireBase authenticate object
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get instance
        mAuth = FirebaseAuth.getInstance();

        //Links
        email = findViewById(R.id.r_email);
        password = findViewById(R.id.r_password);
        register = findViewById(R.id.r_button);

        //On click listeners
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Create Authentication User
                mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //User Feedback
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Register.this, email.getText().toString() + " registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Login.class));
                        } else
                            {
                                Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }


}
