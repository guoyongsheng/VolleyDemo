package xmen.doshr.com.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity
{

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init()
    {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> map = new HashMap<>();
        map.put("telephone", "18255215889");
        map.put("password", encode("111111"));
        map.put("position", "");
        JSONObject jsonObject = new JSONObject(map);


        String url = "http://192.168.0.30:8082/dsweb/ai/account/loginInfo.json";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                Log.i(TAG, "init onResponse" + " jsonObject = " + s);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {

            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("telephone", "18255215889");
                map.put("password", encode("111111"));
                map.put("position", "");
                return map;
            }
        };

        requestQueue.add(stringRequest);

        /*JsonRequest<JSONObject> jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                Log.i(TAG, "init onResponse" + " jsonObject = " + jsonObject);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Log.i(TAG, "init onErrorResponse" + " volleyError = " + volleyError);
            }
        })
        {


            protected Map<String, String> getParams()
            {
                Map<String, String> map = new HashMap<>();
                map.put("telephone", "18255215889");
                map.put("password", encode("111111"));
                map.put("position", "");
                return map;
            }

        };

        requestQueue.add(jsonObjectRequest);*/



    }


    public static String encode(String data)
    {
        // 用于存放返回结果
        String enData = null;
        // 1.存放返回值为哈希值结果
        byte result[] = null;
        if (data != null && data.length() > 0)
        {
            try
            {
                // 2.获得MD5摘要算法的 MessageDigest对象
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                if (messageDigest != null)// 判空
                {
                    // 3.使用给定的字节更新摘要
                    messageDigest.update(data.getBytes());
                    // 4.该函数返回值为存放哈希值结果的byte数组中,获得密文
                    result = messageDigest.digest();
                    // 5.把密文转换成十六进存放在byte数组中
                    enData = toHex(result);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        // 6.返回密文
        return enData;
    }

    private static String toHex(byte[] data)
    {
        String ret = "";
        // 1.检查参数
        if (data != null && data.length > 0)
        {
            int count = data.length;
            for (int i = 0; i < count; i++)
            {
                // 2. 返回参数的十六进制（基数 16）无符号整数值的字符串。
                String hex = Integer.toHexString(data[i] & 0xFF);
                if (hex.length() == 1)
                {
                    hex = '0' + hex;
                }
                ret += hex;
            }
        }
        return ret;
    }


   /* private class JsonRequest extends JsonObjectRequest
    {

        public JsonRequest(int method, String url, JSONObject jsonObject , Response.Listener<JSONObject> listener, Response.ErrorListener errorListener)
        {
            super(method, url, jsonObject, listener, errorListener);
        }

       *//* @Override
        protected Map<String, String> getParams()
        {
            Map<String, String> map = new HashMap<>();
            map.put("telephone", "18255215889");
            map.put("password", encode("111111"));
            map.put("position", "");
            return map;
        }*//*

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError
        {
            HashMap<String,String> headers = new HashMap<>();
            headers.put("Accept", "application/json");
            headers.put("Content-Type", "application/json; charset=UTF-8");
            return headers;
        }
    }*/
}
