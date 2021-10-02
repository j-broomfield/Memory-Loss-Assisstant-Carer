package com.example.memorylosscompanioncarer;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LocationViewerActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference reference;
    TextView TimeViewer;
    public String Longitude ;
    public String Latitude ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_viewer);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        reference.child("Location").child("Loc1").child("Time").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String TimeRecorded = dataSnapshot.getValue(String.class);
                Toast.makeText(LocationViewerActivity.this, "Last updated " +TimeRecorded,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Longitude = GetLongitude();
        Latitude = GetLatitude();






    }

    private void LaunchMap(String retrevedLatitude, String retrevedLongitude) {
        WebView mapView = (WebView) findViewById(R.id.MapViewer);
        mapView.loadUrl("http://maps.google.com/maps?q="+ retrevedLatitude +","+ retrevedLongitude);
    }

    private String GetLatitude() {
        final String[] Lat1 = new String[1];
        reference.child("Location").child("Loc1").child("Latitude").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Lat1[0] = dataSnapshot.getValue(String.class);
                LaunchMap(Lat1[0], Longitude);
                Intent ReturnHome = new Intent(LocationViewerActivity.this, CarerHome.class);
                startActivity(ReturnHome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  Lat1[0];
    }

    public String GetLongitude() {
        reference.child("Location").child("Loc1").child("Longitude").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Longitude = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return Longitude;
    }
}
