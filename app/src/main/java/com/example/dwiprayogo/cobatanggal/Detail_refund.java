package com.example.dwiprayogo.cobatanggal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dwiprayogo.cobatanggal.Adapter.Adapter_refund;
import com.example.dwiprayogo.cobatanggal.Adapter.Adapter_refund2;
import com.example.dwiprayogo.cobatanggal.Model.model_po;
import com.example.dwiprayogo.cobatanggal.Model.model_refund;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dwi.prayogo on 11/23/2017.
 */

public class Detail_refund extends AppCompatActivity {
    RecyclerView list_refund;
    Adapter_refund ar;

    private List<model_refund> taskList = new ArrayList<model_refund>();
    TextView value_cust_name,value_aggNo,value_suppliername;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_refund);


        value_suppliername = (TextView) findViewById(R.id.value_suppliername);
        value_aggNo = (TextView) findViewById(R.id.value_aggNo);
        value_cust_name = (TextView) findViewById(R.id.value_cust_name);

        Intent intent = getIntent();
        String value_suppliername2 = intent.getStringExtra("supplier");
        String value_aggNo2 = intent.getStringExtra("nokontrak");
        String value_cust_name2 = intent.getStringExtra("name");
        value_cust_name.setText(value_cust_name2);
                value_aggNo.setText(value_aggNo2);
        value_suppliername.setText(value_suppliername2);
        initRecyclerview();
        new GetContacts().execute();

    }

    private void initRecyclerview() {
        list_refund = (RecyclerView) findViewById(R.id.list_refund);

        ar = new Adapter_refund(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_refund.setLayoutManager(mLayoutManager);
        list_refund.setItemAnimator(new DefaultItemAnimator());
        list_refund.addOnItemTouchListener(new Payment_Refund.RecyclerTouchListener(getApplicationContext(), list_refund, new Payment_Refund.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("lati2.json");
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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Detail_refund.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                Log.d("cek", "doInBackground: "+ obj  );
                JSONArray m_jArry = obj.getJSONArray("refund");
                Log.d("cek", "doInBackground: "+m_jArry);
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    model_refund location = new model_refund();
                    location.setNama(jo_inside.getString("nama"));
                    location.setPosisi(jo_inside.getString("jabatan"));
                    location.setTanggal(jo_inside.getString("tanggal"));
                    location.setStatus(jo_inside.getString("status"));
                    taskList.add(location);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


//
//            try {
//                JSONObject obj = new JSONObject(loadJSONFromAsset());
//                JSONArray m_jArry = obj.getJSONArray("refund");
//
//                for (int i = 0; i < obj.length(); i++) {
//                    JSONObject jo = m_jArry.getJSONObject(i);
//                    model_refund location = new model_refund();
//                    location.setNama(jo.getString("nama"));
//                    location.setPosisi(jo.getString("posisi"));
//                    location.setStatus(jo.getString("status"));
//                    location.setTanggal(jo.getString("tanggal"));
//                    locList.add(location);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            ar.notifyDataSetChanged();
            list_refund.setAdapter(ar);

        }

    }
}
