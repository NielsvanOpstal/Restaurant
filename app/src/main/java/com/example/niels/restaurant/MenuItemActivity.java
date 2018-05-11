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

        // Gets the serializable form the intent and casts it to a menuItem
        MenuItem menuItem = (MenuItem) getIntent().getSerializableExtra("menuItem");

        // Gets all the text and imageviews and fills them with correct data from the menuItem
        TextView name = findViewById(R.id.menuItemName);
        TextView description = findViewById(R.id.menuItemDescription);
        TextView price = findViewById(R.id.menuItemPrice);
        ImageView image = findViewById(R.id.menuItemImage);

        name.setText(menuItem.getName());
        description.setText(menuItem.getDescription());
        price.setText("€" + Float.toString(menuItem.getPrice()));

        // Picasso is used to set an image form an URL
        Picasso.get().load(menuItem.getImageUrl()).into(image);


    }
}
