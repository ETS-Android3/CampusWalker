package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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


public class FloorPlans extends AppCompatActivity {

    ImageView iv;
    int images[] = {R.drawable.oxendine_floor_one, R.drawable.oxendine_floor_two, R.drawable.oxendine_floor_three};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plans);
//        iv = (ImageView) findViewById(R.id.floorImage);

        PinView imageView = findViewById(R.id.imageViewCampusMap);
        imageView.setImage(ImageSource.resource(R.drawable.oxendine_floor_one));
        imageView.setMinimumDpi(160);
        imageView.setDoubleTapZoomDpi(160);
        imageView.setMinimumTileDpi(160);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerRoomNumber = (Spinner) findViewById(R.id.spinnerRoomNumber);

        // Adapter to obtain oxendine floor numbers
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.oxendine_floors_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object selection = parent.getItemAtPosition(position);
                String stringSelection = selection.toString();

                System.out.println(stringSelection);

                // Change to a switch statement when possible
                if (stringSelection.equals("First Floor")) {
                    imageView.setImage(ImageSource.resource(R.drawable.oxendine_floor_one));
                    System.out.println("Floor 1");
                } else if (stringSelection.equals("Second Floor")) {
                    imageView.setImage(ImageSource.resource(R.drawable.oxendine_floor_two));
                    System.out.println("Floor 2");
                } else if (stringSelection.equals("Third Floor")) {
                    imageView.setImage(ImageSource.resource(R.drawable.oxendine_floor_three));
                    System.out.println("Floor 3");
                } else {
                    System.out.println("Selection did not match");
                }
            }

            @Override
            // Use if necessary
            public void onNothingSelected(AdapterView<?> parent) {
                // Intentionally kept blank
            }
        });





        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> roomNumAdapter = ArrayAdapter.createFromResource(this,
                R.array.oxendine_rooms_array_floorOne, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        roomNumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerRoomNumber.setAdapter(roomNumAdapter);

        spinnerRoomNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object selection = parent.getItemAtPosition(position);
                String stringSelection = selection.toString();

                System.out.println("The String selection" + stringSelection);

                // Change to a switch statement when possible
                if (stringSelection.equals("1201")) {

                    PointF class1201 = new PointF(650f, 1900f);
                    imageView.setPin(class1201);
                    System.out.println("Adapter =  Room 1201");

                } else if (stringSelection.equals("1202")) {

                    PointF class1202 = new PointF(1480f, 1000);
                    imageView.setPin(class1202);
                    System.out.println("Adapter =  Room 1202");

                } else if (stringSelection.equals("1203")) {

                    PointF class1203 = new PointF(1540f, 1300f);
                    imageView.setPin(class1203);
                    System.out.println("Adapter = Room 1203");

                } else {
                    PointF class1202 = new PointF(-1400f, -1400f);
                    imageView.setPin(class1202);
                    System.out.println("Adapter = Room 1203");

                }
            }

            @Override
            // Use if necessary
            public void onNothingSelected(AdapterView<?> parent) {
                // Intentionally kept blank
            }
        });



    }

}


