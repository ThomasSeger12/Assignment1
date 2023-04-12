package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editCheck;
    private EditText editPeople;
    private EditText editTip;
    private Button btnCalculate;
    private Button btnWeb;
    private Button btnDial;
    private Button btnMap;
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
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnDial = (Button) findViewById(R.id.btnDial);
        btnMap = (Button) findViewById(R.id.btnMap);
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

        btnWeb.setOnClickListener(this);
        btnDial.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btnWeb:
                Intent intWeb = new Intent(this, WebLookup.class);
                startActivity(intWeb);
                Toast.makeText(MainActivity.this, "Web Lookup Started", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnDial:
                Uri uriDial = Uri.parse("tel:7818912000");
                Intent intDial = new Intent(Intent.ACTION_DIAL, uriDial);
                startActivity(intDial);
                break;

            case R.id.btnMap:
                Uri uriMap = Uri.parse("geo:0,0?q=175+forest+street+waltham+ma");
                Intent intMap = new Intent(Intent.ACTION_VIEW, uriMap);
                startActivity(intMap);
                break;
        }
    }
}