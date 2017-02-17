package com.umbeerunner.android.umbeerunner.localstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis Vespa on 18/02/2016.
 */
public class databaseHelper extends SQLiteOpenHelper {
    // nombre de la base de datos
    private static final String __DATABASE = "DBPICKUP_USER";
    // versión de la base de datos
    private static final int __VERSION = 1;

    // Users table name
    public final String TABLE_USERS = "users";                      // User table name
    // Careers table name
    public final String TABLE_CAREERS = "careers";

    // Nombres de las columnas de la tabla User
    public final String KEY_ID = "id";
    public final String KEY_UID = "userId";
    public final String KEY_CREATEDAT = "created_at";
    public final String KEY_UPDATEAT = "updated_at";
    public final String KEY_USERNAME = "username";
    public final String KEY_PASSWORD = "password";
    public final String KEY_NAME = "name";
    public final String KEY_USERTYPE = "usertype";


    // Nombres de las columnas de la tabla careers
    public final String KEY_POINT_LATITUDE = "point_lat";
    public final String KEY_POINT_LONGITUDE = "pint_long";
    public final String KEY_ARRIVAL_LATITUDE = "arrival_lat";
    public final String KEY_ARRIVAL_LONGITUDE = "arrival_long";
    public final String KEY_DISTANCE = "distance";
    public final String KEY_TIME = "time";

    // La cadena sql_user almacena la sentencia SQL que crea la tabla user en la Base de Datos.
    private final String sql_user = "CREATE TABLE " + TABLE_USERS + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_UID + " TEXT,"
            + KEY_CREATEDAT + " TEXT,"
            + KEY_UPDATEAT + " TEXT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_NAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_USERTYPE + " TEXT )";

    // La cadena sql_user almacena la sentencia SQL que crea la tabla user en la Base de Datos.
    private final String sql_careers = "CREATE TABLE " + TABLE_CAREERS + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_UID + " TEXT,"
            + KEY_CREATEDAT + " TEXT,"
            + KEY_UPDATEAT + " TEXT,"
            + KEY_POINT_LATITUDE + " DOUBLE,"
            + KEY_POINT_LONGITUDE + " DOUBLE,"
            + KEY_ARRIVAL_LATITUDE + " DOUBLE,"
            + KEY_ARRIVAL_LONGITUDE + " DOUBLE,"
            + KEY_DISTANCE + " TEXT,"
            + KEY_TIME + " TEXT )";


    /**
     * Constructor de clase
     * */
    public databaseHelper(Context context) {
        super( context, __DATABASE, null, __VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( sql_user );
        db.execSQL( sql_careers );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        if ( newVersion > oldVersion )
        {
            //elimina tabla
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_USERS );
            db.execSQL( "DROP TABLE IF EXISTS " + TABLE_CAREERS );
            //y luego creamos la nueva tabla
            db.execSQL( sql_user );
            db.execSQL( sql_careers );
        }
    }

}
