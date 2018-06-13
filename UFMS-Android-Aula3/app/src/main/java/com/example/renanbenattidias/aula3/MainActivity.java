package com.example.renanbenattidias.aula3;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView firstTextView;
    private TextView secondTextView;

    private EditText firstEditText;
    private EditText secondEditText;

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        findViewById(R.id.sumButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1String = firstEditText.getText().toString();
                String number2String = secondEditText.getText().toString();
                Double number1 = convertToDouble(number1String);
                Double number2 = convertToDouble(number2String);

                if(validNumbers(number1, number2)) {
                    Double result = sum(number1, number2);
                    resultTextView.setText(result.toString());
                } else {
                    Log.e("Error", "Failed to cast");
                }
            }
        });

        findViewById(R.id.subtractButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private boolean validNumbers(Double number1, Double number2) {
        return number1 != null && number2 != null;
    }

    private Double convertToDouble(String number) {
        return Double.parseDouble(number);
    }

    private Double sum(Double number1, Double number2) {
        return number1 + number2;
    }

    private void findViews() {
        firstTextView =  findViewById(R.id.firstTextView);
        secondTextView = findViewById(R.id.secondTextView);

        firstEditText = findViewById(R.id.firstInputView);
        secondEditText = findViewById(R.id.secondInputView);

        resultTextView = findViewById(R.id.resultTextView);
    }

    private void applyLayout() {
        firstTextView.setText("Número 1");
        secondTextView.setText("Número 2");

        resultTextView.setText("Resultado");
    }
}
