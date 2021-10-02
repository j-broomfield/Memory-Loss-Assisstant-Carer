package com.example.memorylosscompanioncarer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class WebcamViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcam_view);
        Button homeBtn = (Button) findViewById(R.id.webcamHomeButton);

        WebView webcamView = (WebView) findViewById(R.id.webcamWebpage);
        webcamView.getSettings().setJavaScriptEnabled(true);
        webcamView.loadUrl("https://www.youtube.com/embed/live_stream?channel=kzk_G4K0Kh3q75VXuK5rZA");


        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent loginIntent = new Intent(WebcamViewActivity.this, CarerHome.class);
                startActivity(loginIntent);
            }
        });


    }
}
