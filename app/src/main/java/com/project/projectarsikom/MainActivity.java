package com.project.projectarsikom;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnCreator, btnCalculator, btnDecimalToBinary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreator = findViewById(R.id.Creator);
        btnCreator.setOnClickListener(this);
        btnCalculator = findViewById(R.id.Calculator);
        btnCalculator.setOnClickListener(this);
        btnDecimalToBinary = findViewById(R.id.DecimalConverter);
        btnDecimalToBinary.setOnClickListener(this);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Creator:
                Intent IntentCreator = new Intent(MainActivity.this, Biodata_Activity.class);
                startActivity(IntentCreator);
                break;
            case R.id.Calculator:
                Intent IntentCalculator = new Intent(MainActivity.this, Calculator_Activity.class);
                startActivity(IntentCalculator);
                break;
            case R.id.DecimalConverter:
                Intent IntentDecimalConverter = new Intent(MainActivity.this, Decimaltobinary.class);
                startActivity(IntentDecimalConverter);
                break;
        }
    }
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you really want to exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
