package com.stay4it.volleyextends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView mJsonObjectLabel;
    private TextView mJsonStringLabel;
    private Button mDeserializeBtn;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsonObjectLabel = (TextView) findViewById(R.id.mJsonObjectLabel);
        mJsonStringLabel = (TextView) findViewById(R.id.mJsonStringLabel);
        mDeserializeBtn = (Button) findViewById(R.id.mDeserializeBtn);
        mDeserializeBtn.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this,new OkHttpStack(new OkHttpClient()));
    }


    @Override
    public void onClick(View v) {
        GsonRequest stringRequest = new GsonRequest<User>(Request.Method.GET, "http://api.stay4it.com/test/for_gson_test.php",
                User.class,new Response.Listener<User>() {

                    @Override
                    public void onResponse(User response) {
                        Log.d("TAG", response.toString());
                        mJsonObjectLabel.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                mJsonObjectLabel.setText(error.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }
}
