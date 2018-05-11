package com.example.niels.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MenuAdapter extends ArrayAdapter<MenuItem> {
    ArrayList<MenuItem> menuItems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);

        // Fills the classwide arrayList
        menuItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // If convertview is null, inflate menuListItem.xml
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menulistitem, parent, false);
        }

        // Gets all the text and imageViews and fills them with data received
        TextView menuName = convertView.findViewById(R.id.MenuName);
        TextView menuPrice = convertView.findViewById(R.id.MenuPrice);
        TextView menuDescription = convertView.findViewById(R.id.MenuDescription);
        ImageView menuImage = convertView.findViewById(R.id.MenuImage);
        menuName.setText(menuItems.get(position).getName());
        menuPrice.setText("€" + Float.toString(menuItems.get(position).getPrice()));
        menuDescription.setText(menuItems.get(position).getDescription());

        // Picasso is used to set an image from an url
        Picasso.get().load(menuItems.get(position).getImageUrl()).into(menuImage);

        return convertView;
    }
}
