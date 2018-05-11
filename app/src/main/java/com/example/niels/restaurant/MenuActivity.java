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

        MenuRequest request = new MenuRequest(this, category);
        request.getMenu(this);
    }

    @Override
    public void gotCategories(ArrayList<MenuItem> aMenuItems) {
        ArrayList<MenuItem> menuItems = aMenuItems;
        ListView listView = findViewById(R.id.menuList);
        listView.setAdapter(new MenuAdapter(this, 0, menuItems));
        listView.setOnItemClickListener(new onItemClickListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("ERROR", "Er is een error voor gekomen");
    }

    public class onItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("TAG", "onItemClick: ");
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);
            Log.d("HOI", item.toString());
            intent.putExtra("menuItem", item);
            startActivity(intent);

        }
    }
}
