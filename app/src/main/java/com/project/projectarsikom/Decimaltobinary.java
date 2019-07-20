package com.project.projectarsikom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.spec.ECField;

public class Decimaltobinary extends AppCompatActivity implements View.OnClickListener {
    Spinner spinnerTypeConverter;
    String OperatorCall, TempData, Penampung, PenampungBinary;
    TextView TextView1, TextView2, TextView1Result, TextView2Result, TextView3Result, TextView4Result, TextView5Result;
    Button Converter, ClearCon;
    EditText InputData;
    int Result, key;
    boolean found;
    String PenampungKhusus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimaltobinary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerTypeConverter = findViewById(R.id.spinnerTypeConverter);

        Converter = findViewById(R.id.Converter);
        Converter.setOnClickListener(this);
        TextView1Result = findViewById(R.id.TextView1Result);
        TextView1Result.setOnClickListener(this);
        TextView2Result = findViewById(R.id.TextView2Result);
        TextView2Result.setOnClickListener(this);
        TextView3Result = findViewById(R.id.TextView3Result);
        TextView3Result.setOnClickListener(this);
        TextView4Result = findViewById(R.id.TextView4Result);
        TextView4Result.setOnClickListener(this);
        TextView5Result = findViewById(R.id.TextView5Result);
        TextView5Result.setOnClickListener(this);
        InputData = findViewById(R.id.InputData);
        ClearCon = findViewById(R.id.ClearConverter);
        ClearCon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ClearConverter:
                InputData.setText("");
                TextView1Result.setText("");
                TextView2Result.setText("");
                TextView3Result.setText("");
                TextView4Result.setText("");
                TextView5Result.setText("");
                break;
            case R.id.Converter:
                OperatorCall = spinnerTypeConverter.getSelectedItem().toString();
                TempData = InputData.getText().toString();
                try {
                    if (OperatorCall.equals("Decimal") && TempData.charAt(0)!='-') {
                        Result = Integer.parseInt(TempData);
                        Penampung = Integer.toBinaryString(Result);
                        TextView1Result.setText(TempData);
                        while(Penampung.length()<3){
                            Penampung = "0"+Penampung;
                        }
                        if(Result==0){
                            TextView1Result.setText("+0");
                            TextView2Result.setText("0000");
                            TextView3Result.setText("0000");
                            TextView4Result.setText("0000");
                            TextView5Result.setText("0000");
                        }
                        else{
                            TextView2Result.setText("0"+Penampung);
                            TextView3Result.setText("0"+Penampung);
                            TextView4Result.setText("0"+Penampung);
                            TextView5Result.setText("0"+Penampung);
                        }
                    }
                    else if (OperatorCall.equals("Decimal") && TempData.charAt(0)=='-') {
                        Result = Integer.parseInt(TempData);
                        Result = Result * -1;
                        Penampung = Integer.toBinaryString(Result);
                        TextView1Result.setText(TempData);
                        while(Penampung.length()<3){
                            Penampung = "0"+Penampung;
                        }
                        if(Result==0){
                            TextView1Result.setText("-0");
                            TextView2Result.setText("0000");
                            TextView3Result.setText("1000");
                            TextView4Result.setText("1111");
                            TextView5Result.setText("1000");
                        }
                        else{
                            String ones="", twos="";
                            TextView2Result.setText("0"+Penampung);
                            for(int i=0;i<Penampung.length();i++){
                                ones = ones + flip(Penampung.charAt(i));
                            }
                            found = false;
                            for(int i=Penampung.length()-1;(i>=0&&!found);i--){
                                if(Penampung.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                            }
                            for(int i=0;i<Penampung.length();i++){
                                if(i>=key){
                                    twos = twos + Penampung.charAt(i);
                                }
                                else{
                                    twos = twos + flip(Penampung.charAt(i));
                                }
                            }
                            TextView3Result.setText("1"+Penampung);
                            TextView4Result.setText("1"+ones);
                            TextView5Result.setText("1"+twos);
                        }
                    }
                    else if (OperatorCall.equals("Binary")) {
                        Result = Integer.parseInt(TempData, 2);
                        Penampung = Integer.toString(Result);
                        PenampungBinary = TempData.charAt(0)=='0'? "":"0";
                        PenampungBinary += TempData;
                        while(PenampungBinary.length()<4){
                            PenampungBinary = "0"+PenampungBinary;
                        }
                        TextView1Result.setText(Penampung);
                        TextView2Result.setText(PenampungBinary);
                        TextView3Result.setText(PenampungBinary);
                        TextView4Result.setText(PenampungBinary);
                        TextView5Result.setText(PenampungBinary);
                    }
                    else if (OperatorCall.equals("Sign/Magnitude")) {
                        PenampungBinary = "";
                        if(TempData.charAt(0)=='0' && TempData.charAt(1)=='1'&& TempData.length()>4){
                            for(int i=1;i<TempData.length();i++){
                                PenampungBinary += TempData.charAt(i);
                            }
                            Result = Integer.parseInt(PenampungBinary,2);
                            Penampung = Integer.toString(Result);
                            while(PenampungBinary.length()<3){
                                PenampungBinary = "0"+PenampungBinary;
                            }
                            TextView1Result.setText(Penampung);
                            TextView2Result.setText(PenampungBinary);
                            TextView3Result.setText("0"+PenampungBinary);
                            TextView4Result.setText("0"+PenampungBinary);
                            TextView5Result.setText("0"+PenampungBinary);
                        }
                        else if(TempData.charAt(0)=='1' && TempData.charAt(1)=='1' && TempData.length()>4){
                            found = false;
                            int i = (TempData.length()-1);
                            while (i>=0 && found == false){
                                if(TempData.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                                i--;
                            }
                            String ones = "1", twos = "1";
                            for(i=1;i<TempData.length();i++) {
                                PenampungBinary += TempData.charAt(i);
                                ones += flip(TempData.charAt(i));
                                if(i>=key){
                                    twos += TempData.charAt(i);
                                }
                                else{
                                    twos += flip(TempData.charAt(i));
                                }
                            }
                            Result = Integer.parseInt(PenampungBinary,2);
                            Penampung = Integer.toString(Result);
                            while(PenampungBinary.length()<3){
                                PenampungBinary = "0"+PenampungBinary;
                            }
                            TextView1Result.setText("-"+Penampung);
                            TextView2Result.setText("0"+PenampungBinary);
                            TextView3Result.setText("1"+PenampungBinary);
                            TextView4Result.setText(ones);
                            TextView5Result.setText(twos);
                        }
                        else if(TempData.charAt(0)=='1' && TempData.length()==4){
                            String ones = "1", twos ="1";
                            found = false;
                            int i = (TempData.length()-1);
                            while (i>=0 && found == false){
                                if(TempData.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                                i--;
                            }
                            for(i=1;i<TempData.length();i++){
                                PenampungBinary += TempData.charAt(i);
                                ones += flip(TempData.charAt(i));
                                if(i>=key){
                                    twos += TempData.charAt(i);
                                }
                                else{
                                    twos += flip(TempData.charAt(i));
                                }
                            }
                            Result = Integer.parseInt(PenampungBinary,2);
                            Penampung = Integer.toString(Result);
                            while(PenampungBinary.length()<3){
                                PenampungBinary = "0"+PenampungBinary;
                            }
                            if(Result==0 && TempData.charAt(0)=='1'){
                                TextView2Result.setText("0000");
                                TextView3Result.setText("1000");
                                TextView4Result.setText("1111");
                                TextView5Result.setText("1000");
                                TextView1Result.setText("-0");
                            }
                            else if(Result==0 && TempData.charAt(0)=='0'){
                                TextView2Result.setText(TempData);
                                TextView3Result.setText(TempData);
                                TextView4Result.setText(TempData);
                                TextView5Result.setText(TempData);
                                TextView1Result.setText(Result);
                            }
                            else {
                                TextView1Result.setText("-"+Penampung);
                                TextView2Result.setText("0"+PenampungBinary);
                                TextView3Result.setText("1"+PenampungBinary);
                                TextView4Result.setText(ones);
                                TextView5Result.setText(twos);
                            }
                        }
                        else if(TempData.charAt(0)=='0' && TempData.length()==4){
                            for(int i=1;i<TempData.length();i++){
                                PenampungBinary += TempData.charAt(i);
                            }
                            Result = Integer.parseInt(PenampungBinary,2);
                            Penampung = Integer.toString(Result);
                            while(PenampungBinary.length()<3){
                                PenampungBinary = "0"+PenampungBinary;
                            }
                            TextView1Result.setText(Penampung);
                            TextView2Result.setText(PenampungBinary);
                            TextView3Result.setText("0"+PenampungBinary);
                            TextView4Result.setText("0"+PenampungBinary);
                            TextView5Result.setText("0"+PenampungBinary);
                        }
                        else{
                            InputData.setText("");
                            TextView1Result.setText("");
                            TextView2Result.setText("");
                            TextView3Result.setText("");
                            TextView4Result.setText("");
                            TextView5Result.setText("");
                            Toast.makeText(getApplicationContext(), "Only In Sign Binary", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (OperatorCall.equals("One s Complement")) {
                        if(TempData.charAt(0)=='0'&& TempData.length()>4){
                            PenampungBinary = "0";
                            found = false;
                            for(int i=0 ; i<TempData.length();i++){
                                if(TempData.charAt(i)=='1' || found){
                                    PenampungBinary += TempData.charAt(i);
                                    found = true;
                                }
                            }
                            Penampung = PenampungBinary;
                            Result = Integer.parseInt(Penampung, 2);
                            Penampung = Integer.toString(Result);
                            TextView1Result.setText(Penampung);
                            TextView2Result.setText(Integer.toBinaryString(Result));
                            TextView3Result.setText(PenampungBinary);
                            TextView4Result.setText(PenampungBinary);
                            TextView5Result.setText(PenampungBinary);
                        }
                        else if(TempData.charAt(0)=='1' && TempData.length()==4){
                            try {
                                String twos = "1";
                                found = false;
                                PenampungBinary = "";
                                for (int i = 1; i < TempData.length(); i++) {
                                    PenampungBinary += flip(TempData.charAt(i));
                                }
                                PenampungKhusus = "1" + PenampungBinary;
                                int i = (PenampungKhusus.length() - 1);
                                while (i >= 0 && found == false) {
                                    if (PenampungKhusus.charAt(i) == '1') {
                                        key = i;
                                        found = true;
                                    }
                                    i--;
                                }
                                for (i = 1; i < PenampungKhusus.length(); i++) {
                                    if (i >= key) {
                                        twos += PenampungKhusus.charAt(i);
                                    } else {
                                        twos += flip(PenampungKhusus.charAt(i));
                                    }
                                }
                                Penampung = PenampungBinary;
                                Result = Integer.parseInt(Penampung, 2);
                                Penampung = Integer.toString(Result);
                                TextView1Result.setText("-" + Penampung);
                                TextView2Result.setText("0" + PenampungBinary);
                                TextView4Result.setText(TempData);
                                TextView3Result.setText("1" + PenampungBinary);
                                TextView5Result.setText(twos);
                            }
                            catch (Exception e){
                                if (TempData=="1111"){
                                    TextView1Result.setText("-0");
                                    TextView2Result.setText("0000");
                                    TextView4Result.setText("1111");
                                    TextView3Result.setText("1000");
                                    TextView5Result.setText("1000");
                                }
                            }
                        }
                        else if(TempData.charAt(0)=='0' && TempData.length()==4){
                            String ones = "0";
                            for(int i=1 ; i<TempData.length();i++){
                                ones += TempData.charAt(i);
                            }
                            Penampung = ones;
                            Result = Integer.parseInt(Penampung, 2);
                            Penampung = Integer.toString(Result);
                            TextView1Result.setText(Penampung);
                            TextView2Result.setText(ones);
                            TextView3Result.setText(TempData);
                            TextView4Result.setText(TempData);
                            TextView5Result.setText(TempData);
                        }
                        else if(TempData.charAt(0)=='1' && TempData.charAt(1)=='0' && TempData.length()>4){
                            String ones = "1", twos = "1";
                            PenampungBinary = "";
                            for(int i=1 ; i<TempData.length();i++){
                                ones += flip(TempData.charAt(i));
                                PenampungBinary += flip(TempData.charAt(i));
                            }
                            PenampungKhusus = "1"+PenampungBinary;
                            found = false;
                            int i = (PenampungKhusus.length()-1);
                            while (i>=0 && found == false){
                                if(PenampungKhusus.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                                i--;
                            }
                            for(i=1 ; i<PenampungKhusus.length();i++){
                                if(i>=key){
                                    twos += PenampungKhusus.charAt(i);
                                }
                                else{
                                    twos += flip(PenampungKhusus.charAt(i));
                                }
                            }
                            Penampung = PenampungBinary;
                            Result = Integer.parseInt(Penampung, 2);
                            Penampung = Integer.toString(Result);
                            TextView1Result.setText("-"+Penampung);
                            TextView2Result.setText("0"+PenampungBinary);
                            TextView3Result.setText(ones);
                            TextView4Result.setText(TempData);
                            TextView5Result.setText(twos);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Only In One's Complement Binary", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if (OperatorCall.equals("Two s Complement")) {
                        String twos = "";
                        found = false;
                        int i = (TempData.length()-1);
                        while (i>=0 && found == false){
                            if(TempData.charAt(i)=='1'){
                                key = i;
                                found = true;
                            }
                            i--;
                        }
                        for(i=1;i<TempData.length();i++){
                            if(i>=key){
                                twos += TempData.charAt(i);
                            }
                            else{
                                twos += flip(TempData.charAt(i));
                            }
                        }
                        Result = Integer.parseInt(twos,2);
                        if((TempData.charAt(0)=='1' && TempData.charAt(1)=='0'&& TempData.length()>=4)||(TempData.length()==4 && TempData.charAt(0)=='1')){
                            String ones = "1";
                            twos = "1";
                            found = false;
                            Penampung = Integer.toString((-1*Result));
                            if(Penampung.charAt(0)=='0'){
                                Penampung = "-"+Penampung;
                            }
                            TextView1Result.setText(Penampung);
                            PenampungBinary = Integer.toBinaryString(Result);
                            PenampungKhusus = "0"+PenampungBinary;
                            i = (PenampungKhusus.length()-1);
                            while (i>=0 && found == false){
                                if(PenampungKhusus.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                                i--;
                            }
                            for(i=1; i<PenampungKhusus.length(); i++){
                                ones += flip(PenampungKhusus.charAt(i));
                                if(i>=key){
                                    twos += PenampungKhusus.charAt(i);
                                }
                                else{
                                    twos += flip(PenampungKhusus.charAt(i));
                                }
                            }
                            if(Result <= 3){
                                if(Result == 1) {
                                    TextView2Result.setText("000"+PenampungBinary);
                                    TextView3Result.setText("100"+PenampungBinary);
                                    TextView4Result.setText("1110");
                                    TextView5Result.setText(TempData);
                                }
                                else if(Result==0){
                                    TextView2Result.setText("000"+PenampungBinary);
                                    TextView3Result.setText("100"+PenampungBinary);
                                    TextView4Result.setText("1111");
                                    TextView5Result.setText(TempData);
                                }
                                else{
                                    TextView2Result.setText("00"+PenampungBinary);
                                    TextView3Result.setText("10"+PenampungBinary);
                                    TextView4Result.setText("1"+ones);
                                    TextView5Result.setText(TempData);
                                }
                            }
                            else{
                                TextView2Result.setText("0"+PenampungBinary);
                                TextView3Result.setText("1"+PenampungBinary);
                                TextView4Result.setText(ones);
                                TextView5Result.setText(twos);
                            }

                        }
                        else if(TempData.charAt(0)=='0' && TempData.length()>=4){
                            Result = Integer.parseInt(TempData,2);
                            Penampung = Integer.toString(Result);
                            TextView1Result.setText(Penampung);
                            PenampungBinary = Integer.toBinaryString(Result);
                            TextView2Result.setText(TempData);
                            TextView3Result.setText(TempData);
                            TextView4Result.setText(TempData);
                            TextView5Result.setText(TempData);
                        }
                        else if(key==1){
                            String ones = "1";
                            twos = "1";
                            found = false;
                            Penampung = Integer.toString((-1*Result));
                            TextView1Result.setText(Penampung);
                            PenampungBinary = Integer.toBinaryString(Result);
                            PenampungKhusus = "0"+PenampungBinary;
                            i = (PenampungKhusus.length()-1);
                            while (i>=0 && found == false){
                                if(PenampungKhusus.charAt(i)=='1'){
                                    key = i;
                                    found = true;
                                }
                                i--;
                            }
                            for(i=1; i<PenampungKhusus.length(); i++){
                                ones += flip(PenampungKhusus.charAt(i));
                                if(i>=key){
                                    twos += PenampungKhusus.charAt(i);
                                }
                                else{
                                    twos += flip(PenampungKhusus.charAt(i));
                                }
                            }
                            TextView2Result.setText("0"+PenampungBinary);
                            TextView3Result.setText("1"+PenampungBinary);
                            TextView4Result.setText(ones);
                            TextView5Result.setText(twos);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Only In Binner two's Complemenet", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                    InputData.setText("");
                    TextView1Result.setText("");
                    TextView2Result.setText("");
                    TextView3Result.setText("");
                    TextView4Result.setText("");
                    TextView5Result.setText("");
                    Toast.makeText(getApplicationContext(), "Check Type Input", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public char flip(char c){
        return c == '0'? '1':'0';
    }
}
