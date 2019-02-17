package com.rodrigo.utils;

public class Response<T> {
    private T data;

    public Response() {
    }

    public Response(T t) {
        this.data = t;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }
}
