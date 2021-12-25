package com.javaalgorithms.sorts;

import org.jetbrains.annotations.NotNull;

import static com.javaalgorithms.sorts.SortingUtils.swap;

/**
 * @author nelson-yeh-fy (https://https://github.com/nelson-yeh-fy)
 * @version 1.0
 * @since 1.0
 */
public class QuickSort implements SortingAlgorithm {
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

    private <T extends Comparable<T>> void sort(T[] array, int start, int end){
        if(start < end) {
            int k = partition(array, start, end);
            sort(array, start, k - 1);
            sort(array, k, end);
        }
    }

    private <T extends Comparable<T>> int partition(T @NotNull [] array, int lo, int hi){
        int mid = lo + (hi - lo) / 2;
        T pivot = array[mid];

		while (lo <= hi) {
			while(array[lo].compareTo(pivot) < 0) {
                lo++;
            }
            while(array[hi].compareTo(pivot) > 0) {
                hi--;
            }
            if(lo <= hi){
                swap(array, lo, hi);
                lo++;
                hi--;
			}
		}
        return lo;
    }
}
