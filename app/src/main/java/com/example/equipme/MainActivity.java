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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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

        // Instantiate the 2 main lists
        employeeList = new ArrayList<>();
        equipmentList = new ArrayList<>();

        createEquipmentButton = findViewById(R.id.createEquipmentButton);

        createEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //First parameter of the Intent is where you are going from and the 2nd is your destination
                Intent intent = new Intent( MainActivity.this, CreateEquipmentActivity.class);

                startActivity(intent);
            }
        });

        // Load up data
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveEmployee(ArrayList employeeList) throws IOException {
        Gson employee = new Gson();
        String employeeString = employee.toJson(employeeList);
        URL url = new URL("https://run.mocky.io/v3/059a9c60-9557-46b2-be9b-f59c6214ee3f");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/JSON: utf-8");
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = employeeString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        /*try (BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }*/
    }

    public void saveEquipment(ArrayList equipmentList) throws IOException {
        Gson equipment = new Gson();
        //JSONArray equipmentJSON = new JSONArray(equipmentList);
        String equipmentString = equipment.toJson(equipmentList);
        URL url = new URL("https://run.mocky.io/v3/059a9c60-9557-46b2-be9b-f59c6214ee3f");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/JSON: utf-8");
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = equipmentString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        /*try (BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }*/
    }


    public void load() throws IOException {
        //access database
        //use mocky.io for tests
        //load it in.
        //link: https://run.mocky.io/v3/fc3ebf63-a032-4f1b-acf3-e65a23770054
        URLConnection connection = new URL("https://run.mocky.io/v3/fc3ebf63-a032-4f1b-acf3-e65a23770054").openConnection();

        /******************
         * IMPORTANT!!!!
         * The line of code under this crashes the app whenever I try to run it. Not sure what's happening, but
         * I narrowed it down to here. When I check the link in a browser, I just get a "404: Not found" error
         * message, so that might be it.
         * -Trey
         *****************/
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
        // Get a list of employee IDs
        ArrayList<String> idList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            idList.add(employeeList.get(i).getEmployeeNumber());
        }

        // Create intent and give it the list of employee IDs
        Intent intent = new Intent(this, CreateEmployeeActivity.class);
        intent.putExtra("idList", idList);

        startActivityForResult(intent, 1); // Request code for CreateEmployee == 1

        return;
    }

    /**************************************************************************
     * ON ACTIVITY RESULT
     *
     * When a "Create" Activity completes, it sets the result as a new element
     * in the intent. This function assigns that new object to the appropriate
     * list.
     *
     * @param requestCode - the code showing which Activity is sending the result.
     * @param resultCode - Unimportant for our purposes.
     * @param data - The intent used to send back the new object.
     **************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check request code
        // Request code 1 == result from CreateEmployeeActivity
        if (requestCode == 1) {
            String json = data.getStringExtra("employeeJson");
            Gson gson = new Gson();
            Employee createdEmployee = gson.fromJson(json, Employee.class);
            employeeList.add(createdEmployee);
        }


        // When results are processed, update ListView
    }


}
