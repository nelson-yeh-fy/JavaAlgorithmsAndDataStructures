package com.javaalgorithms.sorts;

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

    /**
     * Recursively doing MergeSort by separating one big chunk of the array to two parts
     * @param array the original array.
     * @param i range starts from i
     * @param j range ends at j
     * @param <T> the sorted array
     */
    private <T extends Comparable<T>> void sort(T[] array, int i, int j){
        if(i < j){
            int mid = i + (j - i) / 2;
            sort(array, i, mid);
            sort(array, mid + 1, j);
            merge(array, i, mid, j);
        }
    }

    /**
     * The private function to merge two sorted arrays.
     *
     * @param array the array that we want to merge its two internally sorted ranges.
     * @param start the first sorted range begins at "start", ends at "mid"
     * @param mid the index we separate two part in the array
     * @param end the second sorted range begins at "mid" + 1, ends at "end"
     * @param <T> the same array but it's sorted when the function ends
     */
    private <T extends Comparable<T>> void merge(T[] array, int start, int mid, int end) {
        int i = mid, j = end; // Use two pointers to check values from two sorted range.
        int len = end - start + 1;
        int m = len - 1;
        @SuppressWarnings("unchecked cast")
        T[] tmp = (T[]) new Comparable[len];

        while(i >= start && j > mid) {
            if (array[i].compareTo(array[j]) < 0) {
                tmp[m--] = array[j--];
            } else {
                tmp[m--] = array[i--];
            }
        }
        while(i >= start){
            tmp[m--] = array[i--];
        }
        while(j > mid) {
            tmp[m--] = array[j--];
        }
        System.arraycopy(tmp, 0, array, start, len);
    }
}
