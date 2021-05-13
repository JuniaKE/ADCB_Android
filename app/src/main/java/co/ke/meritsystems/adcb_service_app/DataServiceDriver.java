package co.ke.meritsystems.adcb_service_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DataServiceDriver {
    private static DataServiceDriver instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private DataServiceDriver(Context context){
        ctx = context;
    }
    public static synchronized DataServiceDriver getInstance(Context context){
        if(instance == null){
            instance = new DataServiceDriver(context);
        }
        return instance;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
//            getApplicationContext() is key. It keeps you from leaking the activity or broadcast receiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
