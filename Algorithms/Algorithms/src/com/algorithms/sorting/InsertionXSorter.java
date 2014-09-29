package com.algorithms.sorting;

import java.util.Comparator;

/**
 * Optimized insertion sort
 */
class InsertionXSorter extends AbstractSorter {

    @Override
    public <Key extends Comparable<Key>> void sortAscending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;

        // put smallest element in position to serve as sentinel
        for (int i = length-1; i > 0; i--)
            if (less(arrayToSort[i], arrayToSort[i-1])) exchange(arrayToSort, i, i - 1);

        // insertion sort with half-exchanges
        for (int i = 2; i < length; i++) {
            Key v = arrayToSort[i];
            int j = i;
            while (less(v, arrayToSort[j-1])) {
                arrayToSort[j] = arrayToSort[j-1];
                j--;
            }
            arrayToSort[j] = v;
        }
    }

    @Override
    public <Key extends Comparable<Key>> void sortDescending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;

        // put biggest element in position to serve as sentinel
        for (int i = length-1; i > 0; i--)
            if (less(arrayToSort[i-1], arrayToSort[i])) exchange(arrayToSort, i, i-1);

        // insertion sort with half-exchanges
        for (int i = 2; i < length; i++) {
            Key v = arrayToSort[i];
            int j = i;
            while (!less(v, arrayToSort[j-1])) {
                arrayToSort[j] = arrayToSort[j-1];
                j--;
            }
            arrayToSort[j] = v;
        }
    }

    @Override
    public <Key> void sortAscending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;

        // put smallest element in position to serve as sentinel
        for (int i = length-1; i > 0; i--)
            if (less(c, a[i], a[i-1])) exchange(a, i, i-1);

        // insertion sort with half-exchanges
        for (int i = 2; i < length; i++) {
            Key v = a[i];
            int j = i;
            while (less(c, v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }

    @Override
    public <Key> void sortDescending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;

        // put biggest element in position to serve as sentinel
        for (int i = length-1; i > 0; i--)
            if (less(c, a[i-1], a[i])) exchange(a, i, i-1);

        // insertion sort with half-exchanges
        for (int i = 2; i < length; i++) {
            Key v = a[i];
            int j = i;
            while (!less(c, v, a[j-1])) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }
}
