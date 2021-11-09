package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class loggedInNavigation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_navigation);

    }

    public void openUserClasses(View view){
        Intent intent  = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void openCampusMap(View view){
        Intent intent  = new Intent(this, FloorPlans.class);
        startActivity(intent);
    }


    public void emergencyCall(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:9105216235"));
        startActivity(callIntent);
    }

}