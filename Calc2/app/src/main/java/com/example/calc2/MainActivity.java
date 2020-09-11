package com.example.calc2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.view.FocusFinder;
import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    String text = "";           // number(type:str) after hit button or calculated result
    String formula = "";        // formula(type:str) for calculation
    float res = 0;              // result value(type:int or float) after calculation
    float cur = 0;              // current value(type:int or float) in text edit
    boolean in_op = false;      // operation(type:bool) whether in operation( +, -, *, /, = ) or not
    char last_op = '=';         // last operation mode(type:str) +, -, *, /, =


    private void init_data() {
        text = "0";
        formula = "";
        res = 0;
        cur = 0;
        in_op = false;
        last_op = 'e';
    }

    private float do_calc ( char op, float i, float j ) {
        if ( op == 'a' )
            return i + j;
        else if ( op == 's' )
            return i - j;
        else if ( op == 'm' )
            return i * j;
        else if ( op == 'd' )
            return i / j;
        else if ( op == 'e' )
            return i;
        else
            return 0;
    }

    // Create an anonymous implementation of OnClickListener
    private OnClickListener mListener = new OnClickListener() {
        public void onClick(View v) {
            // do something when the button is clicked
            String btn_id_str = v.getResources().getResourceName(v.getId());
            String btn = btn_id_str.substring(btn_id_str.length()-1);
            System.out.println("Button Clicked: " + btn);

            if ( btn.charAt(0) >= '0' && btn.charAt(0) <= '9') {
                if (in_op) {
                    if (last_op == 'e')
                        init_data();
                    text = btn;
                    in_op = false;
                }
                else
                    text = text + btn;

                // when starting '0', ignore '0'
                float text_f = Float.parseFloat(text);
                text = Float.toString(text_f);
                if ( text.indexOf('.') != -1 && text.charAt(text.length() -1) == '0' )
                    text = text.substring(0, text.length() -2 );
            }
            else if ( btn.charAt(0) == 'j') {
                if (in_op) {
                    if (last_op == 'e')
                        init_data();
                    text = "'0.";
                    in_op = false;
                }
                else {
                    if (text.indexOf('.') != -1)
                        return ;
                    text = text + btn;
                }
            }
            else if ( btn.charAt(0) == 'c' ) {
                init_data();
            }
            else if ( btn.charAt(0) == 'b' ) {
                if (in_op)
                    return ;

                if ( text.length() > 1 )
                    text = text.substring(0, text.length() -1);
                else
                    text = "";
            }
            else if ( btn.charAt(0) == 'd' || btn.charAt(0) == 'm' || btn.charAt(0) == 's' || btn.charAt(0) == 'a' ) {
                if (in_op && last_op != 'e') {
                    last_op = btn.charAt(0);
                    formula = formula.substring(0, formula.length() -2) + ' ' + btn;
                    //self.lineEdit_fo.setText(self.formula)
                    return;
                }

                if (text.length() == 0)
                    return;

                cur = Float.parseFloat(text);
                if (res == 0)
                    res = cur;
                else
                    res = do_calc(last_op, res, cur);
                String res_s = Float.toString(res);
                if ( res_s.indexOf('.') != -1 && res_s.charAt(res_s.length() -1) == '0' )
                    res_s = res_s.substring(0, res_s.length() -2 );

                if (last_op == 'e')
                    formula = res_s + ' ' + btn;
                else
                    formula = formula + ' ' + res_s + ' ' + btn;
                text = res_s;
                in_op = true;
                last_op = btn.charAt(0);
            }
            else if ( btn.charAt(0) == 'e' ) {
                if ( in_op || text.length() == 0 )
                    return ;

                cur = Float.parseFloat(text);
                res = do_calc(last_op, res, cur);
                String res_s = Float.toString(res);
                if ( res_s.indexOf('.') != -1 && res_s.charAt(res_s.length() -1) == '0' )
                    res_s = res_s.substring(0, res_s.length() -2 );

                formula = formula + ' ' + text + ' ' + btn;
                text = res_s;
                in_op = true;
                last_op = btn.charAt(0);
            }

            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText(text);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_0 = (Button)findViewById(R.id.button_0);
        button_0.setOnClickListener(mListener);
        Button button_1 = (Button)findViewById(R.id.button_1);
        button_1.setOnClickListener(mListener);
        Button button_2 = (Button)findViewById(R.id.button_2);
        button_2.setOnClickListener(mListener);
        Button button_3 = (Button)findViewById(R.id.button_3);
        button_3.setOnClickListener(mListener);
        Button button_4 = (Button)findViewById(R.id.button_4);
        button_4.setOnClickListener(mListener);
        Button button_5 = (Button)findViewById(R.id.button_5);
        button_5.setOnClickListener(mListener);
        Button button_6 = (Button)findViewById(R.id.button_6);
        button_6.setOnClickListener(mListener);
        Button button_7 = (Button)findViewById(R.id.button_7);
        button_7.setOnClickListener(mListener);
        Button button_8 = (Button)findViewById(R.id.button_8);
        button_8.setOnClickListener(mListener);
        Button button_9 = (Button)findViewById(R.id.button_9);
        button_9.setOnClickListener(mListener);

        Button button_c = (Button)findViewById(R.id.button_c);
        button_c.setOnClickListener(mListener);
        Button button_b = (Button)findViewById(R.id.button_b);
        button_b.setOnClickListener(mListener);

        Button button_d = (Button)findViewById(R.id.button_d);
        button_d.setOnClickListener(mListener);
        Button button_m = (Button)findViewById(R.id.button_m);
        button_m.setOnClickListener(mListener);
        Button button_s = (Button)findViewById(R.id.button_s);
        button_s.setOnClickListener(mListener);
        Button button_a = (Button)findViewById(R.id.button_a);
        button_a.setOnClickListener(mListener);
        Button button_e = (Button)findViewById(R.id.button_e);
        button_e.setOnClickListener(mListener);
    }
}