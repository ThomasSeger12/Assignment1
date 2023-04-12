package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editCheck;
    private EditText editPeople;
    private EditText editTip;
    private Button btnCalculate;
    private TextView textCost;
    private TextView textCostPer;
    private TextView textTipCost;
    private TextView textTipPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecimalFormat df = new DecimalFormat("#.##");

        editCheck = (EditText) findViewById(R.id.editCheck);
        editPeople = (EditText) findViewById(R.id.editPeople);
        editTip = (EditText) findViewById(R.id.editTip);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        textCost = (TextView) findViewById(R.id.textCost);
        textCostPer = (TextView) findViewById(R.id.textCostPer);
        textTipCost = (TextView) findViewById(R.id.textTipCost);
        textTipPer = (TextView) findViewById(R.id.textTipPer);

        // default values
        editPeople.setText("1");
        editTip.setText("15");

        btnCalculate.setOnClickListener((View) -> {
            // get the input from user
            double checkAmt = Double.parseDouble(editCheck.getText().toString());
            int peopleAmt = Integer.parseInt(editPeople.getText().toString());
            double tipAmt = Double.parseDouble(editTip.getText().toString());

            // calculate and display
            // total cost
            double totalCost = checkAmt * (1+(tipAmt/100));
            String totalCostDisplay = df.format(totalCost);
            textCost.setText("$" + totalCostDisplay);

            // total per person
            double totalPer = totalCost / peopleAmt;
            String totalPerDisplay = df.format(totalPer);
            textCostPer.setText("$" + totalPerDisplay);

            // total tip
            double totalTip = checkAmt * (tipAmt/100);
            String totalTipDisplay = df.format(totalTip);
            textTipCost.setText("$" + totalTipDisplay);

            // tip per person
            double totalTipPer = totalTip / peopleAmt;
            String totalTipPerDisplay = df.format(totalTipPer);
            textTipPer.setText("$" + totalTipPerDisplay);
        });
    }
}