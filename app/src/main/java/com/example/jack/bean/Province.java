package com.example.jack.bean;

import java.util.List;

/**
 * ���ߣ�Created by JackCheng on 2015/9/27 13:07.
 * ���䣺17764576259@163.com
 */
public class Province {

    private String name;    //ʡ�ݵ�����

    private int code;     //��ʡ�ݵ�code��

    private List<City> cities;     //��ʡ�ݵĳ���

    /**
     * �����ṩһ��������������Ե��ô˷���
     * @param name
     * @param code
     */
    public Province(String name, int code) {
        super();
        this.name=name;
        this.code=code;
    }

    public Province(String name, int code, List<City> cities) {
        super();
        this.name = name;
        this.code = code;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public String toString() {
        return name;
    }
}
