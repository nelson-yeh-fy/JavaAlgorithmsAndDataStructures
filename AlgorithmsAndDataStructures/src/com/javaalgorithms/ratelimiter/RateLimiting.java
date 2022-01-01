package com.javaalgorithms.ratelimiter;

/**
 * The common interface of most rate limiting algorithms
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 */
public interface RateLimiting {
    /**
     * Main method make a request
     *
     * @param throttleID - to support different type of throttle rules, either IP, User ID, or other properties
     * @param request - naive request content for demonstration
     * @return a sorted array
     */
    Response makeRequest(long throttleID, String request) throws InterruptedException;

    /**
     * for a rate limiter to load a configuration
     *
     * @param config - String for demo purpose, may change it to an object which contains more detail configurations.
     */
    void loadConfiguration(String config);
}
