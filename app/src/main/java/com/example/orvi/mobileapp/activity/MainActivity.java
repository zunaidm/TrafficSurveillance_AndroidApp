package com.example.orvi.mobileapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.config.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CardView parking;
    CardView transactions;
    CardView fine;
    CardView help;
    CardView trace;


    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private SharedPreferences dataSave;
    private String id;
    private String data;
    private static String URL = Config.getUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSave = getApplicationContext().getSharedPreferences("LoginPref",MODE_PRIVATE);
        id = dataSave.getString("id","1");

        requestQueue = Volley.newRequestQueue(this);


        parking = (CardView) findViewById(R.id.parkingcardId);
        transactions = (CardView) findViewById(R.id.trancastionCardId);
        fine = (CardView) findViewById(R.id.tollCardId);
        help = (CardView) findViewById(R.id.helpCardId);
        trace = (CardView) findViewById(R.id.traceCardId);

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            data = jsonObject.toString();
                                            Intent intent = new Intent(MainActivity.this,ParkingActivity.class);
                                            intent.putExtra("park",data);
                                            startActivity(intent);
                                            //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getBaseContext(),"Connection or server Error",Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("park","");
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        data = jsonObject.toString();
                                        Intent intent = new Intent(MainActivity.this,TransactionActivity.class);
                                        intent.putExtra("tran",data);
                                        startActivity(intent);
                                        //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getBaseContext(),"Connection or server Error",Toast.LENGTH_LONG).show();
                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> hashMap = new HashMap<String, String>();
                                    hashMap.put("trans","");
                                    hashMap.put("id",id);
                                    return hashMap;
                                }
                            };
                            requestQueue.add(stringRequest);
                            progressDialog.dismiss();
                        }
                    }, 3000);
            }
        });

        fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            data = jsonObject.toString();
                                            Intent intent = new Intent(MainActivity.this,TollActivity.class);
                                            intent.putExtra("toll",data);
                                            startActivity(intent);
                                            //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                            Log.i("mainToFine",data);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getBaseContext(),"Connection or server Error",Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("tollfine","");
                                        hashMap.put("id",id);
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            data = jsonObject.toString();

                                            Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                            Log.i("mainToHelp",data);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getBaseContext(),"Connection or server Error",Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("block","");
                                        hashMap.put("id",id);
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        trace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            data = jsonObject.toString();
                                            Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                                            intent.putExtra("help",data);
                                            startActivity(intent);
                                            //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                                            Log.i("mainToHelp",data);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getBaseContext(),"Connection or server Error",Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("trace","");
                                        hashMap.put("id",id);
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });
    }
}
