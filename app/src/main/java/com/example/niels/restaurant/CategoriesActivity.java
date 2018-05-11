package com.example.niels.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Creates a new Categories request and requests the categories
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // if categories received: sets an adapter and an onItemClickListener to the list view
        ListView listView = findViewById(R.id.categoriesListView);
        listView.setAdapter(new CategoryAdapter(this, 0, categories));
        listView.setOnItemClickListener(new onItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {

        // If error received, print log error
        Log.d("TAG", message);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // When item is clicked, go to menu activity for the category clicked
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class );
            intent.putExtra("category", parent.getItemAtPosition(position).toString());
            startActivity(intent);
        }
    }
}
