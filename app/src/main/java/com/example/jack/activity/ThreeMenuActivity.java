package com.example.jack.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jack.bean.City;
import com.example.jack.bean.Province;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.widget.ProvinceParse;

/**
 * ���ߣ�Created by JackCheng on 2015/9/28 18:46.
 * ���䣺17764576259@163.com
 */

@ContentView(R.layout.activity_three_menu)
public class ThreeMenuActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.atm_spi_province)
    private Spinner spiProvince;

    @ViewInject(R.id.atm_spi_city)
    private Spinner spiCity;

    @ViewInject(R.id.atm_btn_commit)
    private Button btnCommit;

    private ProvinceParse parse;

    private City currentCity;

    private Province currentProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        inite();
    }

    private void inite() {
        btnCommit.setOnClickListener(this);

        parse = ProvinceParse.build(this, R.raw.province, R.raw.cities);
        ArrayAdapter<Province> provinceAdapter = new ArrayAdapter<Province>(this,
                R.layout.item_simple_spinner, android.R.id.text1, parse.getProvinces());
        spiProvince.setAdapter(provinceAdapter);
        spiProvince.setOnItemSelectedListener(new ProvinceAdapter());
        spiCity.setOnItemSelectedListener(new CityAdapter());
    }

    //��ʡ�ݸı�ʱ������Ҳ���Ÿı�
    public void onProvinChange(int position) {
        currentProvince = parse.getProvinces().get(position);   //��ȡ��ǰʡ��
        ArrayAdapter<City> cityAdapter = new ArrayAdapter<City>(this, R.layout.item_simple_spinner,
                android.R.id.text1, currentProvince.getCities());
        spiCity.setAdapter(cityAdapter);
    }

    class ProvinceAdapter implements AdapterView.OnItemSelectedListener {
        //ʡ�ݵ�Spinner�ĵ���¼�
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            onProvinChange(position);    //����onProvinChange����
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    final class CityAdapter extends ProvinceAdapter {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            currentCity = currentProvince.getCities().get(position);   //��ȡ��ǰ����
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "" + currentProvince + currentCity, Toast.LENGTH_SHORT).show();
    }
}
