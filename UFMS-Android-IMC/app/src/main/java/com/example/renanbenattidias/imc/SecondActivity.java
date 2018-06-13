package com.example.renanbenattidias.imc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    TextView nameTextView;

    TextView weightTextView;
    TextView heightTextView;
    TextView ageTextView;

    EditText weightEditText;
    EditText heightEditText;
    EditText ageEditText;

    RadioGroup radioGroup;

    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;

    TextView imcTextView;
    TextView imcResultTextView;

    Button logOutButton;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViews();
        setUpCloseButton();
        setUpCalculateButton();
        applyLayouts();
    }

    private void setUpCalculateButton() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateIMC();
            }
        });
    }

    private void setUpCloseButton() {
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void calculateIMC() {
        String heightString = heightEditText.getText().toString();
        String weightString = weightEditText.getText().toString();
        String ageString = ageEditText.getText().toString();

        if(allFieldsAreNotEmpty(heightString, weightString, ageString)) {

            Double height;
            Double weight;
            Integer age;
            try {
                height = Double.parseDouble(heightString);
                weight = Double.parseDouble(weightString);
                age = Integer.parseInt(ageString);
            } catch(Error error) {
                showErrorToast();
                return;
            }

            if(height == null || weight == null || age == null) {
                showErrorToast();
                return;
            }

            IMC imc = new IMC(height, weight);

            imcResultTextView.setText(imc.getImcString());
            showIMCMessage(imc.getImc(), age);
        }
    }

    private void showIMCMessage(Double imc, Integer age) {

        String text = null;
        switch(radioGroup.getCheckedRadioButtonId()) {
            case R.id.maleRadioButton:
                text = description(age, imc, 'M');
                break;
            case R.id.femaleRadioButton:
                text = description(age, imc, 'F');
                break;
        }

        if(text != null) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.app_name);
            dialog.setMessage(text);
            dialog.show();
        }
    }

    static final Integer IDADE_MAX = 65;
    static final Integer IDADE_MIN = 20;

    private String description(Integer idade, Double valor, char sexo) {
        if (idade > IDADE_MAX && sexo == 'F') {
            if (valor < 21.9)
                return getResources().getString(R.string.lowWeight);
            else if (valor > 22 && valor < 27)
                return getResources().getString(R.string.normalWeight);
            else if (valor > 27.1 && valor <  32)
                return getResources().getString(R.string.overWeight);
            else if (valor > 32.1 && valor < 37)
                return getResources().getString(R.string.obesity1);
            else if (valor > 37.1 && valor <  41.9)
                return getResources().getString(R.string.obesity2);
            else
                return getResources().getString(R.string.obesity3);
        } else if (idade > IDADE_MAX && sexo == 'M') {
            if (valor < 21.9)
                return getResources().getString(R.string.lowWeight);
            else if (valor >= 22 && valor <= 27)
                return getResources().getString(R.string.normalWeight);
            else if (valor >=27.1 && valor <= 30)
                return getResources().getString(R.string.overWeight);
            else if (valor >=30.1 && valor <= 35)
                return getResources().getString(R.string.obesity1);
            else if (valor >=35.1 && valor <= 39.9)
                return getResources().getString(R.string.obesity2);
            else
                return getResources().getString(R.string.obesity3);
        } else if (idade >= IDADE_MIN && idade <= IDADE_MAX) {
            if (valor < 16)
                return getResources().getString(R.string.veryLowWeight);
            else if (valor > 16 && valor < 16.99)
                return getResources().getString(R.string.lowWeight2);
            else if (valor > 17 && valor < 18.49)
                return getResources().getString(R.string.lowWeight);
            else if (valor > 18.50 && valor < 24.99)
                return getResources().getString(R.string.normalWeight);
            else if (valor > 25 && valor < 29.99)
                return getResources().getString(R.string.overWeight);
            else if (valor > 30 && valor < 34.99)
                return getResources().getString(R.string.obesity1);
            else if (valor > 35 && valor < 39.99)
                return getResources().getString(R.string.obesity2);
            else
                return getResources().getString(R.string.obesity3);
        } else {
            if (valor < 10)
                return getResources().getString(R.string.lowWeight);
            else if (valor > 15 && valor < 85)
                return getResources().getString(R.string.normalWeight);
            else if (valor > 85 && valor < 95)
                return getResources().getString(R.string.overWeight);
            else
                return getResources().getString(R.string.obesity);
        }
    }


//    private String maleText(Double imc, Integer age) {
//        if(age > MAX_AGE) {
//            if (imc < 21.9)
//                return "Baixo peso";
//            else if (imc >= 22 && imc <= 27)
//                return "Peso normal";
//            else if (imc >= 27.1 && imc <= 30)
//                return "Sobre peso";
//            else if (imc > 30.1 && imc <= 35)
//                return "Obesidade grau 1";
//            else if (imc > 35.1 && imc <= 39.9)
//                return "Obesidade grau 2";
//            return "Obesidade grau 3";
//        }
//        else if(age >= MIM_AGE && age <= MAX_AGE) {
//            if(imc < 16)
//                return "Baixo peso";
//            else if(imc >= 16.1 && imc <= 16.9)
//                return "Peso normal";
//            else if(imc >= 17 && imc <= 18.49)
//                return "Sobre peso";
//            else if(imc > 18.50 && imc <= 24.99)
//                return "Obesidade grau 1";
//            else if(imc > 25 && imc <= 39.9)
//                return "Obesidade grau 2";
//            return "Obesidade grau 3";
//        }
//    }

//    private String femaleText(Double imc, Integer age) {
//        return "";
//    }

    private void showErrorToast() {
        Toast toast = new Toast(this);
        toast.setText(R.string.errorToast);
    }


    private boolean allFieldsAreNotEmpty(String height, String weight, String age) {
        return !height.isEmpty() || !weight.isEmpty() || !age.isEmpty();
    }

    private void applyLayouts() {
        nameTextView.setText(getIntent().getExtras().getString("name"));

        weightTextView.setText(R.string.weight);
        heightTextView.setText(R.string.height);
        ageTextView.setText(R.string.age);

        calculateButton.setText(R.string.calculate);
        logOutButton.setText(R.string.logout);

        maleRadioButton.setText(R.string.male);
        femaleRadioButton.setText(R.string.female);

        imcTextView.setText(R.string.imc);
        imcResultTextView.setText(R.string.imcResult);
    }

    private void findViews() {
        nameTextView = findViewById(R.id.nameTextView);

        weightTextView = findViewById(R.id.weightTextView);
        heightTextView = findViewById(R.id.heightTextView);
        ageTextView = findViewById(R.id.ageTextView);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        ageEditText = findViewById(R.id.ageEditText);

        radioGroup = findViewById(R.id.radioGroup);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);

        imcTextView = findViewById(R.id.imcTextView);
        imcResultTextView = findViewById(R.id.imcResultTextView);

        logOutButton = findViewById(R.id.logOutButton);
        calculateButton = findViewById(R.id.calculateButton);
    }
}
