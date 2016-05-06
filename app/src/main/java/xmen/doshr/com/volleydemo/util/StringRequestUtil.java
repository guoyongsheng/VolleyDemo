package xmen.doshr.com.volleydemo.util;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by wesley on 2016/3/28.
 */
public class StringRequestUtil extends StringRequest
{

    public StringRequestUtil(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(method, url, listener, errorListener);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return super.getParams();
    }
}
