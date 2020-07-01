package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
    }

    /**************************************************************************
     * ADD EMPLOYEE TO LIST
     *
     * Called when "Create Employe" button is pressed. Checks that all needed
     * information has been filled, then adds a newly created Employee to the
     * main list.
     *
     * @param view - Current view
     **************************************************************************/
    public void addEmployeeToList(View view) {
        if (allInformationFilled()) {
            // Create Employee
            // Send to MainActivity
            // Go to MainActivity
        } else {
            String errorMessage = "Some information is still empty";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**************************************************************************
     * ALL INFORMATION FILLED
     *
     * Called when "Create Employe" button is pressed. Checks that all needed
     * information has been filled, then adds a newly created Employee to the
     * main list.
     *
     * @param view - Current view
     **************************************************************************/
    private boolean allInformationFilled() {
        return false;
    }
}
