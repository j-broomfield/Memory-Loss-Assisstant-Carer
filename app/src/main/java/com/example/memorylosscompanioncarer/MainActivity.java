package com.example.memorylosscompanioncarer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText loginEmailTxt;
    private EditText loginPasswordTxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();


        loginEmailTxt = (EditText) findViewById(R.id.login_email);
        loginPasswordTxt = (EditText) findViewById(R.id.login_pass);
        Button loginButton = (Button) findViewById(R.id.login_btn);
        Button createAccountButton = (Button) findViewById(R.id._acc_btn);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailTxt.getText().toString();
                String loginPassword = loginPasswordTxt.getText().toString();

                if ( loginEmail!=null && loginPassword!=null ){

                    mAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(MainActivity.this, "Successful Login",Toast.LENGTH_SHORT).show();
                                Intent mainIntent = new Intent(MainActivity.this, CarerHome.class);
                                startActivity(mainIntent);


                            }else{

                                String errorToast = task.getException().getMessage();
                                Toast.makeText(MainActivity.this, "error : "+errorToast,Toast.LENGTH_LONG).show();


                            }
                        }
                    });
                }

            }
        });

        //Create account button pressed
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(mainIntent);

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            // User is signed in
            Intent mainIntent = new Intent(MainActivity.this, CarerHome.class);
            startActivity(mainIntent);
        } else {
            // No user is signed in

            //finish();
        }
    }
}
