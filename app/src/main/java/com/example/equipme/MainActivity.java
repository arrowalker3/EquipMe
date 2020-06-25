package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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
    }

    public void testPreferences(View view) {
        String throwaway = "da bomb diggity";

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(throwawayKey, throwaway);
        editor.commit();
        Toast.makeText(MainActivity.this, "It's saved", Toast.LENGTH_SHORT).show();
    }

    public void save(){
        //save to an sd card? maybe?
        //use it like an api?
    }

    public void load(){

    }
}
