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
import android.widget.TableLayout;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 9/5/2017.
 */

public class Payment extends AppCompatActivity {
    LinearLayout lay_button_payment,lay_search_refund,lay_table_refund,lay_info_payment,lay_search_payment;
    EditText edt_PO,edt_refund;
    Button btn_PO,btn_refund,btn_search_PO,btn_search_refund;
    TextView value_IP_payment,value_amount,value_status_payment,value_paiddate,value_IP_refund;
    TableLayout table_REFUND;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);


        btn_PO = (Button) findViewById(R.id.btn_PO);
        btn_refund = (Button) findViewById(R.id.btn_refund);

        btn_search_PO = (Button) findViewById(R.id.btn_search_PO);
        btn_search_refund = (Button) findViewById(R.id.btn_search_refund);

        edt_PO = (EditText) findViewById(R.id.edt_PO);
        edt_refund = (EditText) findViewById(R.id.edt_refund);


        value_IP_payment = (TextView) findViewById(R.id.value_IP_payment);
        value_amount = (TextView) findViewById(R.id.value_amount);
        value_status_payment = (TextView) findViewById(R.id.value_status_payment);
        value_paiddate = (TextView) findViewById(R.id.value_paiddate);

        //layout_utama_PAYMENT
        lay_button_payment = (LinearLayout) findViewById(R.id.lay_button_payment);
        //layout_REFUND
        lay_search_refund = (LinearLayout) findViewById(R.id.lay_search_refund);
        lay_table_refund = (LinearLayout) findViewById(R.id.lay_table_refund);

        //layout_PO
        lay_info_payment = (LinearLayout) findViewById(R.id.lay_info_payment);
        lay_search_payment = (LinearLayout) findViewById(R.id.lay_search_payment);
        initbutton();
    }

    private void initbutton(){
        btn_PO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Payment_PO.class);
                startActivity(intent);
                finish();
//                lay_button_payment.setVisibility(View.GONE);
//                lay_search_payment.setVisibility(View.VISIBLE);
//                searchPO();
            }
        });

        btn_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Payment_Refund.class);
                startActivity(intent);
                finish();
//                lay_button_payment.setVisibility(View.GONE);
//                lay_search_refund.setVisibility(View.VISIBLE);
//                searchrefund();
            }
        });
    }


    private void searchPO(){
        btn_search_PO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_search_payment.setVisibility(View.GONE);
                lay_info_payment.setVisibility(View.VISIBLE);
                value_IP_payment.setText("12345678");
                value_amount.setText("Rp. 5.250.000");
                value_status_payment.setText("Menunggu Approval");
                value_paiddate.setText("20 - 12 - 2017");
            }
        });
    }

    private void searchrefund(){
        btn_search_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay_search_refund.setVisibility(View.GONE);
                lay_table_refund.setVisibility(View.VISIBLE);
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
//
    }
}
