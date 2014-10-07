package com.example.database;

public class ProductTable {

    //DATABASE
    //DataBase Fields Name
    public final static String UID = "_id";
    public final static String PRODUCT_ID = "product_id";
    public final static String PRODUCT_TITLE = "product_title";
    public final static String PRODUCT_DESCRIPTION = "product_description";
    public final static String[] ALL_PRODUCTS = new String[] {PRODUCT_ID, PRODUCT_TITLE, PRODUCT_DESCRIPTION};
    //############################################################################

    //DataBase Columns
    public final static int COL_ID = 1;
    public final static int COL_TITLE = 2;
    public final static int COL_DESCRIPTION = 3;
    //############################################################################

    public final static String DB_NAME = "ProductsDB.db";
    public final static String TABLE_NAME = "products";
    public final static int DB_VERSION = 1;
    public final static String CREATE_DATABASE
            = "CREATE TABLE " + ProductTable.TABLE_NAME
            + "("
            + ProductTable.UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProductTable.PRODUCT_ID + "INTEGER"
            + ProductTable.PRODUCT_TITLE + " TEXT, "
            + ProductTable.PRODUCT_DESCRIPTION + " TEXT, "
            + ");";
    //############################################################################

}
