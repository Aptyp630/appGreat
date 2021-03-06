package com.davidofffarchik.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.davidofffarchik.R;
import com.davidofffarchik.models.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductListAdapter extends BaseAdapter{

    private Context context;
    private List<Product> productList = new ArrayList<Product>();
    private LayoutInflater inflater;

    public ProductListAdapter(Context context){
        this.context = context;
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
        //textView.setText(String.valueOf(newProduct.getProductId()));
        textView.setText(newProduct.getTitle());
        //textView.setText(newProduct.getDescription());
        //ИЗМЕНЕНИЯ СОГЛАСНО СТРАНИЦАМ ЛАТИТУД И ЛОНГИТУД
        //textView.setText(String.valueOf(newProduct.getLatitude()));
        //textView.setText(String.valueOf(newProduct.getLongitude()));
        return view;
    }

    public void addProducts(List<Product> productList) {
        this.productList.addAll(productList);
    }
}
