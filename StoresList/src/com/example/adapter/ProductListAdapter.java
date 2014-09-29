package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.R;
import com.example.models.Product;

import java.util.List;


public class ProductListAdapter extends BaseAdapter{

    Context context;
    List<Product> productList;
    LayoutInflater inflater;

    public ProductListAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view = inflater.inflate(R.layout.text_review, parent, false);
        }

        Product newProduct = getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.text_item);
        textView.setText(newProduct.getTitle());

        return view;
    }
}
