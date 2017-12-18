package com.example.dwiprayogo.cobatanggal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Adapter.Adapter_PO;

import com.example.dwiprayogo.cobatanggal.Model.model_apptracj;
import com.example.dwiprayogo.cobatanggal.Model.model_po;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 11/14/2017.
 */

public class Payment_PO extends AppCompatActivity {
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Button btn_search_process;
    Spinner spinn_po;
    EditText edt_process_po;
    RecyclerView list_po;
    boolean isUp=false;
    private List<model_po> taskList = new ArrayList<model_po>();
    private Adapter_PO ar;
    model_po m;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_po);
        edt_process_po = (EditText) findViewById(R.id.edt_process_po);
        btn_search_process = (Button) findViewById(R.id.btn_search_process);
        spinn_po = (Spinner) findViewById(R.id.spinn_po);


        initRecyclerview();
        initbutton();

    }

    private void initRecyclerview() {
        list_po = (RecyclerView) findViewById(R.id.list_po);
        ar = new Adapter_PO(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_po.setLayoutManager(mLayoutManager);
        list_po.setItemAnimator(new DefaultItemAnimator());
        list_po.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), list_po, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                opendialog_dealer(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
    private void initbutton(){
        btn_search_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetContacts().execute();
            }
        });
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Payment_PO.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                Log.d("cek", "doInBackground: "+ obj  );
                JSONArray m_jArry = obj.getJSONArray("po");
                Log.d("cek", "doInBackground: "+m_jArry);
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    model_po location = new model_po();
                    location.setNama(jo_inside.getString("nama"));
                    location.setNokontrak(jo_inside.getString("aggrementno"));
                    location.setAppid(jo_inside.getString("appid"));
                    location.setAsset(jo_inside.getString("asset"));
                    location.setStatus(jo_inside.getString("status"));
                    location.setPaiddate(jo_inside.getString("paiddate"));
                    taskList.add(location);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            ar.notifyDataSetChanged();
            list_po.setAdapter(ar);

        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("po.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    public void opendialog_dealer(int position){

        LayoutInflater inflater =getLayoutInflater();
        View subView = inflater.inflate(R.layout.dialog_2, null);
        if(!isUp){
            m = taskList.get(position);
        }else{
            m=taskList.get(position);
        }
        builder= new AlertDialog.Builder(this);


        final TextView value_Name = (TextView) subView.findViewById(R.id.value_Name);
        final TextView value_Aggrement = (TextView)subView.findViewById(R.id.value_Aggrement);
        final TextView value_Asset = (TextView)subView.findViewById(R.id.value_Asset);
        final TextView value_status = (TextView)subView.findViewById(R.id.value_status);
        final TextView value_paiddate = (TextView)subView.findViewById(R.id.value_paiddate);


        value_Name.setText(m.getNama());
        value_Aggrement.setText(m.getNokontrak());
        value_Asset.setText(m.getAsset());
        value_status.setText(m.getStatus());
        value_paiddate.setText(m.getPaiddate());

        String status = m.getStatus();

        if (status.contains("Unpaid")){
            value_paiddate.setText("---");
        }


        builder.setView(subView);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }



    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
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
                        Intent intent = new Intent(getApplicationContext(), Payment.class);
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
