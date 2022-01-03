package com.javaalgorithms.ratelimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class TokenBucketLimiterWithDaemonThread extends RateLimiter implements RateLimiting {
    private long MAX_TOKENS; // The max bucket capacity;
    private long REFILL_TOKEN_PER_UNIT;
    private long availableTokens;

    /** Creates a Token Bucket Limiter.
     *
     * @param domain Rate Limiter domain name
     * @param descriptorKey Rate Limiter descriptor key as the throttle categories, e.g.: IP, UserID, other properties
     * @param descriptorValue Rate Limiter descriptor value, e.g.: 10.1.1.100, 001, other properties
     * @param unit Limiter unit, described in the RateLimitingUnit enum
     * @param maxLimitPerUnit the max limit it allows.
     * @param fixedProcessRate the processing rate, it will be token refill rate for TokenBucketLimiter, and processRate for LeakyBucketLimiter
     */
    TokenBucketLimiterWithDaemonThread(String domain, String descriptorKey, String descriptorValue, RateLimitingUnit unit,
                       long maxLimitPerUnit, long fixedProcessRate){

        super(domain, descriptorKey, descriptorValue, unit, maxLimitPerUnit, fixedProcessRate);

        this.MAX_TOKENS = maxLimitPerUnit;
        this.REFILL_TOKEN_PER_UNIT = fixedProcessRate;
        this.availableTokens = 0;

        // Sample, should not start the child thread in the constructor, will do other adjustment to prevent this.
        Thread t = new Thread( () -> {
            try {
                this.daemonThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void daemonThread() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while(true){
            synchronized (this){
                availableTokens += REFILL_TOKEN_PER_UNIT;
                if(availableTokens >= MAX_TOKENS) {
                    availableTokens = MAX_TOKENS;
                }
                this.notify();
            }
            TimeUnit.SECONDS.sleep(RateLimitingUnit.getUnitInSecond(RATE_LIMIT_UNIT));
        }
    }

    /**
     * Main method make a request
     *
     * @param throttleID - to support different type of throttle rules, either IP, User ID, or other properties.
     *                     for the current version, use a global bucket for all the requests.
     * @param request - naive request content for demonstration
     * @return a sorted array
     */
    public Response makeRequest(long throttleID, String request) throws InterruptedException {

        synchronized(this) {
            if (availableTokens == 0) {
                System.out.println("No token for " + Thread.currentThread().getName());
                return new Response(ResponseHeader.X_RATE_LIMITER_RETRY_AFTER, REFILL_TOKEN_PER_UNIT);
            } else {
                availableTokens--;
                System.out.println("Granting " + Thread.currentThread().getName() + " token. (Available tokens now: " + availableTokens + ")");
                /* we may use the rate limiter as the middleware to throttle requests,
                   only send requests to the internal load balancer / api servers when there is an available token.
                   (e.g.: Server.processHttpRequest(request);
                 */
                return new Response(ResponseHeader.X_RATE_LIMITER_REMAINING, availableTokens);
            }
        }
    }
}
