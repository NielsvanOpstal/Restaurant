package com.example.niels.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuitemactivity);

        Intent intent = getIntent();
        MenuItem menuItem = (MenuItem) intent.getSerializableExtra("menuItem");
        TextView name = findViewById(R.id.menuItemName);
        TextView description = findViewById(R.id.menuItemDescription);
        TextView price = findViewById(R.id.menuItemPrice);
        ImageView image = findViewById(R.id.menuItemImage);

        name.setText(menuItem.getName());
        description.setText(menuItem.getDescription());
        price.setText("€" + Float.toString(menuItem.getPrice()));
        Picasso.get().load(menuItem.getImageUrl()).into(image);


    }
}
