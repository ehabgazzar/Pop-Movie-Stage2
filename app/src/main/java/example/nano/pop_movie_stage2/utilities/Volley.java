package example.nano.pop_movie_stage2.utilities;

import android.content.Context;

import com.android.volley.RequestQueue;

/**
 * Created by ehab- on 4/16/2017.
 */

public class Volley {

    Context mContext ;
    private RequestQueue mRequestQueue;
    private static Volley mInstance; // static to be in all app .

    private Volley(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }


    /**
     * Get instance from volley .
     *
     * @param context
     * @return
     */
    public static Volley getInstance(Context context)
    {
        if(mInstance == null)
            mInstance = new Volley(context);

        return mInstance;
    }

    /**
     * Get instance from volley request queue.
     *
     * @return
     */
    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue == null)
            mRequestQueue = com.android.volley.toolbox.Volley.newRequestQueue(mContext.getApplicationContext());

        return mRequestQueue;
    }
}
