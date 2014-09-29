package com.example.models;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;


public class ProductList extends BaseAdapter{

    Context context;
    ArrayAdapter<Products> productsArrayAdapter;
    LayoutInflater inflater;

    public ProductList(Context context, ArrayAdapter<Products> productsArrayAdapter){
        this.context = context;
        this.productsArrayAdapter = productsArrayAdapter;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return productsArrayAdapter.getCount();
    }

    @Override
    public Products getItem(int position) {
        return productsArrayAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
