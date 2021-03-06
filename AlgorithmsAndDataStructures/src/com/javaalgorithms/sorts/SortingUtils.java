package com.javaalgorithms.sorts;

public class SortingUtils {
    /**
     * Helper method for swapping places in array
     *
     * @param array The array which elements we want to swap
     * @param idx index of the first element
     * @param idy index of the second element
     */
    static <T> boolean swap(T[] array, int idx, int idy){
        try {
            T temp = array[idx];
            array[idx] = array[idy];
            array[idy] = temp;
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array Index out of bounds" + e.getMessage());
            throw e;
        }
    }
}
