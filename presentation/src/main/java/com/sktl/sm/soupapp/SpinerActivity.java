package com.sktl.sm.soupapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Created by USER-PC on 26.07.2017.
 */

public class SpinerActivity extends Fragment {

    SpinerActivity spinerActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setSContentView(R.layout.activity_spinner);

        spinerActivity = new SpinerActivity();
        Fragment fragment = (Fragment) getFragmentManager()
                .findFragmentById(R.id.spinner);
        fragment.getLayoutInflater(this.getArguments());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}