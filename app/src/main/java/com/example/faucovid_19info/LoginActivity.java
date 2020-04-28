package com.example.faucovid_19info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    //View Components
    public TextView titleText;
    public TextView statusText;
    public EditText usernameText;
    public EditText passwordText;
    public static RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Kill the ugly App Title Bar at the top
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //Connect the elements with the layout
        statusText = findViewById(R.id.statusText);
        titleText = findViewById(R.id.titleText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        //Instantiate the request queue.
        rQueue = Volley.newRequestQueue(this);
    }

    //If response successfully gets a JSON object back from the backend go here.
    public void handleLoginResponse(JSONObject response) throws JSONException {
        String loginStatus = response.getString("login");
        if(loginStatus.equals("success")){
            //Init the new intent
            Intent intent = new Intent(this, TabbedActivity.class);

            //Send the user a toast message informing them the login was successful
            Context context = getApplicationContext();
            CharSequence text = "LOGIN SUCCESS";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //Start the new intent
            startActivity(intent);
        }
        else{
            statusText.setText("Login Failed : Invalid Username/Password");
            passwordText.setText("");
            usernameText.setText("");
        }

    }

    //If response sucessfully gets a JSON object back from signup request go here.
    public void handleSignupResponse(JSONObject response) throws JSONException {
        String signupStatus = response.getString("account_creation");
        System.out.println("DEBUG*** " + signupStatus);
        if(signupStatus.equals("success")){
            statusText.setText("Account creation successful, please login to continue.");
            passwordText.setText("");
        }
        else{
            String failureReason = response.getString("reason");
            statusText.setText("Account Creation Failed\nServer Returned Reason : "+ failureReason);
        }
    }

    public void login(View v){
        String urlstart = getString(R.string.loginURL);
        String urlp2 = "?username=" + usernameText.getText().toString();
        String urlp3 = "&pw=" + passwordText.getText().toString();
        String url = urlstart + urlp2 + urlp3;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parse the response into JSON
                        try {
                            JSONObject reader = new JSONObject(response);
                            handleLoginResponse(reader);
                        } catch (JSONException e) {
                            statusText.setText("Opps, Something went wrong....\n Please Try Again in a few minutes.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statusText.setText("Opps, Something went wrong....\n Please Try Again in a few minutes.");
            }
        });

        rQueue.add(stringRequest);



    }

    public void signup(View v){
        String urlstart = getString(R.string.signupURL);
        String urlp2 = "?username=" + usernameText.getText().toString();
        String urlp3 = "&pw=" + passwordText.getText().toString();
        String url = urlstart + urlp2 + urlp3;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parse the response into JSON
                        try {
                            JSONObject reader = new JSONObject(response);
                            handleSignupResponse(reader);
                        } catch (JSONException e) {
                            statusText.setText("Opps, Something went wrong....\n Please Try Again in a few minutes.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                statusText.setText("Opps, Something went wrong....\n Please Try Again in a few minutes.");
            }
        });

        rQueue.add(stringRequest);


    }
}
