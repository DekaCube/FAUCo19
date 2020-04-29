package com.example.faucovid_19info.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.faucovid_19info.R;

/**
 * Control logic for historical_fragment.xml
 */
public class Historical extends Fragment {
    private WebView deaths;
    private WebView cases;

    public Historical() {
        // Required empty public constructor
    }


    public static Historical newInstance() {
        Historical fragment = new Historical();
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
        return inflater.inflate(R.layout.fragment_historical, container, false);

    }

    /**
     * main entry point for fragment
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        deaths = view.findViewById(R.id.deathsWebView);
        cases = view.findViewById(R.id.casesWebView);
        WebSettings deathsettings = deaths.getSettings();
        WebSettings casessettings = cases.getSettings();
        casessettings.setJavaScriptEnabled(true);
        deathsettings.setJavaScriptEnabled(true);
        deaths.loadUrl(getString(R.string.deathgraph));
        cases.loadUrl(getString(R.string.casegraph));



    }
}
