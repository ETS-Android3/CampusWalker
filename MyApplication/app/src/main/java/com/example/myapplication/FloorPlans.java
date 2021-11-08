package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FloorPlans extends AppCompatActivity {

    Button next;
    Button prev;
    ImageView iv;
    boolean flag;
    int images[] = {R.drawable.oxendine_floor_one, R.drawable.oxendine_floor_two, R.drawable.oxendine_floor_three};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plans);

        iv = (ImageView) findViewById(R.id.floorImage);
        next = (Button) findViewById(R.id.nextButton);
        prev = (Button) findViewById(R.id.prevButton);
        flag = true;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(images[i]);
                i++;
                if (i == 3)
                    i = 0;
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageResource(images[i]);
                i--;
                if (i ==-1)
                    i=2;
            }
        });
    }
}