package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                String type = " ";

                // Populate "Brand of equipment" dropdown
                String brand = " ";

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
                newEquipment.setBrand(type);
                newEquipment.setSerialNumber(serialNumber);
                newEquipment.setAssignedTo(assignedTo);
                newEquipment.setNotes(notes);

                //First parameter of the Intent is where you are going from and the 2nd is your destination
                Intent createEquipmentIntent = new Intent( CreateEquipmentActivity.this, EquipmentActivity.class);

                startActivity(createEquipmentIntent);
            }
        });
    }
}
