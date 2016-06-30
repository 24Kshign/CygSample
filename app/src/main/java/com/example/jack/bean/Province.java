package com.example.jack.bean;

import java.util.List;

/**
 * 作者：Created by JackCheng on 2015/9/27 13:07.
 * 邮箱：17764576259@163.com
 */
public class Province {

    private String name;    //省份的名称

    private int code;     //各省份的code、

    private List<City> cities;     //各省份的城市

    /**
     * 对外提供一个方法，让其可以调用此方法
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
