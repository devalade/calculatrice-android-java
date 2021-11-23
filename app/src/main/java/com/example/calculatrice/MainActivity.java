package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    public enum Ops {
        PLUS("+"),
        MOINS("-"),
        FOIS("*"),
        DIV("/"),
        VIRGULE(",");

        private String name = "";
        Ops(String name){this.name = name;}
        public String toString(){return name;}
    }

    public enum Ops1 {
        POURC("%"),
        PLUSMOINS("+/-");

        private String name = "";
        Ops1(String name){this.name = name;}
        public String toString(){return name;}
    }


    private TextView screen;
    private double op1=0;
    private int i=0;
    private boolean une = false;
    private int vue = 0;
    private int signe = 0;
    private int vi=0;
    private int vi2=0;
    private int tvi=0;
    private int n=1;
    private int n2=1;
    private int n3=1;
    private double op2=0;
    private Ops operator=null;
    private boolean isOp1=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);
        Button btnEgal = (Button)findViewById(R.id.btnEgal);
        btnEgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                compute();
                i=0;
                //vi =0;
                n=n3;
                vue = 0;
                signe = 0;
                une =  false;
            }
        });

        Button btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();

            }
        });
    }

    private void updateDisplay() {
        double v=op1;
        if(!isOp1) {
            v=op2;
        }

        if (operator==null)
        {
            if (i!=0)
            {
                int var = (int)v;
                if (var == v)
                    screen.setText(var+"");
                else
                    screen.setText(String.format("% .2f",v));
            }
            else if(vi2!=0)
                screen.setText(String.format("% .2f",v));
            else if (vi==0)
                screen.setText(String.format((int)v+""));
            else
            {
                if ( op1 < 1000000000)
                {
                    screen.setText(String.format("% .2f",v));
                }
                else
                {
                    Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                }
            }
                //screen.setText(v+"");
        }
        else if (i!=0 )
        {
            int var = (int)v;
            if (var == v)
                screen.setText(var);
            else
                screen.setText(String.format("% .2f",v));
        }
        else
        {
            if (vi==0)
            {
                if ( op2 <= 1000000000)
                {
                    if (vi2==0)
                        screen.setText(String.format((int)op1+"")+operator+String.format((int)op2+""));
                    else
                        screen.setText(String.format("% .2f", op1)+operator+String.format((int)op2+""));
                }
                else
                {
                    Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                if ( op2 < 1000000000)
                    {
                        if (vi2==0)
                            screen.setText(String.format((int)op1+"")+operator+String.format("% .2f", op2));
                        else
                            screen.setText(String.format("% .2f", op1)+operator+String.format("% .2f", op2));
                        //screen.setText(String.format("% .2f", op1)+operator+String.format("% .2f", op2));
                    }
                else
                    {
                        Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    }


    public void compute() {
        if(isOp1) {
            // do nothing
        } else {
            switch(operator) {
                case PLUS  : op1 = op1 + op2; break;
                case MOINS : op1 = op1 - op2; break;
                case FOIS  : op1 = op1 * op2; break;
                case DIV   : op1 = op1 / op2; break;
                default : return; // do nothing if no operator
            }
            vi2=vi;
            n2=n;
            op2 = 0;
            isOp1 = true;
            operator=null;
            une=true;
            vi =1;
            updateDisplay();
        }
    }


    private void clear() {
        op1 = 0;
        op2 = 0;
        vi = 0;
        vi2=0;
        n = 1;
        n2 = 1;
        n3 = 1;
        i=0;
        signe=0;
        operator = null;
        isOp1 = true;
        updateDisplay();
    }

    public void setOperator(View v) {
        switch (v.getId()) {
            case R.id.btnPlus        :
            {
                n2=n;
                vi2 = vi;
                vi = 0;
                n=1;
                signe++;
                if (signe>1)
                {
                    switch(operator) {
                        case PLUS  : op1 = op1 + op2; break;
                        case MOINS : op1 = op1 - op2; break;
                        case FOIS  : op1 = op1 * op2; break;
                        case DIV   : op1 = op1 / op2; break;
                        default : return; // do nothing if no operator
                    }
                    op2 = 0;
                }
                operator=Ops.PLUS;

            }break;
            case R.id.btnMoins       :
            {
                n2=n;
                vi2 = vi;
                vi = 0;
                n=1;
                signe++;
                if (signe>1)
                {
                    switch(operator) {
                        case PLUS  : op1 = op1 + op2; break;
                        case MOINS : op1 = op1 - op2; break;
                        case FOIS  : op1 = op1 * op2; break;
                        case DIV   : op1 = op1 / op2; break;
                        default : return; // do nothing if no operator
                    }
                    op2 = 0;
                }
                operator=Ops.MOINS;
            }break;
            case R.id.btnFois        :
            {
                n2=n;
                vi2 = vi;
                vi = 0;
                n=1;
                signe++;
                if (signe>1)
                {
                    switch(operator) {
                        case PLUS  : op1 = op1 + op2; break;
                        case MOINS : op1 = op1 - op2; break;
                        case FOIS  : op1 = op1 * op2; break;
                        case DIV   : op1 = op1 / op2; break;
                        default : return; // do nothing if no operator
                    }
                    op2 = 0;
                }
                operator=Ops.FOIS;
            }break;
            case R.id.btnDiv         :
            {
                n2=n;
                vi2 = vi;
                vi = 0;
                n=1;
                signe++;
                if (signe>1)
                {
                    switch(operator) {
                        case PLUS  : op1 = op1 + op2; break;
                        case MOINS : op1 = op1 - op2; break;
                        case FOIS  : op1 = op1 * op2; break;
                        case DIV   : op1 = op1 / op2; break;
                        default : return; // do nothing if no operator
                    }
                    op2 = 0;
                }
                operator=Ops.DIV;
            }break;
            default :
                Toast.makeText(this, "Opérateur non reconnu",Toast.LENGTH_LONG);
                return; // do nothing if no operator
        }
        isOp1=false;
        updateDisplay();
    }

    public void OperateurSecondaire(View v) {
        switch (v.getId()) {
            case R.id.btnpourcentage     :
                {
                    if (operator==null)
                        op1 = op1 / 100;
                    else
                        op2 = op2 / 100;
                }      break;
            case R.id.btnPlusMoins       :
            {
                if (operator==null)
                    op1 = op1 * (-1);
                else
                    op2 = op2 * (-1);
            }      break;
            case R.id.btnVirgule         : vi = vi + 1 ;         break;
            default :
                Toast.makeText(this, "Opérateur non reconnu",Toast.LENGTH_LONG);
                return; // do nothing if no operator
        }
        updateDisplay();
    }


    public void addNumber(View v){
        try {
//            if (operator!=null)
//            {
//                vue++;
//                if (vue < 2)
//                {
//                    n=1;
//                    vi = 0;
//                }
//            }
            //double val = Double.parseDouble(((Button)v).getText().toString());
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                if( vi == 0)
                {
                    double temp = (op1 * 10) + val;
                    if ( temp <= 1000000000)
                    {
                        op1 = temp;
                        updateDisplay();
                    }
                    else
                    {
                        Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                    }
                }
                else //if (vi==1)
                {
                    if ( op1 < 1000000000)
                    {

                        op1 = op1 + val/Math.pow(10,n);
                        n++;
                        n3=n;
                        updateDisplay();
                    }
                    else
                    {
                        Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                    }
                }
               // else{}

            } else {
                if (vi==0)
                {
                    double temp = (op2 * 10) + val;
                    if ( temp <= 1000000000)
                    {
                        op2 = temp;
                        updateDisplay();
                    }
                    else
                    {
                        Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                    }
                }
                else  //if (vi==1)
                {
                    if ( op2 < 1000000000)
                    {

                        op2 = op2 + val/Math.pow(10,n);
                        n++;
                        n3=n;
                        updateDisplay();
                    }
                    else
                    {
                        Toast.makeText(this, "Impossible de dépasser 1 milliard", Toast.LENGTH_SHORT).show();
                    }
                }
                //else{}
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    public void DelNumber(View v)
    {
        try
        {
            if (isOp1) {
                if (une)
                {
                    vi = vi2;
                    n = n2;
                    une = false;
                }
                if( vi == 0)
                {
                    op1 = (int)op1 /10 ;
                    if (op1==0)
                        op1 = (int)op1;
                    updateDisplay();
                }
                else// if (vi==1)
                {
                    op1 = (int)((op1 * Math.pow(10,n-1))/10) /  Math.pow(10,n-2);
                    n--;
                    n3=n;
                    if (n==1)
                    {
                        vi = 0;
                        n=1;
                        n3=n;
                    }
                    updateDisplay();
                }
                //else{}
            } else {
                if (vi==0)
                {
                    op2 = (int)op2 /10 ;
                    updateDisplay();
                }
                else //if (vi==1)
                    {
                        op2 = (int)((op2 * Math.pow(10,n-1))/10) /  Math.pow(10,n-2);
                        n--;
                        n3=n;
                        if (n==1)
                        {
                            vi = 0;
                            n=1;
                            n3=n;
                        }
                        updateDisplay();
                    }
               // else{}
                if (op1 != 0 && op1 != 0.0 && op2 == 0)
                {
                    isOp1=true;
                    signe--;
                    operator=null;
                    une = true ;
                    updateDisplay();
                }

            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Rien à supprimer",Toast.LENGTH_LONG);
        }

    }
}