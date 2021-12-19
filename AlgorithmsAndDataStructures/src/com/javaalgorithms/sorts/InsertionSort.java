package com.javaalgorithms.sorts;
import static com.javaalgorithms.sorts.SortingUtils.*;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class InsertionSort implements SortingAlgorithm {

    /**
     * Implements generic Insertion Sort algorithm.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array) {
        T key;
        int j;
        for (int k = 1; k < array.length; k++) {
            key = array[k];
            j = k-1;

            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j+1] = key;
        }
        return array;
    }
}
