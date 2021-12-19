package com.javaalgorithms.sorts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void sort() {
        // Bubble sort
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(integers);

        for (int i = 0; i < integers.length - 1; ++i) {
            assert integers[i] <= integers[i + 1];
        }
        /* output: [1, 4, 6, 9, 12, 23, 54, 78, 231] */


        String[] strings = {"c", "a", "e", "b", "d"};
        insertionSort.sort(strings);
        for (int i = 0; i < strings.length - 1; i++) {
            assert strings[i].compareTo(strings[i + 1]) <= 0;
        }
        /* output: [a, b, c, d, e] */
    }
}