package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateEmployeeActivity extends AppCompatActivity {
    private Spinner jobTitleSpinner;

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
            // Check for unique ID

                // Create Employee
                // Send to MainActivity
                // Go to MainActivity
            finish();
        } else {
            String errorMessage = "Some information is still empty";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**************************************************************************
     * ALL INFORMATION FILLED
     *
     * Checks each field in the current view except notes. If any of them are
     * at their default value, returns false. Else returns true.
     **************************************************************************/
    private boolean allInformationFilled() {
        // Check Name EditText
        EditText name = (EditText) findViewById(R.id.employeeNameEditText);
        if (name.getText().toString().equals("")) {
            return false;
        }

        // Check Email EditText
        EditText email = (EditText) findViewById(R.id.employeeEmailEditText);
        if (email.getText().toString().equals("")) {
            return false;
        }

        // Check ID EditText
        EditText id = (EditText) findViewById(R.id.employeeIdEditText);
        if (id.getText().toString().equals("")) {
            return false;
        }

        // Check Job Title Spinner
        Spinner jobTitle = findViewById(R.id.employeeJobTitleSpinner);
        if (jobTitle.getSelectedItem().toString().equals("Choose one:")) {
            return false;
        }

        // Successfully passed all checks, good to go
        return true;
    }
}
