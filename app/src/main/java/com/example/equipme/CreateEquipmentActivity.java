package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CreateEquipmentActivity extends AppCompatActivity {
    Button equipmentCreateButton;  // Button to create new equipment and go to equipment activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_equipment);

        equipmentCreateButton = findViewById(R.id.equipmentCreateButton);

        equipmentCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Populate "Type of equipment" dropdown
                Spinner equipTypeSpinner = (Spinner) findViewById(R.id.equipmentTypeSpinner);
                ArrayAdapter<String> equipTypeAdapter = new ArrayAdapter<String>(CreateEquipmentActivity.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.equipmentType));
                equipTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                Spinner equipBrandSpinner = (Spinner) findViewById(R.id.equipmentBrandSpinner);
                ArrayAdapter<String> equipBrandAdapter = new ArrayAdapter<String>(CreateEquipmentActivity.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.equipmentBrand));
                equipBrandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Allows the data to show inside the spinner
                equipTypeSpinner.setAdapter(equipTypeAdapter);
                String type = equipTypeSpinner.getSelectedItem().toString();

                // Populate "Brand of equipment" dropdown
                equipBrandSpinner.setAdapter(equipBrandAdapter);
                String brand = equipBrandSpinner.getSelectedItem().toString();

                // User input for "Serial Number"
                String serialNumber = " ";

                // Populate "Assigned to" dropdown
                String assignedTo = " ";

                // User input for "Other notes"
                String notes = " ";


                /*
                * Equipment creation code and logic here
                * */
                Equipment newEquipment = new Equipment();
                newEquipment.setType(type);
                newEquipment.setBrand(brand);
                newEquipment.setSerialNumber(serialNumber);
                newEquipment.setAssignedTo(assignedTo);
                newEquipment.setNotes(notes);

                //First parameter of the Intent is where you are going from and the 2nd is your destination
                Intent createEquipmentIntent = new Intent( CreateEquipmentActivity.this, MainActivity.class);

                createEquipmentIntent.putExtra("equipment", newEquipment);

                startActivity(createEquipmentIntent);
            }
        });
    }
}
