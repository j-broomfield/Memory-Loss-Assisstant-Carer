package com.example.memorylosscompanioncarer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MotionActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    DatabaseReference reference;
    Button locButton;
    TextView mvmtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion);

        FirebaseApp.initializeApp(this);

        mvmtTime = findViewById(R.id.timeMvmtTxt);
        locButton = findViewById(R.id.lastLocBtn);
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        reference.child("Movement").child("Loc1").child("Time").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String TimeRecorded = dataSnapshot.getValue(String.class);
                mvmtTime.setText(TimeRecorded);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        locButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(MotionActivity.this, LocationViewerActivity.class);
                startActivity(mainIntent);

            }
        });


    }
}
