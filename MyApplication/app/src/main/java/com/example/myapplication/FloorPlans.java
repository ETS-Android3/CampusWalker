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

        SubsamplingScaleImageView imageView = findViewById(R.id.imageViewCampusMap);
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
                System.out.println(stringSelection);

                // Change to a switch statement when possible
                if (stringSelection.equals("First Floor")) {
                    iv.setImageResource(images[0]);
                    System.out.println("Floor 1");
                } else if (stringSelection.equals("Second Floor")) {
                    iv.setImageResource(images[1]);
                    System.out.println("Floor 2");
                } else if (stringSelection.equals("Third Floor")) {
                    iv.setImageResource(images[2]);
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


    }

}


 class PinView extends SubsamplingScaleImageView {

    private final Paint paint = new Paint();
    private final PointF vPin = new PointF();
    private PointF sPin;
    private Bitmap pin;
    Context context;

    public PinView(Context context) {
        this(context, null);
        this.context = context;
    }

    public PinView(Context context, AttributeSet attr) {
        super(context, attr);
        this.context = context;
        initialise();
    }

    public void setPin(PointF sPin) {
        this.sPin = sPin;
        initialise();
        invalidate();
    }

    private void initialise() {
        float density = getResources().getDisplayMetrics().densityDpi;
        pin = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_marker_foreground);
        float w = (density / 420f) * pin.getWidth();
        float h = (density / 420f) * pin.getHeight();
        pin = Bitmap.createScaledBitmap(pin, (int) w, (int) h, true);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw pin before image is ready so it doesn't move around during setup.
        if (!isReady()) {
            return;
        }

        paint.setAntiAlias(true);

        if (sPin != null && pin != null) {
            sourceToViewCoord(sPin, vPin);
            float vX = vPin.x - (pin.getWidth() / 2);
            float vY = vPin.y - pin.getHeight();
            ((android.graphics.Canvas) canvas).drawBitmap(pin, vX, vY, paint);
            Toast.makeText(context, "works ? ", Toast.LENGTH_SHORT).show();
        }

    }
}

