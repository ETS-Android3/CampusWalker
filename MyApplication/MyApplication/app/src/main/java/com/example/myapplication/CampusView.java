package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.Toast;

public class CampusView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campusview);





        SubsamplingScaleImageView imageView = findViewById(R.id.imageViewCampusMap);
        imageView.setImage(ImageSource.resource(R.drawable.campus_map));

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(e.getX(), e.getY());
                    if ((sCoord.x > 1650 && sCoord.y > 3700) && (sCoord.y < 4020 && sCoord.x < 1900)) {
                        System.out.println(sCoord.x + " " + sCoord.y);
                        System.out.println("You clicked Oxendine");
                        Intent intent  = new Intent(CampusView.this, FloorPlans.class);
                        startActivity(intent);
                    } else {
                        System.out.println("nope");
                        return true;
                    }
                    return true;
                }
                return true;
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        imageView.setMinimumDpi(160);
        imageView.setDoubleTapZoomDpi(160);
        imageView.setMinimumTileDpi(160);
    }


    // Open Campus Buildings
    public void openCampusMap(View view){
        Intent intent  = new Intent(this, CampusView.class);
        startActivity(intent);
    }
}