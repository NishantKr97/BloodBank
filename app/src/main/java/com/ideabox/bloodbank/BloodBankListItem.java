package com.ideabox.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BloodBankListItem extends Fragment {

    BloodBankDonerData bloodBankDonerData = new BloodBankDonerData();

    public BloodBankListItem() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bloodBankDonerData.id = getArguments().getInt("Id");
            bloodBankDonerData.full_name = getArguments().getString("Name");
            bloodBankDonerData.city = getArguments().getString("City");
            bloodBankDonerData.area = getArguments().getString("Area");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_item_blood_bank, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name  = (TextView) view.findViewById(R.id.name);
        TextView location  = (TextView) view.findViewById(R.id.location);

        name.setText(bloodBankDonerData.full_name);
        location.setText(bloodBankDonerData.city+", "+ bloodBankDonerData.area);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("info", "onClick: "+ bloodBankDonerData.id);
                Intent i = new Intent(getActivity(),BloodBankDetailActivity.class);
                i.putExtra("Id", bloodBankDonerData.id);
                startActivity(i);
            }
        });
    }
}
