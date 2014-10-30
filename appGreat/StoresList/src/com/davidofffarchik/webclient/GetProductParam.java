package com.davidofffarchik.webclient;


import com.android.volley.Request;
import com.davidofffarchik.models.Pagination;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.ProductResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetProductParam extends Parameter<ProductResult>{

    private int page;

    public GetProductParam(int page) {
        this.page = page;
    }

    @Override
    public int getRequestMethod() {
        return Request.Method.GET;
    }

    @Override
    public ProductResult parseResponse(JSONObject jsonObject) {
        Pagination pagination = parsePagination(jsonObject);
        List<Product> products = parseProducts(jsonObject);
        return new ProductResult(pagination, products);
    }

    @Override
    public JSONObject getBody() {
        return null;
    }

    @Override
    public String getApiMethod() {
        return "product_list.json?page=";
    }

    private static Pagination parsePagination(JSONObject jsonObject){
        try {
            JSONObject paginationJson = jsonObject.getJSONObject("pagination");
            int total_page = paginationJson.getInt("total_page");
            int current_page = paginationJson.getInt("current_page");
            int per_page = paginationJson.getInt("per_page");
            return new Pagination(total_page, current_page, per_page);
        } catch (JSONException e) {e.printStackTrace();
        }
        return null;
    }

    private static List<Product> parseProducts(JSONObject jsonObject){
        List<Product> listProduct = new ArrayList<Product>();
        try {
            JSONArray productsJsonArray = jsonObject.getJSONArray("products");
            for(int i=0; i<productsJsonArray.length(); i++){
                JSONObject jObj = productsJsonArray.getJSONObject(i);
                int id = jObj.getInt("id");
                String title = jObj.getString("title");
                String description = jObj.getString("description");
                Double latitude = jObj.getDouble("lat");
                Double longitude = jObj.getDouble("long");
                Product product = new Product(id, title, description, latitude, longitude);
                listProduct.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listProduct;
    }
}
