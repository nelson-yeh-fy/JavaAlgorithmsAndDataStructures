package com.javaalgorithms.ratelimiter;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class TokenBucketLimiterWithDaemonThreadFactory implements ThreadFactory {
    // stores the thread count
    private int count = 0;

    // returns the thread count
    public int getCount() { return count; }

    /** Override the ThreadFactory's newThread method, this takes runnable command to create a new thread
     *
     * @param r Runnable command
     * @return new thread
     */
    @Override
    public Thread newThread(@NotNull Runnable r) {
        this.count++;
        return new Thread(r);
    }
}
