package com.example.dwiprayogo.cobatanggal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 9/5/2017.
 */

public class Process extends AppCompatActivity {
    LinearLayout lay_info_process,lay_ip,lay_stat,lay_step,lay_search_process;
    EditText edt_process;
    Button btn_search_process;
    TextView value_ip,value_status,value_nextstep;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process);

        edt_process = (EditText) findViewById(R.id.edt_process);
        btn_search_process = (Button) findViewById(R.id.btn_search_process);


        value_ip = (TextView) findViewById(R.id.value_ip);
        value_status = (TextView) findViewById(R.id.value_status);
        value_nextstep = (TextView) findViewById(R.id.value_nextstep);

        lay_search_process = (LinearLayout) findViewById(R.id.lay_search_process);
        lay_info_process = (LinearLayout) findViewById(R.id.lay_info_process);
        lay_ip = (LinearLayout) findViewById(R.id.lay_ip);
        lay_stat = (LinearLayout) findViewById(R.id.lay_stat);
        lay_step = (LinearLayout) findViewById(R.id.lay_step);

    initbutton();
    }

    private void initbutton(){
        btn_search_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_search_process.setVisibility(View.GONE);
                lay_info_process.setVisibility(View.VISIBLE);
                value_ip.setText("12345678910");
                value_status.setText("Aktif");
                value_nextstep.setText("Must be Approval By Credit Head");
            }
        });
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure go to back?")
                .setCancelText("No")
                .setConfirmText("Yes")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(getApplicationContext(), Home_pertama.class);
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
    }
}
