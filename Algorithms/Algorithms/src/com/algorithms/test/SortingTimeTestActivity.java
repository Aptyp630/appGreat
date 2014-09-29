package com.algorithms.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.algorithms.R;
import com.algorithms.debug_utils.Log;
import com.algorithms.sorting.Sorter;
import com.algorithms.sorting.SortingFactory;
import com.algorithms.test.SortingTestAdapter.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortingTimeTestActivity extends Activity {
    private SortingTestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorting_time_test_screen);
        initList();
        startCommonSelectoinArray(startSelectionSort(), SortingFactory.getSelectionSorter());
    }

    private void initList() {
        adapter = new SortingTestAdapter(getLayoutInflater(), new ArrayList<SortItem>());
        ListView list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTesting();
    }

    private void startTesting() {
        final int size = 10000;
        Random random = new Random();
        Integer array[] = new Integer[size];
        for(int i = 0; i < size; ++i) {
            array[i] = random.nextInt(size * 999);
        }

        startSelectionSort(array);
        startInsertionSort(array);
        startInsertionXSort(array);
        startShellSort(array);

    }

    private void startCommonSelectoinArray(String sortingName, Sorter sorter){
        Integer[] copyArray;
        long startTime = System.currentTimeMillis();
        long result = System.currentTimeMillis() - startTime;
        addAdapterItem(new SortingTestAdapter.SortItem("Selection sort", result));

    }

    private void startSelectionSort(final Integer[] array) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SortingFactory.getSelectionSorter().sortAscending(copyArray);
            }
        }).start();
    }

    private void startInsertionSort(final Integer[] array) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer[] copyArray = Arrays.copyOf(array, array.length);
                long startTime = System.currentTimeMillis();
                SortingFactory.getInsertionSorter().sortAscending(copyArray);
                long result = System.currentTimeMillis() - startTime;
                addAdapterItem(new SortingTestAdapter.SortItem("Insertion sort", result));
            }
        }).start();
    }

    private void startInsertionXSort(final Integer[] array) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer[] copyArray = Arrays.copyOf(array, array.length);
                long startTime = System.currentTimeMillis();
                SortingFactory.getInsertionXSorter().sortAscending(copyArray);
                long result = System.currentTimeMillis() - startTime;
                addAdapterItem(new SortingTestAdapter.SortItem("InsertionX sort", result));
            }
        }).start();
    }

    private void startShellSort(final Integer[] array) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Integer[] copyArray = Arrays.copyOf(array, array.length);
                long startTime = System.currentTimeMillis();
                SortingFactory.getShellSorter().sortAscending(copyArray);
                long result = System.currentTimeMillis() - startTime;
                addAdapterItem(new SortingTestAdapter.SortItem("Shell sort", result));
            }
        }).start();
    }

    private void addAdapterItem(final SortingTestAdapter.SortItem item) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.add(item);
            }
        });
    }

    private void logArray(Integer[] array) {
        for(Integer i : array)
            Log.d("SortingTimeTestActivity", "" + i + "  ");
    }
}
