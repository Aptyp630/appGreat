package com.algorithms.sorting;

import java.util.Comparator;

/**
 * O(n²)<br\>
 * Faster than SelectionSorter
 */
class InsertionSorter extends AbstractSorter {
    @Override
    public <Key extends Comparable<Key>> void sortAscending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;
        for(int i = 1; i < length; ++i) {
            for(int j = i; j > 0 && less(arrayToSort[j], arrayToSort[j-1]); --j)
                exchange(arrayToSort, j, j-1);

        }
    }

    @Override
    public <Key extends Comparable<Key>> void sortDescending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;
        for(int i = 1; i < length; ++i) {
            for(int j = i; j > 0 && less(arrayToSort[j-1], arrayToSort[j]); --j)
                exchange(arrayToSort, j, j-1);

        }
    }

    @Override
    public <Key> void sortAscending(Key[] a, Comparator<Key> c) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 && less(c, a[j], a[j-1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    @Override
    public <Key> void sortDescending(Key[] a, Comparator<Key> c) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 && less(c, a[j-1], a[j]); j--) {
                exchange(a, j, j-1);
            }
        }
    }
}
