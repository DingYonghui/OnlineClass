package com.example.ding.study.test;

/**
 * Created by ding on 2015/10/13.
 */
public class ResponseObject<T> {

    private String state ;
    private T data ;

    public ResponseObject() {
        super();
    }

    public ResponseObject(String state, T data) {
        this.state = state;
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
