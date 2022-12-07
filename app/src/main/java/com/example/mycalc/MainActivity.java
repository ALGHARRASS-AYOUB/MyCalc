package com.example.mycalc;

import static com.example.mycalc.R.id.*;
import static com.example.mycalc.R.id.resultat;

import  org.mozilla.javascript.Context;
import  org.mozilla.javascript.Scriptable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mycalc.R.id;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bce, bequal, bplus, bmin, bmul, bdiv, bb1, bb2, bc, bac;
    TextView resultat, main;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.standard:

//                startActivity(new Intent(this, MainActivity.class));

                return true;

            case scientific:

//
//                startActivity(new Intent(this, Activity_Scientific.class));
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignID(b1, R.id.b1);
        assignID(b2, R.id.b2);
        assignID(b3, R.id.b3);
        assignID(b4, R.id.b4);
        assignID(b5, R.id.b5);
        assignID(b6, R.id.b6);
        assignID(b7, R.id.b7);
        assignID(b8, R.id.b8);
        assignID(b9, R.id.b9);
        assignID(b0, R.id.b0);
        assignID(bplus, R.id.bplus);
        assignID(bmin, R.id.bmin);
        assignID(bdiv, R.id.bdiv);
        assignID(bmul, R.id.bmul);
        assignID(bb1, R.id.bb1);
        assignID(bb2, R.id.bb2);
        assignID(bdot, R.id.bdot);
        assignID(bc, R.id.bc);
        assignID(bac, R.id.bac);
        resultat=findViewById(R.id.resultat);
        main=findViewById(R.id.main);




    }

    @Override
    public void onClick(View view) {
        Button btn=(Button) view;
        String btn_text=btn.getText().toString();
        String dataToCalc=resultat.getText().toString();


        if (btn_text.equals("C")){
            resultat.setText("");
             main.setText("0");
             return;
        }

        if(btn_text.equals("=")){
            resultat.setText(main.getText());
            return;
        }


        if(btn_text.equals("AC")){
            if(resultat.getText().toString().equals("")){
                main.setText("0");

                resultat.setText("");
            }
            else {
                dataToCalc=dataToCalc.substring(0,dataToCalc.length()-1);
                resultat.setText(dataToCalc);
            }
        }
        else{
            dataToCalc+=btn_text;
        }
        resultat.setText(dataToCalc);
        String finalResult=getResult(dataToCalc);
        if (!finalResult.equals("error")){
            main.setText(finalResult);
        }




    }


    void assignID(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener((View.OnClickListener) this);
    }

    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String res=context.evaluateString(scriptable,data,"javascript",1,null).toString();

        return res;
        }catch (Exception e){
            return "error";
        }
    }

}