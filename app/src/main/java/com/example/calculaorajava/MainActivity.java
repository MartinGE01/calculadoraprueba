package com.example.calculaorajava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculaorajava.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentInput = "";
    private String previousInput = "";
    private String operator = "";
    private boolean isNewEntry = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Number buttons
        findViewById(R.id.button0).setOnClickListener(view -> appendNumber("0"));
        findViewById(R.id.button1).setOnClickListener(view -> appendNumber("1"));
        findViewById(R.id.button2).setOnClickListener(view -> appendNumber("2"));
        findViewById(R.id.button3).setOnClickListener(view -> appendNumber("3"));
        findViewById(R.id.button4).setOnClickListener(view -> appendNumber("4"));
        findViewById(R.id.button5).setOnClickListener(view -> appendNumber("5"));
        findViewById(R.id.button6).setOnClickListener(view -> appendNumber("6"));
        findViewById(R.id.button7).setOnClickListener(view -> appendNumber("7"));
        findViewById(R.id.button8).setOnClickListener(view -> appendNumber("8"));
        findViewById(R.id.button9).setOnClickListener(view -> appendNumber("9"));

        // Dot button
        findViewById(R.id.buttonDot).setOnClickListener(view -> appendDot());

        // Operator buttons
        findViewById(R.id.buttonPlus).setOnClickListener(view -> setOperator("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(view -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(view -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(view -> setOperator("/"));

        // Equal button
        findViewById(R.id.buttonEqual).setOnClickListener(view -> calculateResult());

        // Clear button

    }

    private void appendNumber(String number) {
        if (isNewEntry) {
            resultTextView.setText("");
            isNewEntry = false;
        }
        currentInput += number;
        resultTextView.setText(currentInput);
    }

    private void appendDot() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            resultTextView.setText(currentInput);
        }
    }

    private void setOperator(String newOperator) {
        if (currentInput.equals("")) return;
        if (!previousInput.equals("")) calculateResult();

        operator = newOperator;
        previousInput = currentInput;
        currentInput = "";
    }

    private void calculateResult() {
        double currentValue = Double.parseDouble(currentInput);
        double previousValue = Double.parseDouble(previousInput);

        DecimalFormat decimalFormat = new DecimalFormat("#.####");

        switch (operator) {
            case "+":
                resultTextView.setText(decimalFormat.format(previousValue + currentValue));
                break;
            case "-":
                resultTextView.setText(decimalFormat.format(previousValue - currentValue));
                break;
            case "*":
                resultTextView.setText(decimalFormat.format(previousValue * currentValue));
                break;
            case "/":
                if (currentValue == 0) {
                    resultTextView.setText("Error");
                    currentInput = "";
                    previousInput = "";
                    operator = "";
                    isNewEntry = true;
                    return;
                }
                resultTextView.setText(decimalFormat.format(previousValue / currentValue));
                break;
        }

        previousInput = resultTextView.getText().toString();
        currentInput = "";
        operator = "";
        isNewEntry = true;
    }

    private void clearAll() {
        currentInput = "";
        previousInput = "";
        operator = "";
        isNewEntry = true;
        resultTextView.setText("");
    }
}