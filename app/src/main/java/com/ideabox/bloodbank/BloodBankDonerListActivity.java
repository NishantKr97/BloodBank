package com.ideabox.bloodbank;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class BloodBankDonerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_list_blood_bank);

        String bloodgrp = getIntent().getStringExtra("bloodgrp");
        String city = getIntent().getStringExtra("city");

        ArrayList<BloodBankDonerData> data = BloodBankdbHelper.getDonerList(this, bloodgrp, city);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (BloodBankDonerData d:data)
        {
            BloodBankListItem listitem = new BloodBankListItem();
            Bundle b = new Bundle();
            b.putInt("Id",d.id);
            b.putString("Name",d.full_name);
            b.putString("City",d.city);
            b.putString("Area",d.area);
            listitem.setArguments(b);

            fragmentTransaction.add(R.id.ListHolder,listitem);
        }

        fragmentTransaction.commit();
    }
}
