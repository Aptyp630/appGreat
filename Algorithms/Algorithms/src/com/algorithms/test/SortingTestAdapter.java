package com.algorithms.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.algorithms.R;

import java.util.List;

public class SortingTestAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<SortItem> sortItemList;

    public SortingTestAdapter(LayoutInflater inflater, List<SortItem> sortItemList) {
        this.inflater = inflater;
        this.sortItemList = sortItemList;
    }

    @Override
    public int getCount() {
        return sortItemList.size();
    }

    @Override
    public SortItem getItem(int position) {
        return sortItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.sorting_test_item, parent, false);
        SortItem item = sortItemList.get(position);
        ((TextView)v.findViewById(R.id.txt_name)).setText(item.sortingName);
        ((TextView)v.findViewById(R.id.txt_time)).setText("" + item.sortingTime);
        return v;
    }

    public void add(SortItem item) {
        sortItemList.add(item);
        notifyDataSetChanged();
    }


    public static class SortItem {
        String sortingName;
        long sortingTime;

        public SortItem(String sortingName, long sortingTime) {
            this.sortingName = sortingName;
            this.sortingTime = sortingTime;
        }
    }
}
