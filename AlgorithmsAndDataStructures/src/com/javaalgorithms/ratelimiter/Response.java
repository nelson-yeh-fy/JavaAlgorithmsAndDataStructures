package com.javaalgorithms.ratelimiter;

public class Response {
    ResponseHeader responseHeader;
    long value;

    Response(ResponseHeader rh, long value){
        this.responseHeader = rh;
        this.value = value;
    }
}
