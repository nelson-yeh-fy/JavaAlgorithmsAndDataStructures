package com.javaalgorithms.ratelimiter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class TokenBucketLimiter implements RateLimiting {
    static final int DEFAULT_MAX_TOKENS = 4;
    static final int DEFAULT_TOKEN_REFILL_RATE = 1;
    private long MAX_TOKENS; // The max bucket capacity;
    private long possibleTokens;
    private long refillTokenPerUnit;
    private long lastRequestTime;

    /** Creates a TokenBucketLimiter with the default max tokens (bucket capability) and refill rate.
     */
    TokenBucketLimiter(){
        this(DEFAULT_MAX_TOKENS, DEFAULT_TOKEN_REFILL_RATE);
    }

    /** Creates a TokenBucketLimiter with the default max tokens (bucket capability) and refill rate.
     *
     * @param maxTokens the max token number of the bucket.
     * @param refillTokenPerUnit the refill rate per limit unit.
     */
    TokenBucketLimiter(int maxTokens, int refillTokenPerUnit){
        this.MAX_TOKENS = maxTokens;
        this.refillTokenPerUnit = refillTokenPerUnit;
        this.possibleTokens = 0;
        this.lastRequestTime = System.currentTimeMillis();
    }

    /**
     * Main method make a request
     *
     * @param throttleID - to support different type of throttle rules, either IP, User ID, or other properties
     * @param request - naive request content for demonstration
     * @return a sorted array
     */
    @Override
    public synchronized Response makeRequest(long throttleID, String request) throws InterruptedException {
        return null;
    }

    public synchronized void getToken(long throttleID, String request) throws InterruptedException {

        possibleTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;// * refillTokenPerUnit;
        if(possibleTokens > this.MAX_TOKENS){
            possibleTokens = this.MAX_TOKENS;
        }

        if(possibleTokens == 0){
            TimeUnit.SECONDS.sleep(1);
            //return new Response(ResponseHeader.X_RATE_LIMITER_RETRY_AFTER, refillTokenPerUnit);
        } else {
            possibleTokens--;
            //return new Response(ResponseHeader.X_RATE_LIMITER_REMAINING, possibleTokens);
        }
        lastRequestTime = System.currentTimeMillis();
        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + possibleTokens);
    }

    /**
     * for a rate limiter to load a configuration
     *
     * @param config - String for demo purpose, may change it to an object which contains more detail configurations.
     */
    @Override
    public void loadConfiguration(String config){
        
    }

    /**
     * Driver Code
     */
    public static void main(String[] args) throws InterruptedException {

        // 1. Initiate our rate limiter
        TokenBucketLimiter limiter = new TokenBucketLimiter();
        limiter.loadConfiguration("Testing Configuration");
        TimeUnit.SECONDS.sleep(5);

        Set<Thread> allThreads = new HashSet<>();
        // Create 15 threads to simulate the requests
        for(int i = 0; i < 15 ; i++) {
            Thread t = new Thread(() -> { // lambda to create anonymous Runnable class
                try {
                    limiter.getToken(1, "");
                } catch (InterruptedException e) {
                    System.out.println("Exception occurred: " + e.getMessage());
                }
            });
            t.setName("Thread_" + (i + 1));
            allThreads.add(t);
        }
        
        for(Thread t : allThreads) {
            t.start();
        }
        for(Thread t : allThreads) {
            t.join();
        }
    }
}
