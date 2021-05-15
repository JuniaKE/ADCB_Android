package co.ke.meritsystems.adcb_service_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    //Variables
    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;


    private static final String IS_LOGIN = "IsLogegdIn";


    public static final String KEY_USERID = "userId";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DARKMODE = "darkMode";
    public static final String KEY_ACCESSTOKEN = "accessToken";


    public SessionManager (Context context){
        context = context;
        usersSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }

    public void createLoginSession(String name, String email, String phone, String darkMode, String userId, String accessToken){
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_USERID, userId);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_DARKMODE, darkMode);
        editor.putString(KEY_ACCESSTOKEN, accessToken);

        editor.commit();
    }

    public HashMap<String, String> getUserDetailsFromSession(){
        HashMap<String,String> userData = new HashMap<String, String>();

        userData.put(KEY_USERID, usersSession.getString(KEY_USERID, null));
        userData.put(KEY_NAME, usersSession.getString(KEY_NAME, null));
        userData.put(KEY_EMAIL, usersSession.getString(KEY_EMAIL, null));
        userData.put(KEY_PHONE, usersSession.getString(KEY_PHONE, null));
        userData.put(KEY_DARKMODE, usersSession.getString(KEY_DARKMODE, null));
        userData.put(KEY_ACCESSTOKEN, usersSession.getString(KEY_ACCESSTOKEN, null));

        return userData;
    }

    public boolean checkLogin(){
        if(usersSession.getBoolean(IS_LOGIN, true )){
            return true;
        }
        else
            return false;
    }
    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }
}
