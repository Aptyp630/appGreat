package com.davidofffarchik.query;

import android.content.Context;
import android.util.Log;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.models.Pagination;
import com.davidofffarchik.models.Product;
import com.davidofffarchik.models.ProductResult;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QueryToServer {

    //СОЗДАНИЕ ОЧЕРЕДИ ЗАПРОСОВ
    //ВЫЗОВ МЕТОДОВ, КОТОРЫЕ ПАРСИЛИ ОБЪЕКТ И МАССИВ
    public static OnResponseListener callGetProducts(final OnResponseListener showProductListener, final Context context, final int currentPage) {
        String url = "http://protected-wave-2984.herokuapp.com/api/product_list.json?page="+currentPage;
                Log.v(Constans.LOG_TAG, "Status_URL " +currentPage);
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
        return showProductListener;
    }
    //##################################################################################

    //РАСПАРСИЛ ОБЪЕКТ
    private static Pagination parsePagination(JSONObject jsonObject){
        try {
            JSONObject paginationJson = jsonObject.getJSONObject("pagination");
                    int total_page = paginationJson.getInt("total_page");
                    int current_page = paginationJson.getInt("current_page");
                    int per_page = paginationJson.getInt("per_page");
            Pagination pagination = new Pagination(total_page, current_page, per_page);

            return pagination;
        } catch (JSONException e) {e.printStackTrace();
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
                Product product = new Product(id, title, description);
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
        //создать еще один метод, вызвать его в слушателе ошибки выше
    }
}
