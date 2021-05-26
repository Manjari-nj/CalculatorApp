package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.text.Spanned;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.Spanned;

public class MainActivity extends AppCompatActivity {

    private EditText display;
//    Pattern p;
//    Button b;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

       

       // p=Pattern.compile("^\\d+(\\.\\d+)?\\d{0,9}?([+\\-*\\/]\\d+(\\.\\d+)?)*$");
//        p= Pattern.compile("^(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[-+/*%]|$))+$");
//        b=findViewById(R.id.equalsBTN);
//        c=findViewById(R.id.clearBTN);
//
//        display.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Matcher m=p.matcher(display.getText().toString());
//                if(!m.matches()) {
//                    display.setError("Enter the Valid Number");
//                   b.setEnabled(false);
//
//                }
//                else{
//                    b.setEnabled(true);
//
//
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//
//        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private boolean doesStringContainDecimal(String lastNumber) {
        return lastNumber.contains(".");
    }

    private String getLastNumberEntered(String display) {
        for (int index = display.length() -1; index >= 0; index--) {
            String character = String.valueOf(display.charAt(index));
            if(Pattern.matches("[-+/*()]", character)){
                return display.substring(index+1);
            }
        }
        return display;
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }


    public void zeroBTN(View view) {
        updateText("0");
    }

    public void oneBTN(View view) {
        updateText("1");
    }

    public void twoBTN(View view) {
        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");
    }

    public void fourBTN(View view) {
        updateText("4");
    }

    public void fiveBTN(View view) {
        updateText("5");
    }

    public void sixBTN(View view) {
        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");
    }

    public void eightBTN(View view) {
        updateText("8");
    }

    public void nineBTN(View view) {
        updateText("9");
    }

    public void pointBTN(View view) {
     int cursorPos = display.getSelectionStart();
        String lastNumber=getLastNumberEntered(display.getText().toString());
        if (TextUtils.isEmpty(lastNumber)) {
           updateText("0.");
            display.setSelection(cursorPos +2);


        }
        else if(doesStringContainDecimal(lastNumber)){

            return;
        }
        else {
            updateText(".");
           display.setSelection(cursorPos + 1);

        }
    }

    public void clearBTN(View view) {
        display.setText("");
    }

    public void parenthesisBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();


        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");

        } else if (closedPar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");

        }
        display.setSelection(cursorPos + 1);
    }

    public void addBTN(View view) {

        if (display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        } else {

            updateText("+");
        }

    }

    public void subBTN(View view) {

        if (display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        } else {
            updateText("-");
        }

    }

    public void plusminusBTN(View view) {
        updateText("-");

    }

    public void multiplyBTN(View view) {
        if (display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        } else {
            updateText("✕");
        }

    }

    public void divideBTN(View view) {
        if (display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        } else {
            updateText("÷");
        }

    }

    public void percentageBTN(View view) {
        long start = System.currentTimeMillis();
        if (!display.getText().toString().equals("")) {
            double amount = Double.parseDouble(display.getText().toString());
            double res = (amount / 100.0f) * 10;
            display.setText(Double.toString(res));
            updateText("");

        } else {
            Toast.makeText(getApplicationContext(), "Enter number before performing operation", Toast.LENGTH_SHORT).show();

        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        Toast.makeText(MainActivity.this,  elapsedTime+"ms", Toast.LENGTH_SHORT).show();

    }


    public void backspaceBTN (View view)
    {
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();

        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);

        }

    }
    public void equalsBTN (View view) {

        long start = System.currentTimeMillis();

        String userExp = display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("✕","*");

        Expression exp = new Expression(userExp);

        if(display.getText().toString().isEmpty()) {

            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            String result = String.valueOf(exp.calculate());
            display.setText(result);
            display.setSelection(result.length());


        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        Toast.makeText(MainActivity.this, elapsedTime+"ms", Toast.LENGTH_SHORT).show();


    }
}

