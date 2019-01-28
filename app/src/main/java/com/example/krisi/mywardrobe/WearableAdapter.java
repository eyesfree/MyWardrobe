package com.example.krisi.mywardrobe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WearableAdapter extends RecyclerView.Adapter<WearableAdapter.MyViewHolder> {

    private List<Wearable> wearables;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, type, state;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            type = (TextView) view.findViewById(R.id.type);
            state = (TextView) view.findViewById(R.id.state);
        }
    }

    public WearableAdapter(List<Wearable> wearables) {
        this.wearables = wearables;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.wearable_list_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Wearable wearable = wearables.get(position);
        myViewHolder.name.setText(wearable.getName());
        myViewHolder.type.setText(wearable.getType());
        myViewHolder.state.setText(wearable.getState());
    }

    @Override
    public int getItemCount() {
        return wearables.size();
    }
}
