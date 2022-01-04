package com.javaalgorithms.ratelimiter;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class TokenBucketLimiter extends RateLimiter implements RateLimiting {
    private long MAX_TOKENS; // The max bucket capacity;
    private long REFILL_TOKEN_PER_UNIT;
    private long availableTokens;
    private long lastRequestTime;

    /** Creates a Token Bucket Limiter.
     *
     * @param domain Rate Limiter domain name
     * @param descriptorKey Rate Limiter descriptor key as the throttle categories, e.g.: IP, UserID, other properties
     * @param descriptorValue Rate Limiter descriptor value, e.g.: 10.1.1.100, 001, other properties
     * @param unit Limiter unit, described in the RateLimitingUnit enum
     * @param maxBucketCapacity the max size of a bucket (token bucket, leaky bucket) or a window (fixed window, sliding window).
     * @param fixedProcessRate the processing rate, it will be token refill rate for TokenBucketLimiter, and processRate for LeakyBucketLimiter
     */
    TokenBucketLimiter(String domain, String descriptorKey, String descriptorValue, RateLimitingUnit unit,
                       long maxBucketCapacity, long fixedProcessRate){

        super(domain, descriptorKey, descriptorValue, unit, maxBucketCapacity, fixedProcessRate);

        this.MAX_TOKENS = maxBucketCapacity;
        this.REFILL_TOKEN_PER_UNIT = fixedProcessRate;

        /* The lastRequestTime is used for calculating available tokens,
           set the initial value as 3 secs ago; this way, it fills tokens to the bucket at the beginning
           similar to the approach if we start the limiter and then TimeUnit.SECONDS.sleep(3);
         */
        this.availableTokens = 0;
        this.lastRequestTime = System.currentTimeMillis() - 3000;
    }

    /**
     * Main method make a request
     *
     * @param throttleID - to support different type of throttle rules, either IP, User ID, or other properties.
     *                     for the current version, use a global bucket for all the requests.
     * @param request - naive request content for demonstration
     * @return a sorted array
     */
    public synchronized Response makeRequest(long throttleID, String request) throws InterruptedException {

        availableTokens += (System.currentTimeMillis() - lastRequestTime) / 1000
                / RateLimitingUnit.getUnitInSecond(super.RATE_LIMIT_UNIT)
                * REFILL_TOKEN_PER_UNIT;

        if(availableTokens > this.MAX_TOKENS){
            availableTokens = this.MAX_TOKENS;
        }

        if(availableTokens == 0){
            //System.out.println("No token for " + Thread.currentThread().getName());
            return new Response(ResponseHeader.X_RATE_LIMITER_RETRY_AFTER, RateLimitingUnit.getUnitInSecond(RATE_LIMIT_UNIT));
        } else {
            availableTokens--;
            lastRequestTime = System.currentTimeMillis();
            //System.out.println("Granting " + Thread.currentThread().getName() + " token. (Available tokens now: " + availableTokens + ")");
            /* we may use the rate limiter as the middleware to throttle requests,
               only send requests to the internal load balancer / api servers when there is an available token.
               (e.g.: Server.processHttpRequest(request);
             */
            return new Response(ResponseHeader.X_RATE_LIMITER_REMAINING_AVAILABLE, availableTokens);
        }
    }
}
