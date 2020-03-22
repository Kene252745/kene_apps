package com.example.bmiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private TextView result;
    private TextView author_page;
    private String currentUnit = "metric";
    private float heightValue;
    private float weightValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
    }

    public boolean isValidInput (){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        float weightValue = Float.parseFloat(weightStr);
        float heightValue = Float.parseFloat(heightStr);
        return heightStr == "" ||Float.compare(heightValue, 30) < 0 || !"".equals(heightStr) || weightStr == ""  || Float.compare(weightValue, 10) < 0 || !"".equals(weightStr);
    }

    public void calculateBMI(View v) {
        String heightStr2 = height.getText().toString();
        String weightStr2 = weight.getText().toString();
        if (!this.isValidInput()) {
            heightValue = Float.parseFloat(heightStr2)/100;
            weightValue = Float.parseFloat(weightStr2);
            float bmi = weightValue/ (heightValue*heightValue);
            displayBMI(bmi);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Please provide a valid data";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }




    private void displayBMI(float bmi) {
        String bmiLabel = "";
        int color;
        if (Float.compare(bmi,15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
            color =   getResources().getColor(R.color.color_very_severely_underweight);
        }else if (Float.compare(bmi,15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
            color =   getResources().getColor(R.color.color_severely_underweight);
        }else if (Float.compare(bmi,16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
            color =   getResources().getColor(R.color.color_underweight);
        }else if (Float.compare(bmi,18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
            color =   getResources().getColor(R.color.color_normal);
        }else if (Float.compare(bmi,25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
            color =   getResources().getColor(R.color.color_overweight);
        }else if (Float.compare(bmi,30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
            color =   getResources().getColor(R.color.color_obese_class_i);
        }else if (Float.compare(bmi,35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
            color =   getResources().getColor(R.color.color_obese_class_ii);
        }else {
            bmiLabel = getString(R.string.obese_class_iii);
            color =   getResources().getColor(R.color.color_obese_class_iii);
        }
        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
        result.setTextColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.author_menu,menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.author_page:
                startActivity(new Intent(this, author.class));
                return true;
            case R.id.imperial_page:
                startActivity(new Intent(this, Imperial.class));
                Toast.makeText(this, "Imperial Units for Americans", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void convertResult(String unit){
        if(this.currentUnit != unit) {
            if(unit.equals("imperial")) {
                double imperialHeight = heightValue * 0.39;
                double imperialWeight = weightValue * 2.2;
                height.setText((int) imperialHeight);
                weight.setText((int) imperialWeight);
            }
            if(unit.equals("metric")) {
                double imperialHeight = heightValue / 0.39;
                double imperialWeight = weightValue / 2.2;
                height.setText((int) imperialHeight);
                weight.setText((int) imperialWeight);
            }
            float bmi = weightValue/ (heightValue*heightValue);
            result.setText((int) bmi);
        }
    }



}