package com.bw.wangyao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText ked1;
    private TextView ktext1;
    private TextView klt;
    private Button klb;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            //控件
        ked1 = findViewById(R.id.ed1);
        ktext1 = findViewById(R.id.text1);
        klt = findViewById(R.id.liut);
        klb = findViewById(R.id.liub);


        klb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klt.setText("");
            }
        });


        //点击事件
        ktext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ked1.getText().toString();
                klt.setText(s);
                Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                     startActivity(intent);


            }
        });
    }
}
