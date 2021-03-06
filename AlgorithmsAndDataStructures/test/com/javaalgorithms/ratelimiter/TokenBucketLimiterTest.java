package com.javaalgorithms.ratelimiter;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class TokenBucketLimiterTest {

    void runTestWithMaxToken(RateLimitingUnit unit, long maxLimitPerUnit, long fixedProcessRate, int threadCount) throws InterruptedException {

        // 1. Initiate our rate limiter
        TokenBucketLimiter limiter = new TokenBucketLimiter("Demo",
                "Global Bucket",
                "Global",
                unit,
                maxLimitPerUnit,
                fixedProcessRate);

        // 2. Create multiple threads to simulate requests
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
        runTestWithMaxToken(RateLimitingUnit.SECOND, 5, 1, 8);
        runTestWithMaxToken(RateLimitingUnit.SECOND, 5, 3, 8);
        runTestWithMaxToken(RateLimitingUnit.MINUTE, 100, 60, 200);
    }
}