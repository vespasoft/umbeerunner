package com.umbeerunner.android.umbeerunner.localstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.umbeerunner.android.umbeerunner.model.User;

/**
 * Created by vespa on 14/02/2016.
 */

public class UserSQLite {
    private databaseHelper sqliteHelper;
    private SQLiteDatabase db;

    /** Constructor de clase */
    public UserSQLite(Context context)
    {
        sqliteHelper = new databaseHelper( context );
    }

    /** Abre conexion a base de datos */
    public void abrir(){
        Log.i("SQLite", "Se abre conexion a la base de datos DB_SE_Purchase"   );
        db = sqliteHelper.getWritableDatabase();
    }

    /** Cierra conexion a la base de datos */
    public void cerrar()
    {
        Log.i("SQLite", "Se cierra conexion a la base de datos DB_SE_Purchase" );
        sqliteHelper.close();
    }

    /**
     * Metodo para agregar un nuevo registro
     * @param dto
     * @return BOOLEAN TRUE si tuvo exito FALSE caso contrario
     * */
    public boolean add(User dto) {
        // 1. get reference to writable DB
        db = sqliteHelper.getWritableDatabase();

        if( dto.getUsername().length() > 0 )
        {
            ContentValues values = new ContentValues();
            values.put(sqliteHelper.KEY_UID, dto.getUserId());                  // UID
            values.put(sqliteHelper.KEY_CREATEDAT, dto.getCreated_at());        // Create Date
            values.put(sqliteHelper.KEY_UPDATEAT, dto.getUpdated_at());         // Update Date
            values.put(sqliteHelper.KEY_USERNAME, dto.getUsername());           // Username or Email
            values.put(sqliteHelper.KEY_NAME, dto.getName());                   // name
            values.put(sqliteHelper.KEY_PASSWORD, dto.getPassword());           // password
            values.put(sqliteHelper.KEY_USERTYPE, dto.getUsertype());           // user type
            Log.i(sqliteHelper.TABLE_USERS, "Nuevo registro "+dto.getUsername()+" TokenID "+dto.getUserId() );
            return ( db.insert( sqliteHelper.TABLE_USERS , null, values ) != -1 )?true:false;
        }
        else
            return false;

    }

    /* Este método verifica que el items agregar no este en la lista del cart
    *  en caso de estar en la lita, agregar al items en la misma linea
    *  incrementando solo el campo quantity().
    * */
    public boolean addNewUser(User dto) {
        User result;
        result = getUser(dto.getUserId());
        if ( result == null ) {
            // Agrega el items en el carro de compras
            return ( add(dto) );
        } else {
            // De lo contrario, se actualiza los datos del usuario
            return ( update(dto) );
        }
    }

    /**
     * Metodo para editar un registro existente
     * @param dto
     * @return BOOLEAN TRUE si tuvo exito FALSE caso contrario
     * */
    public boolean update(User dto)
    {
        // 1. get reference to writable DB
        db = sqliteHelper.getWritableDatabase();
        if( dto.getUserId().length() > 0 )
        {
            ContentValues values = new ContentValues();
            values.put(sqliteHelper.KEY_UID, dto.getUserId());                  // UID
            values.put(sqliteHelper.KEY_CREATEDAT, dto.getCreated_at());        // Create Date
            values.put(sqliteHelper.KEY_UPDATEAT, dto.getUpdated_at());         // Update Date
            values.put(sqliteHelper.KEY_USERNAME, dto.getUsername());           // Username or Email
            values.put(sqliteHelper.KEY_NAME, dto.getName());                   // name
            values.put(sqliteHelper.KEY_PASSWORD, dto.getPassword());           // password
            values.put(sqliteHelper.KEY_USERTYPE, dto.getUsertype());           // user type
            Log.i(sqliteHelper.TABLE_USERS, "Editar registro "+dto.getUsername()+" TokenID "+dto.getUserId());
            return db.update(sqliteHelper.TABLE_USERS , values, sqliteHelper.KEY_UID + "='" + dto.getUserId() +"'", null) > 0;
        }
        else
            return false;
    }

    /**
     * @param id del registro a eliminar
     * @return BOOLEAN
     * */
    public boolean delete( String id )
    {
        // 1. get reference to writable DB
        db = sqliteHelper.getWritableDatabase();
        // table , whereClause, whereArgs
        return  (db.delete( sqliteHelper.TABLE_USERS, sqliteHelper.KEY_ID + " = " + id ,  null) > 0) ? true:false;
    }
    /**
     * @return BOOLEAN
     * */
    public boolean deleteAllUsers()
    {
        // 1. get reference to writable DB
        db = sqliteHelper.getWritableDatabase();
        // table , whereClause, whereArgs
        return  (db.delete( sqliteHelper.TABLE_USERS, sqliteHelper.KEY_ID + " > 0",  null) > 0) ? true:false;
    }

    /**
     * Obtiene un registro a partir de userId
     * */
    public User getUser(String tokenID)
    {
        User dto = null;
        // 1. get reference to writable DB
        db = sqliteHelper.getWritableDatabase();

        // 2. build query
        Cursor cursor = db.query( sqliteHelper.TABLE_USERS ,
                new String[]{
                        sqliteHelper.KEY_ID,
                        sqliteHelper.KEY_UID,
                        sqliteHelper.KEY_USERNAME,
                        sqliteHelper.KEY_NAME,
                        sqliteHelper.KEY_PASSWORD,
                        sqliteHelper.KEY_USERTYPE}, sqliteHelper.KEY_UID + " = '" + tokenID + "'",
                null, null, null, null);
        // 3. Move to first row
        cursor.moveToFirst();
        Log.i("SQLite", " tokenID: " + tokenID);
        if(cursor.getCount() > 0){
            dto = new User();
            dto.setId(cursor.getInt(0));
            dto.setUserId(cursor.getString(1));
            dto.setUsername(cursor.getString(2));
            dto.setName(cursor.getString(3));
            dto.setPassword(cursor.getString(4));
            dto.setUsertype(cursor.getString(5));
        }
        cursor.close();
        db.close();
        // return user
        return dto;
    }

}