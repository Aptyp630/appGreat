package com.davidofffarchik.query;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.davidofffarchik.models.Pagination;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.ProductResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QueryToServer {

    //СОЗДАНИЕ ОЧЕРЕДИ ЗАПРОСОВ
    //ВЫЗОВ МЕТОДОВ, КОТОРЫЕ ПАРСИЛИ ОБЪЕКТ И МАССИВ
    public static void callGetProducts(final OnResponseListener showProductListener, final Context context, final int currentPage) {
        String url = "http://protected-wave-2984.herokuapp.com/api/product_list.json?page="+currentPage;
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Pagination pagination = parsePagination(jsonObject);
                        List<Product> product = parseProducts(jsonObject);
                        ProductResult productResult = new ProductResult(pagination, product);
                        showProductListener.onProductsReceived(productResult);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        showProductListener.errorInternetConnection();
                    }
                });
                requestQueue.add(jsonObjectRequest);
    }
    //##################################################################################

    //РАСПАРСИЛ ОБЪЕКТ
    private static Pagination parsePagination(JSONObject jsonObject){
        try {
            JSONObject paginationJson = jsonObject.getJSONObject("pagination");
                    int total_page = paginationJson.getInt("total_page");
                    int current_page = paginationJson.getInt("current_page");
                    int per_page = paginationJson.getInt("per_page");
            return new Pagination(total_page, current_page, per_page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //##################################################################################

    //РАСПАРСИЛ МАССИВ
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
    //##################################################################################

    //СОЗДАНИЕ ИНТЕРФЕЙСА СЛУШАТЕЛЯ
    public interface OnResponseListener {
        public void onProductsReceived(ProductResult productResult);
        public void errorInternetConnection();
    }
}
