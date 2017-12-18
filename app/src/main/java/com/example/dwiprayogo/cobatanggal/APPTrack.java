package com.example.dwiprayogo.cobatanggal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.example.dwiprayogo.cobatanggal.Adapter.Adapter_apptrack;
import com.example.dwiprayogo.cobatanggal.Adapter.Adapter_refund;
import com.example.dwiprayogo.cobatanggal.Model.model_apptracj;
import com.example.dwiprayogo.cobatanggal.Model.model_refund;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by dwi.prayogo on 11/22/2017.
 */

public class APPTrack extends AppCompatActivity  {
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Button btn_search_process;
    Spinner spinn;
    EditText edt_search;
    RecyclerView list_appid;
    boolean isUp=false;
    private List<model_apptracj> taskList = new ArrayList<model_apptracj>();
    private Adapter_apptrack ar;
    model_apptracj m;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_process);
        edt_search = (EditText) findViewById(R.id.edt_process);
        btn_search_process = (Button) findViewById(R.id.btn_search_process);
        spinn = (Spinner) findViewById(R.id.spinn);


        initRecyclerview();
        initbutton();
    }

    private void initRecyclerview() {
        list_appid = (RecyclerView) findViewById(R.id.list_appid);
        ar = new Adapter_apptrack(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_appid.setLayoutManager(mLayoutManager);
        list_appid.setItemAnimator(new DefaultItemAnimator());
        list_appid.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), list_appid, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                opendialog_dealer(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        //
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
            pDialog = new ProgressDialog(APPTrack.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }
        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                Log.d("cek", "doInBackground: "+ obj  );
                JSONArray m_jArry = obj.getJSONArray("appid");
                Log.d("cek", "doInBackground: "+m_jArry);
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    model_apptracj location = new model_apptracj();
                    location.setNama(jo_inside.getString("nama"));
                    location.setNokontrak(jo_inside.getString("aggrementno"));
                    location.setAppid(jo_inside.getString("app id"));
                    location.setAsset(jo_inside.getString("asset"));
                    location.setAppstep(jo_inside.getString("appstep"));
                    location.setNextstep(jo_inside.getString("nextstep"));
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
            //lay_search_refund.setVisibility(View.GONE);
            ar.notifyDataSetChanged();
            list_appid.setAdapter(ar);


        }

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getApplicationContext().getAssets().open("apptrack.json");
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
        View subView = inflater.inflate(R.layout.dialog, null);
        if(!isUp){
            m = taskList.get(position);
        }else{
            m=taskList.get(position);
        }
        builder= new AlertDialog.Builder(this);


        final TextView value_Name = (TextView) subView.findViewById(R.id.value_Name);
        final TextView value_Aggrement = (TextView)subView.findViewById(R.id.value_Aggrement);
        final TextView value_Asset = (TextView)subView.findViewById(R.id.value_Asset);
        final TextView value_APP = (TextView)subView.findViewById(R.id.value_APP);
        final TextView value_nextstep = (TextView)subView.findViewById(R.id.value_nextstep);


        value_Name.setText(m.getNama());
        value_Aggrement.setText(m.getNokontrak());
        value_Asset.setText(m.getAsset());
        value_APP.setText(m.getAppstep());
        value_nextstep.setText(m.getNextstep());


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
