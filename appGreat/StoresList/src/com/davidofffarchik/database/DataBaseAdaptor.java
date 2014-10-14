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
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseAdaptor(Context context){
        this.context = context;
        dataBase = new DataBase(context);
    }

    public void getColumnNames(Product product){
        addProduct(product);
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null, null);
        String uid = cursor.getColumnName(0);
        String id = cursor.getColumnName(1);
        String title = cursor.getColumnName(2);
        String description = cursor.getColumnName(3);
        Log.v("Names", "uid =" +uid+ "id " +id+ "title " +title+ "description " +description);
    }

    //open DataBase
    public DataBaseAdaptor openDB(){
        sqLiteDatabase = dataBase.getWritableDatabase();
        return this;
    }

    //close any DataBase object
    public synchronized void closeDB(){
        dataBase.close();
    }

    public Product readOneProduct(String title){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_TITLE, title);
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.TABLE_NAME, new String[]{String.valueOf(contentValues)}, null, null, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Product product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return product;
    }

    public void saveProducts(List<Product> productList){
        for(Product product : productList){
            if(getProductByID(product.getProductId()) == null) {
                addProduct(product);
                Log.v(Constans.LOG_TAG, "added products");
            }else
            updateProduct(product);
        }
    }

    private Product getProductByID(int id){
        String where = ProductTable.PRODUCT_ID + "=" + id;
        Log.v("where", "WHERE");
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, where, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_DESCRIPTION));
            cursor.close();
            return new Product(id, title, description);
        }
        return null;
    }

   public void addProduct(Product product){
           ContentValues contentValues = new ContentValues();
           contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
           contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
           contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
           sqLiteDatabase.insert(ProductTable.TABLE_NAME, null, contentValues);
   }

    public void updateProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        sqLiteDatabase.update(ProductTable.TABLE_NAME, contentValues, null, null);
    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();
        //String selectQuery = "SELECT * FROM "  +ProductTable.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                for(Product title : products){
                    products.add(title);
                }
            }while(cursor.moveToNext());
        }
        return products;
    }


    //setData in DB
   public long saveOrUpdate(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        long rowID = sqLiteDatabase.insert(ProductTable.DB_NAME, null, contentValues);
        return rowID;
    }

  public int updateProducts(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        return sqLiteDatabase.update(ProductTable.DB_NAME, contentValues, null, null);
    }

    //return all data in DB
    public Cursor returnProductsFromDB(){
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.DB_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

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
