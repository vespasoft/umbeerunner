package com.umbeerunner.android.umbeerunner.controller;

import android.content.Context;

import com.umbeerunner.android.umbeerunner.localstorage.UserSQLite;
import com.umbeerunner.android.umbeerunner.model.User;
import com.umbeerunner.android.umbeerunner.preferences.SessionPreferences;

/**
 * Created by Luis Vespa on 11/02/2016.
 */
public class UserLocalStore {
    private static final String TAG = UserLocalStore.class.getSimpleName();

    private Context context;
    private SessionPreferences session;
    private UserSQLite userData;
    private User dtoUser;

    public UserLocalStore() {
        dtoUser = new User();
    }

    public UserLocalStore(Context ctx) {
        context = ctx;
        // Session manager
        session = new SessionPreferences(ctx);

        // SQLite database handler
        userData = new UserSQLite(ctx);
        userData.abrir();

    }

    /**
     * function que crea un usuario nuevo en la BD SQLite del dispositivo
     * y inicia sesion guardando en preferencias usando la clase session
     * */
    public void CreateAccount(User dto) {
        /* Este metodo inserta el usuario en la BD SQLite
        *  Si el usuario esta registrado, entonces actualiza
        *  el registro.
        * */
        userData.addNewUser(dto);
        // Formatea todas las preferencias en '' para el nuevo usuario que inicia session
        session.closeSession();
        // Al crear una cuenta automaticamente inicia la session
        session.setLogin(true, dto.getUserId(), dto.getName(), dto.getUsertype());
    }

    /* Este metodo retorna una clase Users con los datos del usuario
       en caso de haber iniciado session correctamente.
    * */
    public User getUserLogged() {
        User dto =null;
        if (session.isLoggedIn())
            // Verifica si existen un usuario registrado con el mismo TokenID
            dto = userData.getUser(session.getUID());
        return dto;
    }

    /* Este metodo cierra la sessión del usuario, verifica primero que este habierta la session
       luego cierra la session en la variable de preferencias
    * */
    public void logoutUser() {
        if (session.isLoggedIn())
            session.closeSession();
    }

    /**
     * function to return isLoggedIn
     * */
    public boolean isLoggedIn() {
        return session.isLoggedIn();
    }

}
