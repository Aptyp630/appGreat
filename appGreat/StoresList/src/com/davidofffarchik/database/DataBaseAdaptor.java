package com.davidofffarchik.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.davidofffarchik.constans.Constans;
import com.davidofffarchik.models.Product;

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

    public int columnItems(){
        Cursor cursor = sq.query(ProductTable.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getColumnCount();
    }

    public String[] getColumnNames(){
        Cursor cursor = sq.query(ProductTable.TABLE_NAME, null, null, null, null, null, null);
        return cursor.getColumnNames();
    }

    public Product readOneProduct(String title){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_TITLE, title);
        Cursor cursor = sq.query(true, ProductTable.TABLE_NAME, new String[]{String.valueOf(contentValues)}, null, null, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Product product = new Product
                (
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_ID))),
                cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_TITLE)),
                cursor.getString(cursor.getColumnIndex(ProductTable.PRODUCT_DESCRIPTION))
                );
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

    private void updateProduct(Product product){
        ContentValues contentValues = new ContentValues();
        //String where = ProductTable.PRODUCT_ID + "=" +
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        sq.update(ProductTable.TABLE_NAME, contentValues, null, null);
    }
/*
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();
        //String selectQuery = "SELECT * FROM "  +ProductTable.TABLE_NAME;
        Cursor cursor = sq.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_COLUMNS, null, null, null, null, null, null);
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
        long rowID = sq.insert(ProductTable.DB_NAME, null, contentValues);
        return rowID;
    }

  public int updateProducts(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        return sq.update(ProductTable.DB_NAME, contentValues, null, null);
    }

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
