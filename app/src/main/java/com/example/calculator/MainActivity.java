package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final Button clearBTN = findViewById(R.id.clearBTN);
        //final Button equalsBTN = findViewById(R.id.equalsBTN);
        display= findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

       // display.setLongClickable(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  clearBTN.setBackgroundColor(getResources().getColor(R.color.redBackground));
             //   equalsBTN.setBackgroundColor(getResources().getColor(R.color.greenBackground));
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos+1);
        }
        else
        {
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
            display.setSelection(cursorPos+1);
        }

    }

    public void zeroBTN (View view)
    {
      updateText("0");
    }
    public void oneBTN (View view)
    {
        updateText("1");
    }
    public void twoBTN (View view)
    {
        updateText("2");
    }
    public void threeBTN (View view)
    {
        updateText("3");
    }
    public void fourBTN (View view)
    {
        updateText("4");
    }
    public void fiveBTN (View view)
    {
        updateText("5");
    }
    public void sixBTN (View view)
    {
        updateText("6");
    }
    public void sevenBTN (View view)
    {
        updateText("7");
    }
    public void eightBTN (View view)
    {
        updateText("8");
    }
    public void nineBTN (View view)
    {
        updateText("9");
    }
    public void clearBTN (View view)
    {
        display.setText("");
    }
    public void parenthesisBTN (View view) {
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

        public void addBTN (View view)
    {
        if(display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
        updateText("+");
    }
        //btn.setEnabled(!display.getText().toString().isEmpty());
    }
    public void subBTN (View view)
    {

        if(display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            updateText("-");
        }
    }
    public void plusminusBTN (View view)
    {
        updateText("-");
    }
    public void multiplyBTN (View view)
    {
        if(display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            updateText("✕");
        }
    }
    public void divideBTN (View view)
    {
        if(display.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter number before performing operation", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            updateText("÷");
        }
    }
    public void percentageBTN (View view)
    {

        if (!display.getText().toString().equals("")) {
            double amount = Double.parseDouble(display.getText().toString());
            double res = (amount / 100.0f) * 10;
            display.setText(Double.toString(res));
            updateText("");

        } else{
            Toast.makeText(getApplicationContext(), "Enter number before performing operation", Toast.LENGTH_SHORT).show();

        }

    }
    public void pointBTN (View view)
    {
        updateText(".");
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
        }
    }
