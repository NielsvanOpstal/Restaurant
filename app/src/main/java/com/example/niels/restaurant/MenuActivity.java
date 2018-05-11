package com.example.niels.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuactivity);
        String category = getIntent().getStringExtra("category");

        // Creates new MenuRequest and gives it the category received trough the intent
        MenuRequest request = new MenuRequest(this, category);
        request.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        // Finds listview, sets an adapter and onItemClickListener on it
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(new MenuAdapter(this, 0, menuItems));
        listView.setOnItemClickListener(new onItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {

        // If error was received, log error message
        Log.d("ERROR", message);
    }

    public class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Creates intent with the menuItem clicked to the menuActivity
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);
            intent.putExtra("menuItem", item);
            startActivity(intent);

        }
    }
}
