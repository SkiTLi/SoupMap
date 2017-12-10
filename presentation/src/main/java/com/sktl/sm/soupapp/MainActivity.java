package com.sktl.sm.soupapp;

import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


//public class MainActivity extends AppCompatActivity {
public class MainActivity extends FragmentActivity {

    MapsActivity mapsActivity;

    public static final String USERNAME_IN_MAIN = "////RESULT////";
    public static final String PASSWORD_IN_MAIN = "////GROGER////";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        //это уже я добавил
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
//        toolbar.setMenu();
        toolbar.setLogo(R.drawable.ic_soup_100dp);
        mapsActivity = new MapsActivity();

//171129(
        //прием данных из VeryFirstActivity
        String usernameFromVeryFirst = getIntent().getStringExtra(USERNAME_IN_MAIN);
        String passwordFromVeryFirst = getIntent().getStringExtra(PASSWORD_IN_MAIN);
        Log.d("eee", "передался из VeryFirstActivity в MainActivity intent = " + usernameFromVeryFirst);
        //передача данных из MainActivity в MapsActivity
        Bundle bundle1 = new Bundle();
        bundle1.putString(MapsActivity.KEY_USERNAME, usernameFromVeryFirst);
        bundle1.putString(MapsActivity.KEY_PASSWORD, passwordFromVeryFirst);
        mapsActivity.setArguments(bundle1);

        Log.d("eee", " в MainActivity bundle1(KEY_USERNAME) = " + bundle1.get(MapsActivity.KEY_USERNAME));
        Log.d("eee", " в MainActivity bundle1(KEY_PASSWORD) = " + bundle1.get(MapsActivity.KEY_PASSWORD));

//171129)

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(getSupportFragmentManager(),
                        new SpinerActivity(), true);
            }
        });


        showFragment(getSupportFragmentManager(),
                mapsActivity, true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void showFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();//выполнить
    }

}
