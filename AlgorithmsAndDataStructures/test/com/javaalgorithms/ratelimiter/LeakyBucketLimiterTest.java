package com.javaalgorithms.ratelimiter;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

class LeakyBucketLimiterTest {

    void runTestWithScheduledExecutor(RateLimitingUnit unit, long maxLimitPerUnit, long fixedProcessRate, int threadCount) {

        // 1. Initiate our rate limiter
        LeakyBucketLimiter limiter = new LeakyBucketLimiter("Demo",
                "Global Bucket",
                "Global",
                unit,
                maxLimitPerUnit,
                fixedProcessRate);

        /* 2. After the rate limiter is fully constructed, use ThreadFactory to create a daemon thread,
         * which process requests in the leaky bucket(queue).
         */
        ThreadFactory tf = Executors.defaultThreadFactory();
        Thread limiterThread = tf.newThread( () -> {
            try {
                limiter.daemonThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("limiter Thread's name:" + limiterThread.getName());
        limiterThread.start();


        /* 3. Create multiple threads to simulate requests periodically
         */
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(threadCount);
        executor.scheduleAtFixedRate(()->{
            try {
                final Response res = limiter.makeRequest(101, "sample request from user 1");
                System.out.println(res.responseHeader.toString() + ", " + res.value);
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }, 0, 1,TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(()->{
            try {
                final Response res = limiter.makeRequest(201, "sample request from user 2");
                System.out.println(res.responseHeader.toString() + ", " + res.value);
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }, 0, 1,TimeUnit.SECONDS);

        // Wait for 10 seconds
        try {
            Thread.sleep(10000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }


    static void runTestWithCallable(RateLimitingUnit unit, long maxLimitPerUnit, long fixedProcessRate, int threadCount) throws InterruptedException, ExecutionException {

        // 1. Initiate our rate limiter
        LeakyBucketLimiter limiter = new LeakyBucketLimiter("Demo",
                "Global Bucket",
                "Global",
                unit,
                maxLimitPerUnit,
                fixedProcessRate);

        /* 2. After the rate limiter is fully constructed, use ThreadFactory to create a daemon thread,
         * which process requests in the leaky bucket(queue).
         */
        ThreadFactory tf = Executors.defaultThreadFactory();
        Thread limiterThread = tf.newThread( () -> {
            try {
                limiter.daemonThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("limiter Thread's name:" + limiterThread.getName());
        limiterThread.start();

        /* 3. Create multiple threads to simulate requests periodically
         */
        ClientTask client1 = new ClientTask(1, limiter);
        ClientTask client2 = new ClientTask(2, limiter);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //noinspection InfiniteLoopStatement
        while(true) {
            Future<String> future = executorService.submit(client1);
            System.out.println(future.get());
            Future<String> future2 = executorService.submit(client2);
            System.out.println(future2.get());
        }
        //executorService.shutdown();
    }

    public static class ClientTask implements Callable<String> {
        int callerId;
        LeakyBucketLimiter limiter;
        ClientTask(int callerId, LeakyBucketLimiter limiter){
            this.callerId = callerId;
            this.limiter = limiter;
        }
        public String call() throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
            Response rp = limiter.makeRequest(callerId, "request from " + callerId);
            return "Caller(" + this.callerId + ") make a request, Result: " + rp.responseHeader.toString() + ": " + rp.value;
        }
    }

    @Test
    void simulateRequests() throws InterruptedException, ExecutionException {
        runTestWithScheduledExecutor(RateLimitingUnit.SECOND, 3, 1, 2);
        runTestWithScheduledExecutor(RateLimitingUnit.SECOND, 5, 2, 2);
        runTestWithScheduledExecutor(RateLimitingUnit.MINUTE, 100, 60, 200);
        runTestWithCallable(RateLimitingUnit.SECOND, 3, 2, 3);
        runTestWithCallable(RateLimitingUnit.SECOND, 3, 1, 2);
    }
}