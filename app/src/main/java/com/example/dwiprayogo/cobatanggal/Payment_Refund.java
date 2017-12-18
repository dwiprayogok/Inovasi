package com.example.dwiprayogo.cobatanggal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 11/14/2017.
 */

public class Payment_Refund extends AppCompatActivity {

    TextView value_IP_refund;
    RecyclerView list_refund;
    RecyclerView list_refund_awal;
    EditText edt_refund;
    Button btn_search_refund;
    //Adapter_refund ar;
    Adapter_refund2 ar;
    //private List<model_refund> taskList = new ArrayList<model_refund>();
    private List<model_po> taskList2 = new ArrayList<model_po>();
    LinearLayout lay_search_refund, lay_list_refund;

    TextView value_suppliername,value_aggNo,value_cust_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_refund);

        lay_search_refund = (LinearLayout) findViewById(R.id.lay_search_refund);
        lay_list_refund = (LinearLayout) findViewById(R.id.lay_list_refund);

        value_suppliername = (TextView) findViewById(R.id.value_suppliername);
        value_IP_refund = (TextView) findViewById(R.id.value_IP_refund);
        value_IP_refund = (TextView) findViewById(R.id.value_IP_refund);

        value_IP_refund = (TextView) findViewById(R.id.value_IP_refund);
        edt_refund = (EditText) findViewById(R.id.edt_refund);

        btn_search_refund = (Button) findViewById(R.id.btn_search_refund);
        btn_search_refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetContacts().execute();
            }
        });
        initRecyclerview();

    }

    private void initRecyclerview() {
        //list_refund = (RecyclerView) findViewById(R.id.list_refund);
        list_refund_awal = (RecyclerView) findViewById(R.id.list_refund_awal);
        //ar = new Adapter_refund(taskList);
        ar = new Adapter_refund2(taskList2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_refund_awal.setLayoutManager(mLayoutManager);
        list_refund_awal.setItemAnimator(new DefaultItemAnimator());
        list_refund_awal.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), list_refund_awal, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), Detail_refund.class);
                intent.putExtra("name", taskList2.get(position).getNama()); //you can name the keys whatever you like
                intent.putExtra("nokontrak", taskList2.get(position).getNokontrak()); //note that all these values have to be primitive (i.e boolean, int, double, String, etc.)
                intent.putExtra("supplier", "TUNAS TOYOTA - JATIWARINGIN");
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        //list_refund.setAdapter(ar);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            //InputStream is = getApplicationContext().getAssets().open("lati2.json");
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

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Payment_Refund.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

//            try {
//                JSONObject obj = new JSONObject(loadJSONFromAsset());
//                Log.d("cek", "doInBackground: "+ obj  );
//                JSONArray m_jArry = obj.getJSONArray("refund");
//                Log.d("cek", "doInBackground: "+m_jArry);
//                for (int i = 0; i < m_jArry.length(); i++) {
//                    JSONObject jo_inside = m_jArry.getJSONObject(i);
//                    model_refund location = new model_refund();
//                    location.setNama(jo_inside.getString("nama"));
//                    location.setPosisi(jo_inside.getString("jabatan"));
//                    location.setTanggal(jo_inside.getString("tanggal"));
//                    location.setStatus(jo_inside.getString("status"));
//                    taskList.add(location);
//
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

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
                    taskList2.add(location);

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
            //lay_search_refund.setVisibility(View.GONE);
            ar.notifyDataSetChanged();
            //list_refund.setAdapter(ar);
            list_refund_awal.setAdapter(ar);

            //lay_list_refund.setVisibility(View.VISIBLE);
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

    //    public ArrayList<model_refund> loadJSONFromAsset() {
//        ArrayList<model_refund> locList = new ArrayList<>();
//        String json = null;
//        try {
//            InputStream is = getAssets().open("lati2.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        try {
//            JSONObject obj = new JSONObject(json);
//            JSONArray m_jArry = obj.getJSONArray("list_refund");
//
//            for (int i = 0; i < obj.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                model_refund location = new model_refund();
//                location.setNama(jo_inside.getString("nama"));
//                location.setPosisi(jo_inside.getString("posisi"));
//                location.setStatus(jo_inside.getString("status"));
//                location.setTanggal(jo_inside.getString("tanggal"));
//
//
//                locList.add(location);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return locList;
//    }
}
