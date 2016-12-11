package com.example.lakshita.bookstore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public FeedbackAdapter(Context context, int resource) {

        super(context, resource);
    }

    public void add(Feedback object) {
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
        FeedbackAdapter.FeedbackHolder feedbackHolder;

        // Recreate row if null
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_feedback, parent,false);

            feedbackHolder = new FeedbackAdapter.FeedbackHolder();
            feedbackHolder.tx_feedback_isbn = (TextView)row.findViewById(R.id.tx_feedback_isbn);
            feedbackHolder.tx_feedback_fText = (TextView)row.findViewById(R.id.tx_feedback_fText);
            feedbackHolder.tx_feedback_score = (TextView)row.findViewById(R.id.tx_feedback_score);
            feedbackHolder.tx_feedback_FID = (TextView)row.findViewById(R.id.tx_feedback_FID);
            row.setTag(feedbackHolder);
        }else {
            feedbackHolder=(FeedbackAdapter.FeedbackHolder)row.getTag();
        }
        Feedback feedback = (Feedback) this.getItem(position);
        feedbackHolder.tx_feedback_isbn.setText(feedback.getIsbn());
        feedbackHolder.tx_feedback_fText.setText(feedback.getfText());
        feedbackHolder.tx_feedback_score.setText(feedback.getScore());
        feedbackHolder.tx_feedback_FID.setText(feedback.getFID());

        return row;
    }

    //
    static class FeedbackHolder{
        TextView tx_feedback_isbn, tx_feedback_fText, tx_feedback_score, tx_feedback_FID;
    }
}
