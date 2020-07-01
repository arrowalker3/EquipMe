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
        //save(employeeList, equipmentList);
    }

    public void save(ArrayList employeeList, ArrayList equipmentList){
        Gson employee = new Gson();
        Gson equipment = new Gson();
        employee.toJson(employeeList);
        equipment.toJson(equipmentList);
        JSONArray employeeJSON = new JSONArray(employeeList);
        JSONArray equipmentJSON = new JSONArray(equipmentList);
        //This toast goes so it is getting made into Json
        //Toast.makeText(MainActivity.this, "Wow its Json now.", Toast.LENGTH_SHORT).show()
        //connect to database and save to it
        //Load into a file on the database

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

    /**************************************************************************
     * CREATE EMPLOYEE
     *
     * Called whenever the "Create Employee" button is pressed. Creates a new
     * intent and changes the screen to CreateEmployeeActivity.
     *
     * @param view - Current view
     **************************************************************************/
    public void createEmployee(View view) {
        Intent intent = new Intent(this, CreateEmployeeActivity.class);

        startActivity(intent);

        return;
    }

    /**************************************************************************
     * ADD EMPLOYEE
     *
     * Called by CreateEmployeeActivity. Adds the given employee to the array
     *
     * @param employee - Employee to add to the list of employees
     **************************************************************************/
    public void addEmployee(Employee employee) {
        employeeList.add(employee);

        return;
    }
}
