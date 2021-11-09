package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Map;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        getStudentData();





        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView3);
//        textView.setText(message);
    }

    public void getStudentData() {
        // Instantiate the RequestQueue.
        TextView textView = (TextView) findViewById(R.id.textView3);
        RequestQueue queue = Volley.newRequestQueue(DisplayMessageActivity.this);
        String url = "https://campus-map-web-server.herokuapp.com";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONObject response) {
//                        System.out.println("--------------------------------------------1");
//                        System.out.println();
//                        System.out.println("--------------------------------------------2");
//
//                        JSONArray classes = new JSONArray();
//                        classes.put(response);
//                        System.out.println("--------------------------------------------3");
////                        try {
////                            for (int i = 0; i < classes.length (); ++i) {
////                                String keys = classes.getString (i);
////                                String value = response.getString (keys);
////                                System.out.println(value);
////                            }
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                        for(int i = 0; i < classes.length(); i++){
////
////                            System.out.println(classes);
////                        }
//
//                        response.keys().forEachRemaining(key -> {
//                            try {
//                                Object value = response.get(key);
//
//                                System.out.println(value.toString());
//                                System.out.println("Key: " + key + " Value" + value);
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        });



                        textView.setText("Response is: " + response);
                        String stringResponse = response.toString();
                        storeClassData(stringResponse);
                        System.out.println("This is being read " + read(DisplayMessageActivity.this, "storage.json"));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    public void storeClassData(String data){
        boolean isFilePresent = isFilePresent( DisplayMessageActivity.this,"storage.json");
        if(isFilePresent) {
            System.out.println("File is present");
            String jsonString = read(DisplayMessageActivity.this, "storage.json");
            //do the json parsing here and do the rest of functionality of app
        } else {
            System.out.println("File is not present");
            boolean isFileCreated = create(DisplayMessageActivity.this, "storage.json", data);
            if(isFileCreated) {
                //proceed with storing the first todo or show ui
            } else {
                //show error or try again.
            }
        }
    }

    // Read the user class data is already stored on device
    private String read(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }

    // Create the user class data
    private boolean create(Context context, String fileName, String jsonString){
        String FILENAME = "storage.json";
        try {
            System.out.println("Attempting to write file");
            FileOutputStream fos = context.openFileOutput(fileName,Context.MODE_PRIVATE);
            if (jsonString != null) {
                System.out.println("The response is NOT null");
                fos.write(jsonString.getBytes());
                System.out.println("String was successfully written");
            }
            System.out.println("Attempting to close");
            fos.close();
            System.out.println("fos is closed");
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }
    }

    // See if class data is already present on the device
    public boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }



}