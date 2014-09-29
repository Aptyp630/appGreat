package com.algorithms.sorting;

import java.util.Comparator;

/**
 * Faster than InsertionSorter<br\>
 * Worst case O(n²)<br\>
 * The number of compares used by shellsort with the increments
 * 1, 4, 13, 40, 121, 364, ... is O(N ^ 3/2).
 */
public class ShellSorter extends AbstractSorter {


    @Override
    public <Key extends Comparable<Key>> void sortAscending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;

        int d = 1;
        while (d < length/3)
            d = 3*d + 1;                  // 1, 4, 13, 40, 121, 364, …

        while (d >= 1) {
            //d-sort the array
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && less(arrayToSort[j], arrayToSort[j - d]); j -= d)
                    exchange(arrayToSort, j, j - d);
            }
            d = d/3;
        }
    }

    @Override
    public <Key extends Comparable<Key>> void sortDescending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;

        int d = 1;
        while (d < length/3)
            d = 3*d + 1;                  // 1, 4, 13, 40, 121, 364, …

        while (d >= 1) {
            //d-sort the array
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && !less(arrayToSort[j], arrayToSort[j - d]); j -= d)
                    exchange(arrayToSort, j, j - d);
            }
            d = d/3;
        }
    }

    @Override
    public <Key> void sortAscending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;

        int d = 1;
        while (d < length/3)
            d = 3*d + 1;                  // 1, 4, 13, 40, 121, 364, …

        while (d >= 1) {
            //d-sort the array
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && less(c, a[j], a[j - d]); j -= d)
                    exchange(a, j, j - d);
            }
            d = d/3;
        }
    }

    @Override
    public <Key> void sortDescending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;

        int d = 1;
        while (d < length/3)
            d = 3*d + 1;                  // 1, 4, 13, 40, 121, 364, …

        while (d >= 1) {
            //d-sort the array
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && !less(c, a[j], a[j - d]); j -= d)
                    exchange(a, j, j - d);
            }
            d = d/3;
        }
    }
}
