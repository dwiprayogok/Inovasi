package com.example.dwiprayogo.cobatanggal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 11/14/2017.
 */

public class Login_2 extends AppCompatActivity {
    String username;
    String password;
    Button btnLogin;
    EditText name , pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);


        btnLogin = (Button) findViewById(R.id.btnlogin);
        name = (EditText) findViewById(R.id.edtname);
        pass = (EditText) findViewById(R.id.edtpass);
        initbutton();
    }


    private void initbutton(){

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                username= name.getText().toString();
                password= pass.getText().toString();


                name.clearFocus();
                pass.clearFocus();
                View currentFocus = getCurrentFocus();
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                if(name.getText().toString().equals("admin") &&
                        pass.getText().toString().equals("123")) {
                    new SweetAlertDialog(Login_2.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Berhasil Login")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    Intent intent = new Intent(getApplicationContext(), Home_pertama.class);
                                    startActivity(intent);
                                    finish();
                                    sDialog.cancel();
                                }
                            })
                            .show();


                }else{
                    new SweetAlertDialog(Login_2.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();

                }


            }
        });
    }

}
