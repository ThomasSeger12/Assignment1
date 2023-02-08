package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // instance variables
    private TextView txtResult;
    private EditText editNum;
    private Button btnAdd;
    private Button btnSubtract;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // connects Activity to its UI

        txtResult = (TextView) findViewById(R.id.textView);
        editNum = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.button1);
        btnSubtract = (Button) findViewById(R.id.button2);
        btnMultiply = (Button) findViewById(R.id.multiply);
        btnDivide = (Button) findViewById(R.id.divide);
        btnClear = (Button) findViewById(R.id.button3);

        txtResult.setText("0");

        btnAdd.setOnClickListener((View) -> {
            String op1 = txtResult.getText().toString();
            double operand1 = Double.parseDouble(op1);

            String op2 = editNum.getText().toString();
            double operand2 = Double.parseDouble(op2);

            double result = operand1 + operand2;
            String res = new Double(result).toString();
            txtResult.setText(res);
        });

        btnSubtract.setOnClickListener((View) -> {
            String op1 = txtResult.getText().toString();
            double operand1 = Double.parseDouble(op1);

            String op2 = editNum.getText().toString();
            double operand2 = Double.parseDouble(op2);

            double result = operand1 - operand2;
            String res = new Double(result).toString();

            txtResult.setText(res);
            editNum.setText("");
        });

        btnMultiply.setOnClickListener((View) -> {
            String op1 = txtResult.getText().toString();
            double operand1 = Double.parseDouble(op1);

            String op2 = editNum.getText().toString();
            double operand2 = Double.parseDouble(op2);

            double result = operand1 * operand2;
            String res = new Double(result).toString();

            txtResult.setText(res);
            editNum.setText("");
        });

        btnDivide.setOnClickListener((View) -> {
            String op1 = txtResult.getText().toString();
            double operand1 = Double.parseDouble(op1);

            String op2 = editNum.getText().toString();
            double operand2 = Double.parseDouble(op2);

            double result = operand1 / operand2;
            String res = new Double(result).toString();

            txtResult.setText(res);
            editNum.setText("");
        });

        btnClear.setOnClickListener((View) -> {
            txtResult.setText("0");
            editNum.setText("");
        });
    }
}