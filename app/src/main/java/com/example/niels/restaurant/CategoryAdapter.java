package com.example.niels.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CategoryAdapter extends ArrayAdapter<String> {
    ArrayList<String> categories;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
        super(context, resource, objects);

        // Fills the class wide arraylist with the categories received
        categories = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflate categorylistitem.xml when converview is null
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categorylistitem, parent, false);
        }

        // Fills the categorylistitem.xml and returns it
        TextView textView = convertView.findViewById(R.id.categoryView);
        textView.setText(categories.get(position));
        return convertView;
    }
}
