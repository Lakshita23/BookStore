package com.example.lakshita.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lakshita on 12/10/2016.
 */

public class CustomAdapterStats extends ArrayAdapter<String> {
    private final Context context;
    private final HashMap<Integer, List<String>> values;
    private int selection;

    public CustomAdapterStats(Context context, HashMap<Integer,List<String>> values) {
        super(context, R.layout.list_row, values.get(0));
        this.context = context;
        this.values = values;

        setSelection(1);
        System.out.println("VALUES:"+values);
    }

    @Override
    public int getCount(){
        return values.size();
    }
    public void setSelection(int i){
        if (i==1)
            selection = 3;
        else if (i==2)
            selection = 2;
        else
            selection = 4;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_row, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.row_title);
        TextView copy = (TextView) rowView.findViewById(R.id.row_copy);
        TextView isbn = (TextView) rowView.findViewById(R.id.row_isbn);
        TextView price = (TextView) rowView.findViewById(R.id.row_price);
        title.setText(values.get(position).get(selection));
        copy.setText(values.get(position).get(0));
        isbn.setText(values.get(position).get(1));
        price.setText("$"+values.get(position).get(7));

        return rowView;
    }
}