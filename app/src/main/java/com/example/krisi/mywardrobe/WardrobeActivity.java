package com.example.krisi.mywardrobe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WardrobeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WearableAdapter wearableAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Wearable> wearablesContent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wardrobe);
        recyclerView = (RecyclerView) findViewById(R.id.items);

        wearableAdapter = new WearableAdapter(wearablesContent);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(wearableAdapter);

        prepareWearablesContent();
    }

    private void prepareWearablesContent() {
        Wearable jeans = new Wearable("Enge Jeans BG", "Hose", "getragen");
        Wearable sweater = new Wearable("Orangene Thermo Puli", "Bluse", "getragen");
        Wearable dress = new Wearable("Purple Long Dress", "Kleid", "frisch");

        wearablesContent.add(jeans);
        wearablesContent.add(sweater);
        wearablesContent.add(dress);

        wearableAdapter.notifyDataSetChanged();
    }
}
