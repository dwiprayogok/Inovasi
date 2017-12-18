package com.example.dwiprayogo.cobatanggal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 10/25/2017.
 */

public class Home_pertama extends AppCompatActivity {
    TextView txt_login, txt_nama;
    Button btn_process, btn_payment, btn_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_utama);

        btn_process = (Button) findViewById(R.id.btn_process);
        btn_payment = (Button) findViewById(R.id.btn_payment);
        btn_report = (Button) findViewById(R.id.btn_report);

        txt_nama = (TextView) findViewById(R.id.txt_nama);
        button();
    }

    private void button() {

        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), APPTrack.class);
                startActivity(intent);
                finish();
            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Payment.class);
                startActivity(intent);
                finish();
            }
        });

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public void onBackPressed() {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure go to exit?")
                .setCancelText("No")
                .setConfirmText("Yes")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(getApplicationContext(), Login_2.class);
                        startActivity(intent);
                        finish();
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//        builder.setIcon(android.R.drawable.ic_dialog_info);
//        builder.setTitle("Confirmation");
//        builder.setMessage("Do you really want to exit ?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, final int id) {
//                        Intent intent = new Intent(getApplicationContext(), Login_2.class);
//                        startActivity(intent);
//                        finish();
//
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, final int id) {
//                        dialog.dismiss();
//                    }
//                });
//        final AlertDialog alert = builder.create();
//        alert.show();
    }
}