package com.example.dwiprayogo.cobatanggal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by dwi.prayogo on 9/4/2017.
 */

public class Login extends AppCompatActivity {
    RelativeLayout lay_logo;
    LinearLayout lay_name,lay_loginID;
    EditText edt_loginID,edtname;
    Button btn_check,btn_signin;
    Button btn_pindah,btn_pindah2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        lay_logo = (RelativeLayout) findViewById(R.id.lay_logo);
        lay_name = (LinearLayout) findViewById(R.id.lay_name);
        lay_loginID = (LinearLayout) findViewById(R.id.lay_loginID);

        edt_loginID = (EditText) findViewById(R.id.edt_loginID);
        edtname = (EditText) findViewById(R.id.edtname);

        btn_check = (Button) findViewById(R.id.btn_check);
        btn_signin = (Button) findViewById(R.id.btn_signin);

        btn_pindah = (Button) findViewById(R.id.btn_pindah);
        btn_pindah2 = (Button) findViewById(R.id.btn_pindah2);

        button();
    }

    private void button(){

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home_pertama.class);
                startActivity(intent);

            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home_pertama.class);
                startActivity(intent);
            }
        });

        btn_pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_loginID.setVisibility(View.VISIBLE);
                btn_pindah2.setVisibility(View.VISIBLE);
                lay_name.setVisibility(View.GONE);
                btn_pindah.setVisibility(View.GONE);


            }
        });

        btn_pindah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_loginID.setVisibility(View.GONE);
                btn_pindah2.setVisibility(View.GONE);
                lay_name.setVisibility(View.VISIBLE);
                btn_pindah.setVisibility(View.VISIBLE);
            }
        });

    }


}
