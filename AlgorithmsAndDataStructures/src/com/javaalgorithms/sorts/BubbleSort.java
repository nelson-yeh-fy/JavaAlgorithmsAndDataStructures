package com.javaalgorithms.sorts;

import static com.javaalgorithms.sorts.SortingUtils.*;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class BubbleSort implements SortingAlgorithm {
    /**
     * Implements generic bubble sort algorithm.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements in the array.
     * @return the sorted array.
     */
    //@Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        for (int i = 1, size = array.length; i < size; ++i) {
            boolean swapped = false;
            for (int j = 0; j < size - i; ++j) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }
}