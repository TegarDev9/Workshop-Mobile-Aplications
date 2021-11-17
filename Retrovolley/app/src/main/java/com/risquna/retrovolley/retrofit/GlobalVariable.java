package com.risquna.retrovolley.retrofit;

import android.content.Context;

public class GlobalVariable {
    private Context context;
    public static String BASE_URL = "http://192.168.43.51";
    public static String TYPE_CONN =  "typeConnection";
    public static String RETROFIT = "retrofit";
    public static String VOLLEY = "volley";
    public static String PREFERENCE_NAME = "Retrovolley";
    public static String CURRENT_USER_ID = "currentUserID";
    public static String CURRENT_USERNAME = "currentUsername";
    public static String CURRENT_USER_EMAIL = "currentUserEmail";
    public static String CURRENT_USER_PASSWORD = "currentUserPassword";
    public static String UPDATE_USER = "updateUser";
    public static String DELETE_USER = "deleteuser";
    public GlobalVariable() {
    }
    public GlobalVariable(Context context) { this.context = context; }

}
