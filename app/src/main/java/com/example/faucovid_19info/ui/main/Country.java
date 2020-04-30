package com.example.faucovid_19info.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.faucovid_19info.data.CountryCovidData;
import com.example.faucovid_19info.LoginActivity;
import com.example.faucovid_19info.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Control logic for country_fragment.xml
 */

public class Country extends Fragment {

    private TextView country;

    private TextView deaths;
    private TextView tdeaths;
    private TextView dpm;
    private TextView cases;
    private TextView tcases;
    private TextView cpm;
    private TextView tests;
    private TextView tpm;
    private TextView recovered;
    private TextView critical;
    private TextView active;
    private WebView flagview;


    /**
     * Data container class used for caching the request data
     */
    private static CountryCovidData data;

    //URL for the API request
    private String url;


    public Country() {
        // Required empty public constructor
    }


    public static Country newInstance() {
        Country fragment = new Country();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false);
    }

    /**
     * This function is what runs after the view has been created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //This needs to be done here, trying to do this in class variable causes crash
        //because of activity context DO NOT MOVE
        url = getString(R.string.countrydata);


        //Initialize the view elements to the class variables;
        country = view.findViewById(R.id.cname);
        deaths = view.findViewById(R.id.cdeaths);
        tdeaths = view.findViewById(R.id.cnewDeaths);
        cases = view.findViewById(R.id.ctotalCases);
        tcases = view.findViewById(R.id.cnewCases);
        flagview = view.findViewById(R.id.cflag);
        cpm = view.findViewById(R.id.ccpm);
        dpm = view.findViewById(R.id.cdpm);
        tpm = view.findViewById(R.id.ctpm);
        tests = view.findViewById(R.id.ctotalTests);
        recovered = view.findViewById(R.id.crecovered);
        critical = view.findViewById(R.id.ccritical);
        active = view.findViewById(R.id.cactive);

        if(data == null){
            make_request();
        }
        else{
            buildView();
        }



    }

    /**
     * Once the data element is populated with data, this function populates the view elements
     * with that data
     */
    private void buildView(){
        flagview.loadUrl(data.getFlagURL());

        //This typecasting is ugly TODO : Change objects have string methods
        deaths.setText("Total Deaths : " + Integer.toString((int)data.getTotalDeaths()));
        dpm.setText("Deaths per million : " + Integer.toString((int)data.getDeathsPerMillion()));
        tdeaths.setText("Today's Deaths : " + Integer.toString((int)data.getNewDeaths()));
        cases.setText("Total Cases : " + Integer.toString((int)data.getTotalCases()));
        cpm.setText("Cases per million : " + Integer.toString((int)data.getCasesPerMillion()));
        tcases.setText("Today's Cases : " + Integer.toString((int)data.getNewCases()));
        country.setText("COVID 19 info for " + data.getCountry());
        tests.setText("Total Tests : " + Integer.toString((int)data.getTests()));
        tpm.setText("Tests per million : " + Integer.toString((int)data.getTestsPerMillion()));
        active.setText("Active Cases : " + Integer.toString((int)data.getActive()));
        critical.setText("Critical Cases : " + Integer.toString((int)data.getCritical()));
        recovered.setText("Recovered Cases : " + Integer.toString((int)data.getRecovered()));




    }

    /**
     * This function makes the request for data, and calls buildView() when the request is completed/successful
     */
    private void make_request(){
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject r = new JSONObject(response);
                            data = new CountryCovidData();
                            data.setCountry(r.getString("country"));
                            data.setActive(r.getDouble("active"));
                            data.setCritical(r.getDouble("critical"));
                            data.setRecovered(r.getDouble("recovered"));
                            data.setFlagURL("https://corona.lmao.ninja/assets/img/flags/us.png"); //TODO : DIG THIS OUT LATER
                            data.setTotalCases(r.getDouble("cases"));
                            data.setNewCases(r.getDouble("todayCases"));
                            data.setCasesPerMillion(r.getDouble("casesPerOneMillion"));
                            data.setTotalDeaths(r.getDouble("deaths"));
                            data.setNewDeaths(r.getDouble("todayDeaths"));
                            data.setDeathsPerMillion(r.getDouble("deathsPerOneMillion"));
                            data.setTests(r.getDouble("tests"));
                            data.setTestsPerMillion(r.getDouble("testsPerOneMillion"));
                            buildView();

                        } catch (JSONException e) {
                            country.setText("Error parsing data...");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                country.setText("Something went wrong...\n Please try again later.");
            }
        });

        LoginActivity.rQueue.add(stringRequest);
    }
}
