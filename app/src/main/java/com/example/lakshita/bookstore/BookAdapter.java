package com.example.lakshita.bookstore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends ArrayAdapter{

    List list = new ArrayList();

    public BookAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Books object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {

        // Get item position in the list
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        BookHolder bookHolder;

        // Recreate row if null
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent,false);

            bookHolder = new BookHolder();
            bookHolder.tx_isbn = (TextView)row.findViewById(R.id.tx_isbn);
            bookHolder.tx_title = (TextView)row.findViewById(R.id.tx_title);
            bookHolder.tx_copies = (TextView)row.findViewById(R.id.tx_copies);
            row.setTag(bookHolder);
        }else {
            bookHolder=(BookHolder)row.getTag();
        }
        Books books = (Books)this.getItem(position);
        bookHolder.tx_isbn.setText(books.getIsbn());
        bookHolder.tx_title.setText(books.getTitle());
        bookHolder.tx_copies.setText(books.getCopies());

        return row;
    }

    //
    static class BookHolder{
        TextView tx_isbn, tx_title, tx_copies;
    }
}
