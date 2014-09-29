package com.algorithms.sorting;

public class SortingFactory {

    /**
     * O(n²)<br\>
     * Slower than InsertionSorter
     */
    public static Sorter getSelectionSorter() {
        return new SelectionSorter();
    }

    /**
     * O(n²)<br\>
     * Faster than SelectionSorter
     */
    public static Sorter getInsertionSorter() {
        return new InsertionSorter();
    }

    /**
     * Optimized insertion sort
     */
    public static Sorter getInsertionXSorter() {
        return new InsertionSorter();
    }

    /**
     * Faster than InsertionSorter. <br\>
     * Improved insertion sort. <br\>
     * Worst case O(n²).<br\>
     */
    public static Sorter getShellSorter() {
        return new ShellSorter();
    }
}
