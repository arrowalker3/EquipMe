package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ViewEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        String employeeData = getIntent().getStringExtra("employeeData");
        ArrayList<String> equipmentArrayList = getIntent().getStringArrayListExtra("equipmentArrayList");
        if (employeeData != null) {
            Gson gson = new Gson();
            Employee employee = gson.fromJson(employeeData, Employee.class);
            ArrayList equipment = gson.fromJson(String.valueOf(equipmentArrayList), ArrayList.class);

            TextView name = (TextView) findViewById(R.id.viewEmployeeNameTextView);
            name.setText(employee.getName());

            TextView email = (TextView) findViewById(R.id.viewEmployeeEmailTextView);
            email.setText(employee.getEmailAddress());

            TextView idNumber = (TextView) findViewById(R.id.viewEmployeeNumberTextView);
            idNumber.setText(employee.getEmployeeNumber());

            TextView jobTitle = (TextView) findViewById(R.id.viewEmployeeJobTitleTextView);
            jobTitle.setText(employee.getJobTitle());

            if (equipmentArrayList != null) {
                ArrayList<String> displayEquipment = new ArrayList<>();
                for (int i = 0; i < equipmentArrayList.size(); i++) {
                    displayEquipment.add(equipmentArrayList.get(i));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.textView, displayEquipment);
                final ListView EquipmentDisplay = (ListView) findViewById(R.id.viewEmployeeAssignedEquipmentListView);
                EquipmentDisplay.setAdapter(arrayAdapter);

                TextView notes = (TextView) findViewById(R.id.viewEmployeeNotesEditText);
                notes.setText(employee.getNotes());
            }
        }
    }
}

