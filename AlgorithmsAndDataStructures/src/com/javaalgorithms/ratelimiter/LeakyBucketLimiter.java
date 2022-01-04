package com.javaalgorithms.ratelimiter;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class LeakyBucketLimiter extends RateLimiter implements RateLimiting {

    private long taskCount; // how many tasks are queued, this is similar with TokenBucketLimiter's availableTokens, which represent tokens in an opposite way
    private Queue<String> tasks;

    /** Creates a Leaky Bucket Limiter.
     *
     * @param domain Rate Limiter domain name
     * @param descriptorKey Rate Limiter descriptor key as the throttle categories, e.g.: IP, UserID, other properties
     * @param descriptorValue Rate Limiter descriptor value, e.g.: 10.1.1.100, 001, other properties
     * @param unit Limiter unit, described in the RateLimitingUnit enum
     * @param maxBucketCapacity the max size of a bucket (token bucket, leaky bucket) or a window (fixed window, sliding window).
     * @param fixedProcessRate the processing rate, it will be token refill rate for TokenBucketLimiter, and processRate for LeakyBucketLimiter
     */
    LeakyBucketLimiter(String domain, String descriptorKey, String descriptorValue, RateLimitingUnit unit,
                       long maxBucketCapacity, long fixedProcessRate){

        super(domain, descriptorKey, descriptorValue, unit, maxBucketCapacity, fixedProcessRate);

        this.taskCount = 0;
        tasks = new LinkedList<>();
    }

    /** A daemon thread to process the bucket continuously.
     * In the leaky bucket filter, we see the max token number as a queue's capacity, and process the queue continuously.
     * Construct the class object first, and then we start this thread to process the bucket.
     * @throws InterruptedException throws InterruptedException
     */
    public void daemonThread() throws InterruptedException {
        //noinspection InfiniteLoopStatement
        while(true){
            synchronized (this){
                // Process our bucket (queue) in a fixed rate, e.g.: process 3 requests per second
                for(long i = FIXED_PROCESS_RATE; i >= 0 && taskCount > 0; i--){
                    taskCount--;

                    String task = tasks.poll();
                    /* we could send the requests to the internal load balancer / api servers.
                     * e.g.: Server.processHttpRequest(task);
                     */
                    System.out.println("Process one task (Remaining Tasks: " + tasks.size() + ")");
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
            if (taskCount >= MAX_BUCKET_CAPACITY) {
                //System.out.println("Not available for " + Thread.currentThread().getName() + " (Remaining Tasks: " + taskCount + ")");
                return new Response(ResponseHeader.X_RATE_LIMITER_RETRY_AFTER, RateLimitingUnit.getUnitInSecond(RATE_LIMIT_UNIT));
            } else {
                taskCount++;
                tasks.offer(request);
                //System.out.println("Accepting task from " + Thread.currentThread().getName() + " (Remaining Tasks: " + taskCount + ")");
                /* we may use the rate limiter as the middleware to throttle requests,
                   only send requests to the internal load balancer / api servers when there is an available token.
                   (e.g.: Server.processHttpRequest(request);
                 */
                return new Response(ResponseHeader.X_RATE_LIMITER_REMAINING_AVAILABLE, MAX_BUCKET_CAPACITY - taskCount);
            }
        }
    }
}
