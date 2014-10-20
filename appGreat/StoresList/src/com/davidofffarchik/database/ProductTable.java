package com.davidofffarchik.database;

public class ProductTable {

    //DATABASE
    //DataBase Fields Name
    public final static String UID = "_id";
    public final static String PRODUCT_ID = "product_id";
    public final static String PRODUCT_TITLE = "product_title";
    public final static String PRODUCT_DESCRIPTION = "product_description";
    public final static String PRODUCT_LATITUDE = "latitude";
    public final static String PRODUCT_LONGITUDE = "longitude";
    public final static String[] ALL_COLUMNS = new String[] {UID, PRODUCT_ID, PRODUCT_TITLE, PRODUCT_DESCRIPTION, PRODUCT_LATITUDE, PRODUCT_LONGITUDE};
    //############################################################################

    public final static String DB_NAME = "products";
    public final static String TABLE_NAME = "product_table";
    public final static int DB_VERSION = 1;
    public final static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUCT_ID + " INTEGER, "
            + PRODUCT_TITLE + " TEXT, "
            + PRODUCT_DESCRIPTION + " TEXT, "
            + PRODUCT_LATITUDE + " REAL, "
            + PRODUCT_LONGITUDE + " REAL "
            + ")";
    //############################################################################

}
