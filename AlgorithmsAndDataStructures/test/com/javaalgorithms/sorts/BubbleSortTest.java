package com.javaalgorithms.sorts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.javaalgorithms.sorts.SortingUtils.swap;

class BubbleSortTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sort() {
        // Bubble sort
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(integers);

        for (int i = 0; i < integers.length - 1; ++i) {
            assert integers[i] <= integers[i + 1];
        }
        /* output: [1, 4, 6, 9, 12, 23, 54, 78, 231] */


        String[] strings = {"c", "a", "e", "b", "d"};
        bubbleSort.sort(strings);
        for (int i = 0; i < strings.length - 1; i++) {
            assert strings[i].compareTo(strings[i + 1]) <= 0;
        }
        /* output: [a, b, c, d, e] */
    }

    @Test
    void swapTest() {
        try {
            Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
            swap(integers, 0, 1);
            swap(integers, 0, 1);
            swap(integers, 0, 10);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array Index out of bounds" + e.getMessage());
        }
    }
}