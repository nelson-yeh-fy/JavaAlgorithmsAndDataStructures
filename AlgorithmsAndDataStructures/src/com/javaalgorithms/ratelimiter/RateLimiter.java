package com.javaalgorithms.ratelimiter;

public abstract class RateLimiter {
    final String DOMAIN;
    final String DESCRIPTOR_KEY;
    final String DESCRIPTOR_VALUE;
    final RateLimitingUnit RATE_LIMIT_UNIT;
    final long MAX_BUCKET_CAPACITY;
    final long FIXED_PROCESS_RATE;

    /** Creates a Rate Limiter.
     *
     * @param domain Rate Limiter domain name
     * @param descriptorKey Rate Limiter descriptor key as the throttle categories, e.g.: IP, UserID, other properties
     * @param descriptorValue Rate Limiter descriptor value, e.g.: 10.1.1.100, 001, other properties
     * @param unit Limiter unit, described in the RateLimitingUnit enum
     * @param maxBucketCapacity the max size of a bucket (token bucket, leaky bucket) or a window (fixed window, sliding window).
     * @param fixedProcessRate the processing rate, it will be token refill rate for TokenBucketLimiter, and processRate for LeakyBucketLimiter
     */
    RateLimiter(String domain, String descriptorKey, String descriptorValue, RateLimitingUnit unit,
                long maxBucketCapacity, long fixedProcessRate){
        this.DOMAIN = domain;
        this.DESCRIPTOR_KEY = descriptorKey;
        this.DESCRIPTOR_VALUE = descriptorValue;
        this.RATE_LIMIT_UNIT = unit;
        this.MAX_BUCKET_CAPACITY = maxBucketCapacity;
        this.FIXED_PROCESS_RATE = fixedProcessRate;
    }
}
