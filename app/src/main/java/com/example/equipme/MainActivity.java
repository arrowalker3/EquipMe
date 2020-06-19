package com.example.equipme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static final String throwawayKey = "THROW_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
