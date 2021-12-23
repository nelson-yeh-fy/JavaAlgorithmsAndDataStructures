package com.javaalgorithms.sorts;

import org.junit.jupiter.api.Test;

class MergeSortTest {

    @Test
    void sort() {
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(integers);

        for (int i = 0; i < integers.length - 1; ++i) {
            assert integers[i] <= integers[i + 1];
        }
        /* output: [1, 4, 6, 9, 12, 23, 54, 78, 231] */

        Integer[] integers1 = {-5, 0, 78, -49, 0, 78, 99, -90};
        mergeSort.sort(integers1);

        for (int i = 0; i < integers1.length - 1; ++i) {
            assert integers1[i] <= integers1[i + 1];
        }
        /* output: [-90, -49, -5, 0, 0, 78, 78, 99] */

        String[] strings = {"c", "a", "e", "b", "d"};
        mergeSort.sort(strings);
        for (int i = 0; i < strings.length - 1; i++) {
            assert strings[i].compareTo(strings[i + 1]) <= 0;
        }
        /* output: [a, b, c, d, e] */
    }
}