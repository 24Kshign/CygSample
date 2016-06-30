package com.example.jack.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/9/23 18:15.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_province_two_menu)
public class ProvinceTwoMenuActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.aptm_spi_province)
    private Spinner spiProvince;

    @ViewInject(R.id.aptm_spi_city)
    private Spinner spiCity;

    @ViewInject(R.id.aptm_btn_commit)
    private Button btnCommit;

    private String strPro;
    private String strCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initeListener();
    }

    private void initeListener() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.province,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiProvince.setAdapter(adapter);
        spiProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                strPro= (String) spinner.getItemAtPosition(position);
                ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(
                        ProvinceTwoMenuActivity.this, R.array.defaulty,
                        android.R.layout.simple_spinner_item);
                if (strPro.equals("江西")) {
                    cityAdapter = ArrayAdapter.createFromResource(ProvinceTwoMenuActivity.this,
                            R.array.city_jx, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                } else if (strPro.equals("浙江")) {
                    cityAdapter = ArrayAdapter.createFromResource(ProvinceTwoMenuActivity.this,
                            R.array.city_zj, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                } else if (strPro.equals("广东")) {
                    cityAdapter = ArrayAdapter.createFromResource(ProvinceTwoMenuActivity.this,
                            R.array.city_gd, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                } else if (strPro.equals("湖南")) {
                    cityAdapter = ArrayAdapter.createFromResource(ProvinceTwoMenuActivity.this,
                            R.array.city_hn, android.R.layout.simple_spinner_item);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                }
                spiCity.setAdapter(cityAdapter);

                spiCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent1, View view1, int position1, long id1) {
                        if (position1 != 0) {
                            strCity = (String) parent1.getItemAtPosition(position1);
                        } else {
                            strCity = null;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent1) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aptm_btn_commit:
                Toast.makeText(this, "您选择了" + strPro + "省" + strCity + "市",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
