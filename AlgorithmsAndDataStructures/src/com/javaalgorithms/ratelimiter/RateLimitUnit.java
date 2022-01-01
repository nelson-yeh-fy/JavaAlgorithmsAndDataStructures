package com.javaalgorithms.ratelimiter;

public enum RateLimitUnit {
    SECOND (1),
    MINUTE (60),
    HOUR (3600),
    DAY (86400);

    int unit_second;
    RateLimitUnit(int second){
       this.unit_second = second;
    }

    public int getUnitInSecond(RateLimitUnit rlu){
        return this.unit_second;
    }
}
