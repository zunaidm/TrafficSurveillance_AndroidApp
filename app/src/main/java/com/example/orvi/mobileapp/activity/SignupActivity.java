package com.example.orvi.mobileapp.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import com.example.orvi.mobileapp.R;
import com.example.orvi.mobileapp.config.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private static String  URL = Config.getRegister();


    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    EditText _addressText;
    EditText _nidText;
    EditText _engineText;

    Button _signupButton;
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);

        _nameText = (EditText) findViewById(R.id.input_name);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _addressText = (EditText) findViewById(R.id.input_address);
        _nidText = (EditText) findViewById(R.id.input_nid);
        _engineText = (EditText) findViewById(R.id.input_engine);

        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();
        final String address = _addressText.getText().toString();
        final String nid = _nidText.getText().toString();
        final String engine = _engineText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean error = jsonObject.getBoolean("error");
                                    if(!error){
                                        onSignupSuccess();
                                    }else {
                                        onSignupFailed();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getBaseContext(),e+"",Toast.LENGTH_LONG).show();
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
                                hashMap.put("name",name);
                                hashMap.put("email",email);
                                hashMap.put("pass",password);
                                hashMap.put("addr",address);
                                hashMap.put("nid",nid);
                                hashMap.put("engine",engine);

                                return hashMap;
                            }
                        };

                        requestQueue.add(stringRequest);
                        progressDialog.dismiss();
                        onSignupSuccess();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        Toast.makeText(getBaseContext(),"Sign Up Completed",Toast.LENGTH_SHORT).show();
        _signupButton.setEnabled(true);
        //setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String address = _addressText.getText().toString();
        String nid = _nidText.getText().toString();
        String engine = _engineText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (address.isEmpty() || address.length() < 10) {
            _addressText.setError("at least 10 characters");
            valid = false;
        } else {
            _addressText.setError(null);
        }

        if (nid.isEmpty() || nid.length() < 13) {
            _nidText.setError("at least 13 characters");
            valid = false;
        } else {
            _nidText.setError(null);
        }

        if (engine.isEmpty() || engine.length() < 3) {
            _engineText.setError("at least 3 characters");
            valid = false;
        } else {
            _engineText.setError(null);
        }

        return valid;
    }
}

