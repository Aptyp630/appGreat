package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.constans.Constans;
import com.example.models.Product;

//create DataBaseAdaptor
public class DataBaseAdaptor{

    private DataBase dataBase;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseAdaptor(Context context){
        this.context = context;
        dataBase = new DataBase(context);
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

    //setData in DB
    public long saveOrUpdate(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductTable.PRODUCT_ID, product.getProductId());
        contentValues.put(ProductTable.PRODUCT_TITLE, product.getTitle());
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, product.getDescription());
        return sqLiteDatabase.insert(ProductTable.TABLE_NAME, null, contentValues);
    }

    //return all data in DB
    public Cursor returnData(){
        Cursor cursor = sqLiteDatabase.query(true, ProductTable.TABLE_NAME, ProductTable.ALL_PRODUCTS, null, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //upDate Rows
    public boolean updateRows(long rowID, int productId, String productTitle, String productDescription){
        ContentValues contentValues = new ContentValues();
        String where = ProductTable.UID + "=" + rowID;
        contentValues.put(String.valueOf(ProductTable.PRODUCT_ID), productId);
        contentValues.put(ProductTable.PRODUCT_TITLE, productTitle);
        contentValues.put(ProductTable.PRODUCT_DESCRIPTION, productDescription);
        return sqLiteDatabase.update(ProductTable.TABLE_NAME, contentValues, where, null) != 0;
    }

    public static class DataBase extends SQLiteOpenHelper {

        public DataBase(Context context) {
            super(context, ProductTable.TABLE_NAME, null, ProductTable.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ProductTable.CREATE_DATABASE);
            Log.v(Constans.LOG_TAG, "Data_Base allReady Created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Destroyed table
            db.execSQL("DROP TABLE IF EXISTS" + ProductTable.TABLE_NAME);
            onCreate(db);
            Log.v(Constans.LOG_TAG, "Data_Base allReady upGraded");
        }
    }
}
