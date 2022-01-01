package com.javaalgorithms.ratelimiter;

public enum RateLimitingUnit {
    SECOND (1),
    MINUTE (60),
    HOUR (3600),
    DAY (86400);

    long unit_second;
    RateLimitingUnit(long second){
       this.unit_second = second;
    }

    public static long getUnitInSecond(RateLimitingUnit rlu){
        return rlu.unit_second;
    }
}
