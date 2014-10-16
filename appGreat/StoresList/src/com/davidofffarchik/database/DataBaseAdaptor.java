package com.davidofffarchik.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.models.Product;

import java.util.ArrayList;
import java.util.List;

//create DataBaseAdaptor
public class DataBaseAdaptor{

    private DataBase dataBase;
    private Context context;
    private SQLiteDatabase sq;

    public DataBaseAdaptor(Context context){
        this.context = context;
        dataBase = new DataBase(context);
    }

    //open DataBase
    public DataBaseAdaptor openDB(){
        sq = dataBase.getWritableDatabase();
        return this;
    }

    //close any DataBase object
    public synchronized void closeDB(){
        dataBase.close();
    }
//methods to retrieve
    public int getColumnsCount(){
        Cursor cursor = sq.query(ProductTable.TABLE_NAME, null, null, null, null, null, null);

        return cursor.getColumnCount();
    }

    public int getRowsCounts(){
        Cursor cursor = sq.query(ProductTable.TABLE_NAME, null, null, null, null, null, null);

        return cursor.getCount();
    }

    public String[] getColumnNames(){
        Cursor cursor = sq.query(ProductTable.TABLE_NAME, null, null, null, null, null, null);

        return cursor.getColumnNames();
    }

    public void saveProducts(List<Product> productList){
        for(Product product : productList){
            if(getProductByID(product.getProductId()) == null) {
                addProduct(product);
            }else
            updateProduct(product, product.getProductId());
        }
    }

    private Product getProductByID(int id){
        String where = ProductTable.PRODUCT_ID + "=" + id;
        Cursor cursor = sq.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, where, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_DESCRIPTION));
            cursor.close();
            return new Product(id, title, description);
        }
        return null;
   }

   private void addProduct(Product product){
           ContentValues contentValues = new ContentValues();
           contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
           contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
           contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
           sq.insert(ProductTable.TABLE_NAME, null, contentValues);
   }

    private void updateProduct(Product product, int id){
        ContentValues contentValues = new ContentValues();
        String where = ProductTable.PRODUCT_ID + "=" + id;
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        sq.update(ProductTable.TABLE_NAME, contentValues, where, null);
    }

    public List<Product> getProductTitle(){
        List<Product> productList = new ArrayList<Product>();
            Cursor cursor = sq.query(ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(ProductTable.PRODUCT_ID));
                String title = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_TITLE));
                String description = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_DESCRIPTION));
                Product product = new Product(id, title, description);
                productList.add(product);
            }while (cursor.moveToNext());
        }
        return productList;
    }

/*



    //return all data in DB
    public Cursor returnProductsFromDB(){
        Cursor cursor = sq.query(true, ProductTable.DB_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
*/
    public static class DataBase extends SQLiteOpenHelper {

        public DataBase(Context context) {
            super(context, ProductTable.DB_NAME, null, ProductTable.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ProductTable.CREATE_TABLE);
            Log.v(Constans.LOG_TAG, "Data_Base Created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ProductTable.TABLE_NAME);
            onCreate(db);
            Log.v(Constans.LOG_TAG, "Data_Base allReady upGraded");
        }
    }
}
