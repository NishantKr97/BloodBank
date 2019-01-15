package com.ideabox.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BloodBankMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_blood_bank);
    }

    public void request_blood(View view)
    {
        Intent intent=new Intent(this,BloodBankRequestActivity.class);
        startActivity(intent);
    }

    public void register_doner(View view)
    {
        Intent intent=new Intent(this,BloodBankDonateActivity.class);
        startActivity(intent);
    }


}
