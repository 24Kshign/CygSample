package com.example.jack.bean;

/**
 * ���ߣ�Created by JackCheng on 2015/9/27 13:07.
 * ���䣺17764576259@163.com
 */
public class City {

    private String name;      //��������

    private int provinceCode;       //ʡ��code

    private int code;     //����code

    private int uniqueCode;

    public City(String name, int provinceCode, int code, int uniqueCode) {
        super();
        this.name = name;
        this.provinceCode = provinceCode;
        this.code = code;
        this.uniqueCode = uniqueCode;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(int uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
