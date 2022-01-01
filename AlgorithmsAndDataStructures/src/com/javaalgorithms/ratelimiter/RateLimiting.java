package com.javaalgorithms.ratelimiter;

/**
 * The common interface of most rate limiting algorithms
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public interface RateLimiting {
    /**
     * Main method make a request
     *
     * @param throttleID - to support different type of throttle rules, either IP, User ID, or other properties
     * @param request - naive request content for demonstration
     * @return Response object
     */
    Response makeRequest(long throttleID, String request) throws InterruptedException;
}
