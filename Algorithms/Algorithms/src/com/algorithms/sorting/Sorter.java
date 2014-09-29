package com.algorithms.sorting;

import java.util.Comparator;

public interface Sorter {
    <Key extends Comparable<Key>> void sortAscending(Key[] arrayToSort);
    <Key extends Comparable<Key>> void sortDescending(Key[] arrayToSort);
    <Key> void sortAscending(Key[] a, Comparator<Key> c);
    <Key> void sortDescending(Key[] a, Comparator<Key> c);
}
