package com.javaalgorithms.ratelimiter;

public class Response {
    ResponseHeader responseHeader;
    long value;

    Response(ResponseHeader responseHeader, long value){
        this.responseHeader = responseHeader;
        this.value = value;
    }
}
