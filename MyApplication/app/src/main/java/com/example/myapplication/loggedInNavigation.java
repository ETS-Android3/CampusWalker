package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class loggedInNavigation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_navigation);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab7);
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab6);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab5);
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab4);
        fab.setOnClickListener(new View.OnClickListener() {
            boolean isFABOpen = false;

            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
            private void showFABMenu(){
                isFABOpen=true;
                fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
                fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
                fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
            }

            private void closeFABMenu(){
                isFABOpen=false;
                fab1.animate().translationY(0);
                fab2.animate().translationY(0);
                fab3.animate().translationY(0);
            }

        });

    }

    public void openUserClasses(View view){
        Intent intent  = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void openCampusMap(View view){
        Intent intent  = new Intent(this, CampusView.class);
        startActivity(intent);
    }


    //    Emergency Call Methods
    public void call911(View view){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:911"));
        startActivity(callIntent);
    }

    public void callCampusSecurity(View view){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:9105216235"));
        startActivity(callIntent);
    }

    public void callEmergencyContact(View view){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:9109999999"));
        startActivity(callIntent);
    }

}