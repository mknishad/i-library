package com.elitedevelopers.ilibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by Nishad on 23-Jul-16.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    private ArrayList<Book> books;
    private Context context;

    // constructor
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, R.layout.custom_row, books);
        this.context = context;
        this.books = books;
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
        viewHolder.ivIcon.setImageResource(R.drawable.icon_book);
        viewHolder.tvName.setText(books.get(position).getBookName());

        return convertView;
    }
}
