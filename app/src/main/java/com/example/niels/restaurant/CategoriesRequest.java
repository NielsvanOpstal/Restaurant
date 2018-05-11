package com.example.niels.restaurant;

import android.content.Context;
import android.util.Log;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }



    public CategoriesRequest(Context aContext) {

        // On create loads the context in the class wide variable
        context = aContext;

    }

    public void getCategories(Callback aActivity) {

        // Loads the activity in the class wide variable
        activity = aActivity;

        // Makes a request queue to get a JSONarray from resto.mrpog.nl containing the categories
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError aError) {

        // If response is error, give the error message back to the activity
        activity.gotCategoriesError(aError.getMessage());
    }

    @Override
    public void onResponse(JSONObject aResponse) {
        ArrayList<String> responses = new ArrayList<>();

        // If got a JSON back, try to put the categories in an arraylist and give it back to activity
        try {
            JSONArray response = aResponse.getJSONArray("categories");
            Log.d("Responses", response.toString());
            int length = response.length();
            for (int i = 0; i < length; i++ ){
                String category = response.getString(i);
                responses.add(category);
            }
            activity.gotCategories(responses);

            } catch(JSONException e){
            // If JOSNException is thrown, give back an error message to activity
            Log.d("er ging iets mis", e.toString());
        }

        }
}
