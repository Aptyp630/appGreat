package com.algorithms.sorting;

import com.algorithms.debug_utils.Log;

import java.util.Comparator;

public abstract class AbstractSorter implements Sorter {

    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/


    protected boolean isEmpty(Comparable<? extends Object>[] array) {
        return array == null || array.length == 0;
    }

    protected boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /*// is v < w ?
    protected static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // is v < w ?
    protected static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

    // exchange a[i] and a[j]
    protected static void exchange(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }*/

    // is v < w ?
    protected static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    // is v < w?
    protected static <Key> boolean less(Comparator<Key> comparator, Key v, Key w) {
        return comparator.compare(v, w) < 0;
    }


    // exchange a[i] and a[j]
    protected static <Key> void exchange(Key[] a, int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // exchange a[i] and a[j]  (for indirect sortAscending)
    protected static void exchange(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***********************************************************************
     *  Check if array is sorted - useful for debugging
     ***********************************************************************/
    protected static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    protected static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    protected static boolean isSorted(Object[] a, Comparator c, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(c, a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            Log.d("AbstractSorter", a[i].toString());
        }
    }
}
