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
public class AuthorAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> authors;

    // constructor
    public AuthorAdapter(Context context, ArrayList<String> authors) {
        super(context, R.layout.custom_row, authors);
        this.context = context;
        this.authors = authors;
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
        viewHolder.ivIcon.setImageResource(R.drawable.icon_author);
        viewHolder.tvName.setText(authors.get(position));

        return convertView;
    }

}
