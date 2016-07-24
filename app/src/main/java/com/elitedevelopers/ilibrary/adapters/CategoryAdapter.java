package com.elitedevelopers.ilibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elitedevelopers.ilibrary.R;

import java.util.ArrayList;

/**
 * Created by Nishad on 24-Jul-16.
 */
public class CategoryAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> categories;

    // constructor
    public CategoryAdapter(Context context, ArrayList<String> categories) {
        super(context, R.layout.custom_row, categories);
        this.context = context;
        this.categories = categories;
    }

    // view holder class
    private class ViewHolder {
        ImageView ivIcon;
        TextView tvName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            // inflate the custom row
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_row, null, true);

            // initialize the variables
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set contents to the views
        viewHolder.ivIcon.setImageResource(R.drawable.icon_category);
        viewHolder.tvName.setText(categories.get(position));

        return convertView;
    }

}
