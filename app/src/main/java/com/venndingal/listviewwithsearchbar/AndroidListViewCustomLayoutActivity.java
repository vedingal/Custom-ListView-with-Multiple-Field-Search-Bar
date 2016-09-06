package com.venndingal.listviewwithsearchbar;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AndroidListViewCustomLayoutActivity extends Activity {

    MyCustomAdapter dataAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Generate list View from ArrayList
        displayListView();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        Country country = new Country("AFG","Afghanistan","Asia",
                "Southern and Central Asia");
        countryList.add(country);
        country = new Country("ALB","Albania","Europe","Southern Europe");
        countryList.add(country);
        country = new Country("DZA","Algeria","Africa","Northern Africa");
        countryList.add(country);
        country = new Country("ASM","American Samoa","Oceania","Polynesia");
        countryList.add(country);
        country = new Country("AND","Andorra","Europe","Southern Europe");
        countryList.add(country);
        country = new Country("AGO","Angola","Africa","Central Africa");
        countryList.add(country);
        country = new Country("AIA","Anguilla","North America","Caribbean");
        countryList.add(country);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.country_info, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        country.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

    }


}