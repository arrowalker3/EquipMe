package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewEmployeeActivity extends AppCompatActivity {

    ArrayList<String> displayEquipment;
    ArrayList<String> allEquipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        String employeeData = getIntent().getStringExtra("employeeData");
        String arrayJSON = getIntent().getStringExtra("equipmentArrayList");
        //ArrayList<String> equipmentArrayList = getIntent().getStringArrayListExtra("equipmentArrayList");
        if (employeeData != null) {
            Gson gson = new Gson();
            Employee employee = gson.fromJson(employeeData, Employee.class);
            //ArrayList equipment = gson.fromJson(String.valueOf(equipmentArrayList), ArrayList.class);

            TextView name = (TextView) findViewById(R.id.viewEmployeeNameTextView);
            name.setText(employee.getName());

            TextView email = (TextView) findViewById(R.id.viewEmployeeEmailTextView);
            email.setText(employee.getEmailAddress());

            TextView idNumber = (TextView) findViewById(R.id.viewEmployeeNumberTextView);
            idNumber.setText(employee.getEmployeeNumber());

            TextView jobTitle = (TextView) findViewById(R.id.viewEmployeeJobTitleTextView);
            jobTitle.setText(employee.getJobTitle());

            if (arrayJSON != null) {
                // Parse ArrayList from JSON
                TypeToken<ArrayList<Equipment>> token = new TypeToken<ArrayList<Equipment>>() {};
                ArrayList<Equipment> equipmentArrayList = gson.fromJson(arrayJSON, token.getType());
                displayEquipment = new ArrayList<>();
                allEquipment = new ArrayList<>();
                for (int i = 0; i < employee.getEquipment().size(); i++) {
                    String hold = employee.getEquipment().get(i).getBrand() + " " + employee.getEquipment().get(i).getType();
                    displayEquipment.add(hold);
                }
                for (int all = 0; all < equipmentArrayList.size(); all++) {
                    allEquipment.add(equipmentArrayList.get(all).getDisplayString());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.textView, displayEquipment);
                final ListView EquipmentDisplay = (ListView) findViewById(R.id.viewEmployeeAssignedEquipmentListView);
                EquipmentDisplay.setAdapter(arrayAdapter);
                Spinner addRemoveEquipment = (Spinner)findViewById(R.id.employeeAddRemoveEquipmentSpinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allEquipment);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                addRemoveEquipment.setAdapter(adapter);
            }
            TextView notes = (TextView) findViewById(R.id.viewEmployeeNotesEditText);
            notes.setText(employee.getNotes());

            ListView EquipmentDisplay = (ListView) findViewById(R.id.viewEmployeeAssignedEquipmentListView);
            EquipmentDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Gson gson = new Gson();
                        String equipmentJSON = gson.toJson((String) allEquipment.get(i));

                        // Create intent
                        Intent intent = new Intent(ViewEmployeeActivity.this, ViewEquipmentActivity.class);
                        intent.putExtra("equipmentData", equipmentJSON);

                        // Start Activity
                        startActivity(intent);
                    }
            });
        }
    }
}

