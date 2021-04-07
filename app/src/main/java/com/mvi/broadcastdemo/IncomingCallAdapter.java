package com.mvi.broadcastdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IncomingCallAdapter extends RecyclerView.Adapter<IncomingCallAdapter.IncomingViewHolder> {

    private List<IncomingCalls> incomingCallsList;

    public IncomingCallAdapter(List<IncomingCalls> incomingCallsList) {
        this.incomingCallsList = incomingCallsList;
    }

    @NonNull
    @Override
    public IncomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incoming_view, parent, false);
        return new IncomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomingViewHolder holder, int position) {

        holder.ID.setText(Integer.toString(incomingCallsList.get(position).getId()));
        holder.INCOMING_NUMBER.setText(incomingCallsList.get(position).getIncomingNo());
    }

    @Override
    public int getItemCount() {
        return incomingCallsList.size();
    }

    public static class IncomingViewHolder extends RecyclerView.ViewHolder {

        TextView ID, INCOMING_NUMBER;

        public IncomingViewHolder(View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.text_id);
            INCOMING_NUMBER = itemView.findViewById(R.id.text_number);
        }
    }
}