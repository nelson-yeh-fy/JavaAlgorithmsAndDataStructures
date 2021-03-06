package com.javaalgorithms.ratelimiter;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class TokenBucketLimiterWithDaemonThreadTest {

    void runTestWithMaxToken(RateLimitingUnit unit, long maxLimitPerUnit, long fixedProcessRate, int threadCount) throws InterruptedException {

        // 1. Initiate our rate limiter
        TokenBucketLimiterWithDaemonThread limiter = new TokenBucketLimiterWithDaemonThread("Demo",
                "Global Bucket",
                "Global",
                unit,
                maxLimitPerUnit,
                fixedProcessRate);

        /* 2. After the rate limiter is fully constructed, use the threadFactory to create its daemon thread,
         * which fills the token bucket in a given rate.
         */
        TokenBucketLimiterWithDaemonThreadFactory tf = new TokenBucketLimiterWithDaemonThreadFactory();
        Thread limiterThread = tf.newThread( () -> {
            try {
                limiter.daemonThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("limiter Thread's name:" + limiterThread.getName() + ", Thread Count: " + tf.getCount());
        limiterThread.start();

        // 3. Create multiple threads to simulate requests
        Set<Thread> allThreads = new HashSet<>();
        for(int i = 0; i < threadCount ; i++) {
            Thread t = new Thread(() -> { // lambda to create anonymous Runnable class
                try {
                    final Response res = limiter.makeRequest(1, "sample request");
                    System.out.println(res.responseHeader.toString() + ", " + res.value);
                } catch (InterruptedException e) {
                    System.out.println("Exception occurred: " + e.getMessage());
                }
            });
            t.setName("Thread_" + (i + 1));
            allThreads.add(t);
        }

        for(Thread t : allThreads) {
            // Simulating multiple requests in different time frame by delaying every thread,
            TimeUnit.MILLISECONDS.sleep(500);
            t.start();
        }
        for(Thread t : allThreads) {
            t.join();
        }
    }

    @Test
    void simulateRequests() throws InterruptedException {
        runTestWithMaxToken(RateLimitingUnit.SECOND, 3, 1, 8);
        runTestWithMaxToken(RateLimitingUnit.SECOND, 5, 3, 10);
        runTestWithMaxToken(RateLimitingUnit.MINUTE, 100, 60, 200);
    }
}