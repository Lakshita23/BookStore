package com.example.lakshita.bookstore;

/**
 * Created by Lakshita on 12/8/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {
    Context context;
    HashMap<Integer,ArrayList<String>> books;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, HashMap<Integer,ArrayList<String>> books) {
        this.context = applicationContext;
        this.books = books;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.gridview, null);
        TextView title = (TextView) view.findViewById(R.id.titleview);
        TextView author = (TextView) view.findViewById(R.id.authorview);
        title.setText(books.get(i).get(1));
        author.setText(books.get(i).get(2));
        return view;
    }
}
