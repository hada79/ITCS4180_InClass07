package com.jasonhada.inclass07;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreadAdapter extends RecyclerView.Adapter<ThreadAdapter.ViewHolder> {
    ArrayList<Thread> threads;

    public ThreadAdapter(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView threadName;
        Thread thread;

        public ViewHolder(View itemView) {
            super(itemView);

            threadName = itemView.findViewById(R.id.textViewThreadItemName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("d", "clicked " + thread.getTitle());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.thread_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Thread thread = threads.get(i);

        viewHolder.threadName.setText(thread.getTitle());
        viewHolder.thread = thread;
    }

    @Override
    public int getItemCount() {
        return threads.size();
    }


}
