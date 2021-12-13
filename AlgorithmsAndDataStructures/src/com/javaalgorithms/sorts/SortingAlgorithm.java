package com.javaalgorithms.sorts;

/**
 * The common interface of most sorting algorithms
 *
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 */
public interface SortingAlgorithm {
    /**
     * Main method arrays sorting algorithms
     *
     * @param unsorted - an array should be sorted
     * @return a sorted array
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);
}
