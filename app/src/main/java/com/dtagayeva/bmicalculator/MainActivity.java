package com.dtagayeva.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private TextView resultText;
    private EditText weightEditText;
    private EditText inchesEditText;
    private EditText feetEditText;
    private EditText ageEditText;
    private RadioButton femaleButton;
    private RadioButton maleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setupButtonClickListener();

    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);

    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);


        /*String bmiTextResult = String.valueOf(bmi);
        resultText.setText(bmiTextResult);*/
//        displayResult(bmi);


    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            fullResultString = bmiTextResult + " - You are underweight";

        } else if (bmi > 25) {
            fullResultString = bmiTextResult + " - You are weight is overweight. Please contact the doctor.";
        } else {
            fullResultString = bmiTextResult + " - You are healthy weight!";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for boys.";
        } else if (femaleButton.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range for girls.";
        } else {
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healthy range.";

        }
        resultText.setText(fullResultString);
    }
}