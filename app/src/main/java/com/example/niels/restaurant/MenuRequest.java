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


public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    private String category;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    public MenuRequest(Context aContext, String aCategory) {

        // Fills the context and category classwide variables when MenuRequest is created
        context = aContext;
        category = aCategory;

    }

    public void getMenu(Callback aActivity) {

        // Fills the class wide variable activity
        activity = aActivity;

        // Creates a requestQueue and tries to receive the the menu items from the clicked category
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category=" + category, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError aError) {

        // If error response, give the error message back to the activity
        activity.gotMenuError(aError.getMessage());
    }

    @Override
    public void onResponse(JSONObject aResponse) {
        ArrayList<MenuItem> responses = new ArrayList<>();

        // If JSON received, fill responses with the menuItems received and give it back to activity
        try {
            JSONArray response = aResponse.getJSONArray("items");
            Log.d("Responses", response.toString());
            int length = response.length();
            for (int i = 0; i < length; i++ ){
                MenuItem menuItem = new MenuItem();
                JSONObject item = response.getJSONObject(i);
                menuItem.setName(item.getString("name"));
                menuItem.setDescription(item.getString("description"));
                menuItem.setCategory(item.getString("category"));
                menuItem.setPrice(item.getInt("price"));
                menuItem.setImageUrl(item.getString("image_url"));
                responses.add(menuItem);
            }
            activity.gotMenu(responses);

        } catch(JSONException e){

            // If JSONException was thrown, lig error
            Log.d("er ging iets mis", e.toString());
        }

    }
}
