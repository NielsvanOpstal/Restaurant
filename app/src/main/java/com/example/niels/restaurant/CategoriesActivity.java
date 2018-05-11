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
    public void gotCategories(ArrayList<String> categories) {
        ArrayList<String> listItems = categories;
        ListView listView = findViewById(R.id.categoriesListView);
        listView.setAdapter(new CategoryAdapter(this, 0, listItems));
        listView.setOnItemClickListener(new onItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("TAG", message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);
    }

    private class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class );
            intent.putExtra("category", parent.getItemAtPosition(position).toString());
            startActivity(intent);
        }
    }
}
