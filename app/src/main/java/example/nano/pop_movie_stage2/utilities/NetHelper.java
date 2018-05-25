package example.nano.pop_movie_stage2.utilities;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by ehab- on 4/15/2017.
 */

public class NetHelper {
    private static NetHelper mInstance;
    private static RequestQueue mRequestQueue;

    private NetHelper(Context context) {
        mRequestQueue = Volley.getInstance(context).getRequestQueue();
    }


    /**
     * Get an Instance from NetHelper in all the project (Signleton pattern).
     *
     * @return NetHelper
     */
    public static NetHelper getInstance(Context context) {
        if (mInstance == null)
            mInstance = new NetHelper(context);

        return mInstance;
    }

    public void fetchMovies(final String url, Response.Listener<String> successLinstener, Response.ErrorListener errorListener) {
        StringRequest request = new StringRequest(Request.Method.GET, url, successLinstener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

               return map;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(request);
    }

}
