package com.javaalgorithms.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class MergeSort implements SortingAlgorithm{
    /**
     * Implements generic Merge Sort algorithm.
     *
     * @param array the array to be sorted.
     * @param <T> the type of elements in the array.
     * @return the sorted array.
     */
    @Override
    public <T extends Comparable<T>> T[] sort(T[] array){
        sort(array, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void sort(T[] array, int i, int j){
        if(i < j){
            int mid = i + (j - i) / 2;
            sort(array, i, mid);
            sort(array, mid + 1, j);
            merge(array, i, mid, j);
        }
    }

    private <T extends Comparable<T>> void merge(T[] array, int start, int mid, int end) {
        int i = mid, j = end;
        while(j > start) {
            if (array[i].compareTo(array[j]) <= 0) {
                j--;
            } else {
                T temp = array[j];
                array[j--] = array[i--];
                array[i] = temp;
            }
        }
    }
}
