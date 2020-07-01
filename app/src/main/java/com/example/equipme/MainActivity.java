package com.example.equipme;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Employee> employeeList;
    ArrayList<Equipment> equipmentList;


    Button createEquipmentButton;  // Button to go to the new equipment activity

    SharedPreferences sharedPreferences;
    public static final String throwawayKey = "THROW_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEquipmentButton = findViewById(R.id.createEquipmentButton);

        createEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //First parameter of the Intent is where you are going from and the 2nd is your destination
                Intent intent = new Intent( MainActivity.this, CreateEquipmentActivity.class);

                startActivity(intent);
            }
        });

        String whatever;
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        whatever = sharedPreferences.getString(throwawayKey, null);
        //save(employeeList, equipmentList);
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testPreferences(View view) {
        String throwaway = "da bomb diggity";

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(throwawayKey, throwaway);
        editor.commit();
        Toast.makeText(MainActivity.this, "It's saved", Toast.LENGTH_SHORT).show();
    }

    public void saveEmployee(ArrayList employeeList) throws IOException {
        Gson employee = new Gson();
        employee.toJson(employeeList);
        JSONArray employeeJSON = new JSONArray(employeeList);
        URLConnection connection = new URL("https://run.mocky.io/v3/059a9c60-9557-46b2-be9b-f59c6214ee3f").openConnection();
    }

    public void saveEquipment(ArrayList equipmentList) throws IOException {
        Gson equipment = new Gson();
        equipment.toJson(equipmentList);
        JSONArray equipmentJSON = new JSONArray(equipmentList);
        URLConnection connection = new URL("https://run.mocky.io/v3/059a9c60-9557-46b2-be9b-f59c6214ee3f").openConnection();
    }


    public void load() throws IOException {
        //access database
        //use mocky.io for tests
        //load it in.
        //link: https://run.mocky.io/v3/fc3ebf63-a032-4f1b-acf3-e65a23770054
        URLConnection connection = new URL("https://run.mocky.io/v3/fc3ebf63-a032-4f1b-acf3-e65a23770054").openConnection();
        InputStream responseStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
        StringBuilder stringBuilder = new StringBuilder();
        String hold;
        while((hold = reader.readLine()) != null)
            stringBuilder.append(hold);
        stringBuilder.toString();
    }
}
