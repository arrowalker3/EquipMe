package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    ArrayList<Displayable> currentDisplayList;

    Button createEquipmentButton;  // Button to go to the new equipment activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the 3 main lists
        employeeList = new ArrayList<>();
        equipmentList = new ArrayList<>();
        currentDisplayList = new ArrayList<>();

        createEquipmentButton = findViewById(R.id.createEquipmentButton);

        Intent equipmentIntent = getIntent();
        if(equipmentIntent != null) {
            Equipment selectedEquipment = (Equipment) equipmentIntent.getParcelableExtra("equipment");

            equipmentList.add(selectedEquipment);
        }

        createEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //First parameter of the Intent is where you are going from and the 2nd is your destination
                Intent intent = new Intent( MainActivity.this, CreateEquipmentActivity.class);

                startActivity(intent);
            }
        });

        // Display Employees at start
        updateListViewEmployees(makeFakeEmployees());
    }

    /**************************************************************************
     * SAVE EMPLOYEE
     *
     * Saves the current instance of employeeList
     * Currently saves the list to a fake url
     *
     * @param employeeList - the list of employees created
     **************************************************************************/
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

    /**************************************************************************
     * SAVE EQUIPMENT
     *
     * Saves the current instance of equipmentList
     * Currently saves the list to a fake url
     *
     * @param equipmentList - the list of equipment created
     **************************************************************************/
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

    /**************************************************************************
     * LOAD
     *
     * Loads saved data from an outside source
     * Currently loads nothing from a fake url
     **************************************************************************/
    public void load() throws IOException {
        //access database
        //use mocky.io for tests
        //load it in.
        //link: https://run.mocky.io/v3/fc3ebf63-a032-4f1b-acf3-e65a23770054
        URL url = new URL("https://run.mocky.io/v3/059a9c60-9557-46b2-be9b-f59c6214ee3f");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            idList.add(employeeList.get(i).getMyKey());
        }

        // Create intent and give it the list of employee IDs
        Intent intent = new Intent(this, CreateEmployeeActivity.class);
        intent.putExtra("keyList", idList);

        startActivityForResult(intent, 1); // Request code for CreateEmployee == 1

        return;
    }

    /**************************************************************************
     * ON ACTIVITY RESULT
     *
     * When a "Create" Activity completes, it sets the result as a new element
     * in the intent. This function assigns that new object to the appropriate
     * list, then saves the list.
     *
     * @param requestCode - the code showing which Activity is sending the result.
     * @param resultCode - Value is "RESULT_OK" if the object was created successfully.
     * @param data - The intent used to send back the new object.
     **************************************************************************/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check request code
        // Request code 1 == result from CreateEmployeeActivity
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String json = data.getStringExtra("employeeJson");
            Gson gson = new Gson();
            Employee createdEmployee = gson.fromJson(json, Employee.class);
            employeeList.add(createdEmployee);

            // Save
            try {
                saveEmployee(employeeList);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to save Employee List", Toast.LENGTH_SHORT).show();
            }

            // Display Employees
            updateListViewEmployees(employeeList);
        }
    }

    /***********************************************************************
     * Create array of employees to test list view
     **********************************************************************/
    public ArrayList<Employee> makeFakeEmployees() {
        ArrayList<Employee> listOfEmployees = new ArrayList<>();

        Employee employeeToAdd = new Employee();
        employeeToAdd.setName("John Doe");
        for (int i = 0; i < 25; i++) {
            listOfEmployees.add(employeeToAdd);
        }

        return listOfEmployees;
    }



    /**************************************************************************
     * UPDATE LIST VIEW
     *
     * Takes the given array of Employee objects and
     * puts the Display String in the Listview
     *
     * @param displayList - The Arraylist to show in the Listview
     **************************************************************************/
    public void updateListViewEmployees(ArrayList<Employee> displayList) {
        // Clear current display
        currentDisplayList.clear();

        // Move the display list to the current display
        for (int i = 0; i < displayList.size(); i++) {
            currentDisplayList.add(displayList.get(i));
        }

        fillListView();

        // Set onClickListeners
    }

    /**************************************************************************
     * UPDATE LIST VIEW EQUIPMENT
     *
     * Takes the given array of Equipment objects and
     * puts the Display String in the Listview
     *
     * @param displayList - The Arraylist to show in the Listview
     **************************************************************************/
    public void updateListViewEquipment(ArrayList<Equipment> displayList) {
        // Clear current display
        currentDisplayList.clear();

        // Move the display list to the current display
        for (int i = 0; i < displayList.size(); i++) {
            currentDisplayList.add(displayList.get(i));
        }

        fillListView();

        // Set onClickListeners
    }

    /**************************************************************************
     * UPDATE LIST VIEW EQUIPMENT
     *
     * Takes the currentDisplayList and uses that to fill the ListView
     **************************************************************************/
    public void fillListView() {
        // Create Array just with Displayable strings
        ArrayList<String> displayStrings = new ArrayList<>();
        for (int i = 0; i < currentDisplayList.size(); i++) {
            displayStrings.add(currentDisplayList.get(i).getDisplayString());
        }

        // Set ListView
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, R.layout.list_view, R.id.textView, displayStrings);
        ListView mainDisplay = (ListView) findViewById(R.id.mainDisplay);
        mainDisplay.setAdapter(arrayAdapter);
    }
}
