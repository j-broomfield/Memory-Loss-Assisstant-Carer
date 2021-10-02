package com.example.memorylosscompanioncarer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class CarerHome extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer_home);

        mAuth = FirebaseAuth.getInstance();

        Button webcam = (Button) findViewById(R.id.webcamButton);
        Button chat = (Button) findViewById(R.id.messagePatentButton);
        Button logoutButton = (Button) findViewById(R.id.logout);
        Button notes = (Button) findViewById(R.id.notes_btn);
        Button location = (Button) findViewById(R.id.getLocationButton);
        Button movement = (Button) findViewById(R.id.movementCheckButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mAuth.signOut();

                Intent loginIntent = new Intent(CarerHome.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        webcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent loginIntent = new Intent(CarerHome.this, WebcamViewActivity.class);
                startActivity(loginIntent);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent loginIntent = new Intent(CarerHome.this, MessageActivity.class);
                startActivity(loginIntent);
            }
        });
        movement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent loginIntent = new Intent(CarerHome.this, MotionActivity.class);
                startActivity(loginIntent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent loginIntent = new Intent(CarerHome.this, LocationViewerActivity.class);
                startActivity(loginIntent);
            }
        });


        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.keep");
                if (launchIntent != null) {
                 startActivity(launchIntent);
                  } else {
                    Uri marketUri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.keep&hl=en_GB");
                 Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                 startActivity(marketIntent);
                }

            }
        });

    }

}
