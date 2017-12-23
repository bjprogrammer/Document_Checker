package com.example.bobby.trafficsubscriber.utility;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobby.trafficsubscriber.R;

import java.util.List;

public class ticketadapter extends RecyclerView.Adapter<ticketadapter.MyViewHolder> {
    private List<ticketutility> ticketList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, time, genre,issue,id,location;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            time = (TextView) view.findViewById(R.id.time);
            issue = (TextView) view.findViewById(R.id.issue);
            id = (TextView) view.findViewById(R.id.id);
            location = (TextView) view.findViewById(R.id.location);
        }
    }

    public ticketadapter(List<ticketutility> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ticketutility movie = ticketList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.time.setText(movie.getTime());
        holder.issue.setText(movie.getIssue());
        holder.id.setText(movie.getId());
        holder.location.setText(movie.getLocation());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }
}
