package com.example.faucovid_19info.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.faucovid_19info.LoginActivity;
import com.example.faucovid_19info.R;
import com.example.faucovid_19info.data.StateCovidData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Control logic for the state_fragment.xml functionality
 */
public class State extends Fragment {
    private static StateCovidData data;
    private TextView deaths;
    private TextView newDeaths;
    private TextView cases;
    private TextView newCases;
    private TextView state;
    private TextView tests;
    private TextView testPerMillion;





    public State() {
        // Required empty public constructor
    }

    public static State newInstance() {
        State fragment = new State();
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
        return inflater.inflate(R.layout.fragment_state, container, false);
    }

    /**
     * populates the view elements with data from the CovidStateData static object
     */
    public void buildView(){
        state.setText("COVID-19 Info for " + data.getState());
        deaths.setText("Deaths : " + Integer.toString((int)data.getDeaths()));
        newDeaths.setText("Today's Deaths : " + Integer.toString((int)data.getTodayDeaths()));
        cases.setText("Cases : " + Integer.toString((int)data.getCases()));
        newCases.setText("Today's Cases : " + Integer.toString((int)data.getTodayCases()));
        tests.setText("Tests : " + Integer.toString((int)data.getTests()));
        testPerMillion.setText("Tests per million : " + Integer.toString((int)data.getTPM()));

    }

    /**
     * Main entry point for fragment logic
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Init class variables
        deaths = view.findViewById(R.id.deathsText);
        newDeaths = view.findViewById(R.id.newDeathsText);
        cases = view.findViewById(R.id.casesText);
        newCases = view.findViewById(R.id.newCasesText);
        state = view.findViewById(R.id.stateText);
        tests = view.findViewById(R.id.testsText);
        testPerMillion = view.findViewById(R.id.testspmText);

        String url = getString(R.string.statedata);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject reader = new JSONObject(response);
                            String stateName = reader.getString("state");
                            double tottests = reader.getDouble("tests");
                            double testsPM = reader.getDouble("testsPerOneMillion");
                            double totcases = reader.getDouble("cases");
                            double newcas = reader.getDouble("todayCases");
                            double ds = reader.getDouble("deaths");
                            double nds = reader.getDouble("todayDeaths");

                            data = new StateCovidData(stateName,totcases,newcas,ds,nds,tottests,testsPM);
                            buildView();


                        } catch (JSONException e) {
                            state.setText("Something went parsing the response.");


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                state.setText("Something went wrong with your request.\n Please try again later.");
            }
        });
        if(data == null) {
            LoginActivity.rQueue.add(stringRequest); //Data is only updated daily, so can cache it for the entire session
        }
        else{
            buildView();
        }
    }

}
