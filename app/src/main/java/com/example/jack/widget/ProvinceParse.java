package com.example.jack.widget;

import android.content.Context;

import com.example.jack.bean.City;
import com.example.jack.bean.Province;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/9/27 13:23.
 * ÓÊÏä£º17764576259@163.com
 */
public class ProvinceParse {
    private static final String SPLIT_REGEX=",";

    private Context mContext;

    private int provinceId;

    private int cityId;

    private List<Province> provinces;

    private ProvinceParse() {

    }
    public static ProvinceParse build (Context mContext, int provinceId,int cityId) {
        final ProvinceParse parse=new ProvinceParse();
        parse.mContext=mContext;
        parse.cityId=cityId;
        parse.provinceId=provinceId;
        parse.parase();
        return parse;
    }

    //parse from file
    private void parase() {
        try {
            parseProvince();
            final List<City> cities = parseCity();
            List<City> tempCities = null;
            for (Province province : provinces) {
                tempCities = new ArrayList<City>();
                for (City city : cities) {
                    if (city.getProvinceCode() == province.getCode()) {
                        tempCities.add(city);
                    }
                }
                province.setCities(tempCities);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<City> parseCity() throws IOException {
        final List<String> strings = readLine(mContext, cityId);
        final List<City> cities = new ArrayList<City>();
        City city = null;
        String[] splitstr = null;
        for (String str : strings) {
            splitstr = splitLine(str, SPLIT_REGEX);
            if (splitstr.length == 4) {
                city = new City(splitstr[1], Integer.parseInt(splitstr[0]), Integer.parseInt(splitstr[2]),
                        Integer.parseInt(splitstr[3]));
                cities.add(city);
            }
        }
        return cities;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    private static String[] splitLine(String str, String regex) {
        return str.split(regex);
    }

    private void parseProvince() throws IOException{
        final List<String> strings=readLine(mContext,provinceId);
        provinces=new ArrayList<Province>();
        Province province=null;
        String[] splitstr=null;
        for (String str:strings) {
            splitstr=splitLine(str,SPLIT_REGEX);
            if (splitstr.length == 2) {
                province = new Province(splitstr[0], Integer.parseInt(splitstr[1]));
                provinces.add(province);
            }
        }

    }

    private List<String> readLine(Context mContext, int id) throws IOException {
        final InputStream in = mContext.getResources().openRawResource(id);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));
        final List<String> strings = new ArrayList<String>();
        String line = null;
        while (null != (line = reader.readLine())) {
            strings.add(line);
        }
        reader.close();
        return strings;
    }
}
