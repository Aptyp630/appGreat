package com.algorithms.sorting;

import java.util.Comparator;

/**
 * O(n²)<br\>
 * Selection sortAscending uses ~N2/2 compares and N exchanges to sortAscending an array of length N.
 * Slower than InsertionSorter
 */
class SelectionSorter extends AbstractSorter {
    @Override
    public <Key extends Comparable<Key>> void sortAscending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;
        for(int i = 0; i < length; ++i) {
            int min = i;
            for(int j = i + 1; j < length; ++j)
                if(less(arrayToSort[j], arrayToSort[min]))
                    min = j;
            exchange(arrayToSort, i, min);
        }
    }

    @Override
    public <Key extends Comparable<Key>> void sortDescending(Key[] arrayToSort) {
        if(isEmpty(arrayToSort)) return;
        final int length = arrayToSort.length;
        for(int i = 0; i < length; ++i) {
            int max = i;
            for(int j = i + 1; j < length; ++j)
                if(less(arrayToSort[max], arrayToSort[j]))
                    max = j;
            exchange(arrayToSort, i, max);
        }
    }

    @Override
    public <Key> void sortAscending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i+1; j < length; j++) {
                if (less(c, a[j], a[min])) min = j;
            }
            exchange(a, i, min);
        }
    }

    @Override
    public <Key> void sortDescending(Key[] a, Comparator<Key> c) {
        if(isEmpty(a)) return;
        final int length = a.length;
        for (int i = 0; i < length; i++) {
            int max = i;
            for (int j = i+1; j < length; j++) {
                if (less(c, a[max], a[j])) max = j;
            }
            exchange(a, i, max);
        }
    }
}
