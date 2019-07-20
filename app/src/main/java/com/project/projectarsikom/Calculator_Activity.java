package com.project.projectarsikom;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator_Activity extends AppCompatActivity implements View.OnClickListener{
    EditText InputText1, InputText2;
    TextView Result, Decimal1, Decimal2, ResultDecimal;
    Button Clear, Calculate;
    Spinner spinnerOperator, spinnerCalculator;
    String OperatorCall, InputType, Binary1, Binary2, TempDecimal3, TempBinary, TempDecimal1, TempDecimal2;
    int Temp1, Temp2;
    String sign2, sign1;
    int Negative1, Negative2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        InputText1 = findViewById(R.id.FirstBinary);
        InputText2 = findViewById(R.id.SecondBinary);
        Result = findViewById(R.id.Result);
        ResultDecimal = findViewById(R.id.ResultDecimal);
        Decimal1 = findViewById(R.id.FirstDecimal);
        Decimal2 = findViewById(R.id.SecondDecimal);
        Clear = findViewById(R.id.Clear);
        Clear.setOnClickListener(this);
        Calculate = findViewById(R.id.Calculate);
        Calculate.setOnClickListener(this);

        spinnerOperator = findViewById(R.id.spinnerCalculatorOperator);
        spinnerCalculator = findViewById(R.id.spinnerCalculatorChoose);
    }

    public void onClick (View view){
        switch (view.getId()){
            case R.id.Clear:
                InputText1.setText("");
                InputText2.setText("");
                Decimal2.setText("");
                Decimal1.setText("");
                Result.setText("");
                ResultDecimal.setText("");
                break;
            case R.id.Calculate:
                Negative1 = 1; Negative2=1;Binary1="";Binary2="";
                sign1=""; sign2="";
                try{
                    InputType = spinnerCalculator.getSelectedItem().toString();
                    OperatorCall = spinnerOperator.getSelectedItem().toString();
                    if(InputType.equals("Sign/Magnitude")){
                        String TempSign2="", TempSign1="";
                        sign1 = InputText1.getText().toString();
                        sign2 = InputText2.getText().toString();
                        if(sign1.charAt(1)=='1' && sign1.length()>4){
                            if(sign1.charAt(0)=='1'){
                                Negative1 = Negative1 * -1;
                            }
                            if(sign1.charAt(0)=='1'){
                                for(int i=1;i<sign1.length();i++) {
                                    TempSign1 += sign1.charAt(i);
                                }
                                Binary1 = TempSign1;
                            }
                            else if(sign1.charAt(0)=='0'){
                                Binary1 = sign1;
                            }
                        }
                        if(sign2.charAt(1)=='1' && sign2.length()>4){
                            if(sign2.charAt(0)=='1'){
                                Negative2 = Negative2 * -1;
                            }
                            if(sign2.charAt(0)=='1'){
                                for(int i=1;i<sign2.length();i++){
                                    TempSign2 += sign2.charAt(i);
                                }
                                Binary2 = TempSign2;
                            }
                            else if(sign2.charAt(0)=='0'){
                                Binary2 = sign2;
                            }
                        }
                        if(sign1.length()==4){
                            if(sign1.charAt(0)=='1'){
                                Negative1 = Negative1 * -1;
                            }
                            if(sign1.charAt(0)=='1'){
                                for(int i=1;i<sign1.length();i++) {
                                    TempSign1 += sign1.charAt(i);
                                }
                                Binary1 = TempSign1;
                            }
                            else if(sign1.charAt(0)=='0'){
                                Binary1 = sign1;
                            }
                        }
                        if(sign2.length()==4){
                            if(sign2.charAt(0)=='1'){
                                Negative2 = Negative2 * -1;
                            }
                            if(sign2.charAt(0)=='1'){
                                for(int i=1;i<sign2.length();i++){
                                    TempSign2 += sign2.charAt(i);
                                }
                                Binary2 = TempSign2;
                            }
                            else if(sign2.charAt(0)=='0'){
                                Binary2 = sign2;
                            }
                        }
                        Temp1 = Integer.parseInt(Binary1,2);
                        Temp2 = Integer.parseInt(Binary2,2);
                        Temp1 = Temp1 * Negative1;
                        Temp2 = Temp2 * Negative2;
                    }
                    else if(InputType.equals("Binary")){
                        Binary1 = InputText1.getText().toString();
                        Binary2 = InputText2.getText().toString();
                        Temp1 = Integer.parseInt(Binary1,2);
                        Temp2 = Integer.parseInt(Binary2,2);
                    }
                    if(OperatorCall.equals("+ (Penjumlahan)")){
                        TempDecimal1 = Integer.toString(Temp1);
                        TempDecimal2 = Integer.toString(Temp2);
                        Temp1 = Temp1 + Temp2;
                        TempDecimal3 = Integer.toString(Temp1);
                        ResultDecimal.setText(TempDecimal3);
                        if(Temp1>=0){
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            Result.setText("0"+TempBinary);
                        }
                        else{
                            Temp1 = Temp1*-1;
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            if(InputType.equals("Sign/Magnitude")){
                                Result.setText("1"+TempBinary);
                            }
                            else if(InputType.equals("Binary")){
                                Result.setText("-"+TempBinary);
                            }
                        }
                        TempBinary = Integer.toBinaryString(Temp1);
                        Decimal1.setText(TempDecimal1);
                        Decimal2.setText(TempDecimal2);
                    }
                    else if(OperatorCall.equals("- (Pengurangan)")){
                        TempDecimal1 = Integer.toString(Temp1);
                        TempDecimal2 = Integer.toString(Temp2);
                        Temp1 = Temp1 - Temp2;
                        TempDecimal3 = Integer.toString(Temp1);
                        if(Temp1>=0){
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            Result.setText("0"+TempBinary);
                        }
                        else{
                            Temp1 = Temp1*-1;
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            if(InputType.equals("Sign/Magnitude")){
                                Result.setText("1"+TempBinary);
                            }
                            else if(InputType.equals("Binary")){
                                Result.setText("-"+TempBinary);
                            }
                        }
                        Decimal1.setText(TempDecimal1);
                        Decimal2.setText(TempDecimal2);
                        ResultDecimal.setText(TempDecimal3);
                    }
                    else if(OperatorCall.equals("/ (Pembagian)")){
                        TempDecimal1 = Integer.toString(Temp1);
                        TempDecimal2 = Integer.toString(Temp2);
                        Temp1 = Temp1 / Temp2;
                        TempDecimal3 = Integer.toString(Temp1);
                        TempBinary = Integer.toBinaryString(Temp1);
                        if(Temp1>=0){
                            TempBinary = Integer.toBinaryString(Temp1);;
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            Result.setText("0"+TempBinary);
                        }
                        else{
                            Temp1 = Temp1*-1;
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            if(InputType.equals("Sign/Magnitude")){
                                Result.setText("1"+TempBinary);
                            }
                            else if(InputType.equals("Binary")){
                                Result.setText("-"+TempBinary);
                            }
                        }
                        Decimal1.setText(TempDecimal1);
                        Decimal2.setText(TempDecimal2);
                        ResultDecimal.setText(TempDecimal3);
                    }
                    else if(OperatorCall.equals("x (Perkalian)")) {
                        String TempDecimal1 = Integer.toString(Temp1);
                        String TempDecimal2 = Integer.toString(Temp2);
                        Temp1 = Temp1 * Temp2;
                        String TempDecimal3 = Integer.toString(Temp1);
                        TempBinary = Integer.toBinaryString(Temp1);
                        if(Temp1>=0){
                            TempBinary = Integer.toBinaryString(Temp1);;
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            Result.setText("0"+TempBinary);
                        }
                        else{
                            Temp1 = Temp1*-1;
                            TempBinary = Integer.toBinaryString(Temp1);
                            while(TempBinary.length()<3){
                                TempBinary = "0"+TempBinary;
                            }
                            if(InputType.equals("Sign/Magnitude")){
                                Result.setText("1"+TempBinary);
                            }
                            else if(InputType.equals("Binary")){
                                Result.setText("-"+TempBinary);
                            }
                        }
                        Decimal1.setText(TempDecimal1);
                        Decimal2.setText(TempDecimal2);
                        ResultDecimal.setText(TempDecimal3);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Check Your Input Type", Toast.LENGTH_SHORT).show();
                }

                break;


        }
    }
}
