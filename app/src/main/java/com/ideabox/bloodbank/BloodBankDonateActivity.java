package com.ideabox.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BloodBankDonateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String TAG = "BloodBankDonateActivity";
    ArrayAdapter<CharSequence> Blood_List;
    ArrayAdapter<CharSequence> City_List;
    ArrayAdapter<CharSequence> Area_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood_bank);

        // Initialize Arrayadapter from Array resource.

        Blood_List = ArrayAdapter.createFromResource(this,R.array.bg_arrays, android.R.layout.select_dialog_item);
        City_List = ArrayAdapter.createFromResource(this,R.array.city_arrays, android.R.layout.select_dialog_item);
        Area_List = ArrayAdapter.createFromResource(this, R.array.agra_arrays, android.R.layout.select_dialog_item);

        Spinner spinner_blood = (Spinner)findViewById(R.id.spinner_bloodgrp);
        Spinner spinner_city = (Spinner)findViewById(R.id.spinner_city);
        Spinner spinner_area = (Spinner)findViewById(R.id.spinner_area);

        spinner_city.setAdapter(City_List);
        spinner_city.setOnItemSelectedListener(this);
        City_List.getItem(0).toString();

        spinner_blood.setAdapter(Blood_List);
        Blood_List.getItem(0).toString();

        spinner_area.setAdapter(Area_List);
        Area_List.getItem(0).toString();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String city = City_List.getItem(position).toString();
            updateAreaSpinner(city);
            Log.d("info", "onItemSelected: "+city);
    }

    private void updateAreaSpinner(String city) {

        int aid = 0;

        if(city.equals("Agra"))
        {
            aid = R.array.agra_arrays;
        }
        else if(city.equals("Vadodara"))
        {
            aid = R.array.Vadodara_arrays;
        }
        else if(city.equals("Bhopal"))
        {
            aid = R.array.bpl_arrays;
        }
        else if(city.equals("Chennai"))
        {
            aid = R.array.chennai_arrays;
        }
        else if(city.equals("Delhi"))
        {
            aid = R.array.delhi_arrays;
        }
        else if(city.equals("Dewas"))
        {
            aid = R.array.Dewas_arrays;
        }
        else if(city.equals("Faridabad"))
        {
            aid = R.array.faridabad_arrays;
        }
        else if(city.equals("Goa"))
        {
            aid = R.array.goa_arrays;
        }
        else if(city.equals("Hyderabad"))
        {
            aid = R.array.hyderabad_arrays;
        }
        else if(city.equals("Indore"))
        {
            aid = R.array.indore_arrays;
        }
        else if(city.equals("Khargone"))
        {
            aid = R.array.khargone_arrays;
        }
        else if(city.equals("Khandwa"))
        {
            aid = R.array.khandwa_arrays;
        }
        else if(city.equals("Ujjain"))
        {
            aid = R.array.ujjain_arrays;
        }

        Area_List = ArrayAdapter.createFromResource(this, aid , android.R.layout.select_dialog_item);
        Spinner spinner_area = (Spinner)findViewById(R.id.spinner_area);
        spinner_area.setAdapter(Area_List);

    }

    public void register(View view)
    {
        String msg = "";

        BloodBankDonerData bloodBankDonerData = new BloodBankDonerData();

        bloodBankDonerData.full_name = ((EditText) findViewById(R.id.editText_name)).getText().toString();
        bloodBankDonerData.phone = ((EditText) findViewById(R.id.editText_phone)).getText().toString();
        bloodBankDonerData.email = ((EditText) findViewById(R.id.editText_email)).getText().toString();
        bloodBankDonerData.addr = ((EditText) findViewById(R.id.editText_address)).getText().toString();

        bloodBankDonerData.bloodgrp = ((Spinner)findViewById(R.id.spinner_bloodgrp)).getSelectedItem().toString();
        bloodBankDonerData.city = ((Spinner)findViewById(R.id.spinner_city)).getSelectedItem().toString();
        bloodBankDonerData.area = ((Spinner)findViewById(R.id.spinner_area)).getSelectedItem().toString();

        // Validation of input data

        if(bloodBankDonerData.full_name.isEmpty() || bloodBankDonerData.phone.isEmpty() || bloodBankDonerData.email.isEmpty() ||  bloodBankDonerData.addr.isEmpty())
        {
            msg = "One or Multiple fields are Empty.\nCheck all Fields and try Again.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher( bloodBankDonerData.email).matches())
        {
            msg = "Invalid E-mail address.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else if( bloodBankDonerData.phone.length()<10)
        {
            msg = "Invalid Phone number.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else
        {
            // Save data in db

            BloodBankdbHelper.insert(bloodBankDonerData, this);
            msg = "Successfully, registered as Doner.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
