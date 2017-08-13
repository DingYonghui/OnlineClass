package com.example.ding.study.entity;

/**
 * 院系
 * Created by ding on 2015/10/21.
 */
public class Department {

    private String id ;
    private String dName ;

    public Department() {
    }
    public Department(String id) {
        this.id = id;
    }
    public Department(String id, String dName) {
        this.id = id;
        this.dName = dName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}
