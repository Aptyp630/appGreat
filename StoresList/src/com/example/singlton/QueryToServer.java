package com.example.singlton;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.models.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//СОЗДАЕМ СИНГЛТОН
public class QueryToServer {
    public static QueryToServer queryInstance = new QueryToServer();

    //СОЗДАЕМ ДЛЯ СИНГЛТОНА МЕТОД, ЧЕРЕЗ КОТОРЫЙ БУДЕМ ОБРАЩАТЬСЯ
    public static synchronized QueryToServer getInstance(){
        if(queryInstance ==null) {
            queryInstance = new QueryToServer();
        }
        return queryInstance;
    }
    //##################################################################################################################

    ////ОТПРАВЛЯЕМ ЗАПРОС НА СЕРВЕР, ПЕРЕДАВАЯ СЛУШАТЕЛЯ
    public OnShowProductListener requestQueue(OnShowProductListener showProductListener){
        Context context = null;
        String url = "http://protected-wave-2984.herokuapp.com/api/product_list.json?page=2";
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    parseJsonObject(jsonObject);
                    parseJsonArray(jsonObject);
                    }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.v("TAG", volleyError.getMessage());
                }
            });
            requestQueue.add(jsonObjectRequest);
        return showProductListener;
    }
    //##################################################################################################################

    //ПАРСИМ ОБЪЕКТ
    private JSONObject parseJsonObject(JSONObject jsonObject){
        try {
            JSONObject pagination = jsonObject.getJSONObject("pagination");
                {
                    int total_page = pagination.getInt("total_page");
                    int current_page = pagination.getInt("current_page");
                    int per_page = pagination.getInt("per_page");
                }
        } catch (JSONException e) {e.printStackTrace();}
        return jsonObject;
    }
    //##################################################################################################################

    //ПАРСИМ МАССИВ
    private List<Product> parseJsonArray(JSONObject jsonObject){
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
    //##################################################################################################################

    //СОЗДАЕМ ИНТЕРФЕЙС СЛУШАТЕЛЯ
    public interface OnShowProductListener{
        public void showProduct(List<Product> p);
    }
}
