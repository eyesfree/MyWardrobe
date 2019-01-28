package com.example.krisi.mywardrobe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private TextView welcomeMessage;
    private Button next;
    private EditText name;
    private boolean firstClick;
    private TextView date;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private WearableAdapter wearableAdapter;
    private List<Wearable> wearablesContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState war null");
        } else {
            Log.d(TAG, "Wurde vor "
                    + (System.currentTimeMillis() - savedInstanceState.getLong(TAG))
                    + " Millisekunden beendet");
        }

        setContentView(R.layout.activity_main);

        welcomeMessage = (TextView) findViewById(R.id.message);
        next = (Button) findViewById(R.id.next);
        name = (EditText) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        date.setText(currentDateTimeString);
        recyclerView = (RecyclerView) findViewById(R.id.items);

        welcomeMessage.setText(R.string.willkommen);
        next.setText(R.string.weiter);
        next.setEnabled(false); // is enabled when there is a name entered by the user
        firstClick = true;

        next.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(firstClick) {
                    welcomeMessage.setText(getString(R.string.hallo, name.getText()));
                    welcomeMessage.append("\n Your Hobby is " + getResources().getString(R.string.hobby));
                    welcomeMessage.append("\n Your Profession is " + getResources().getString(R.string.profession));

                    name.setVisibility(View.INVISIBLE);
                    next.setText(R.string.fertig);
                    firstClick = false;
                } else {
                    finish();
                }
            }
        });

        wearableAdapter = new WearableAdapter(wearablesContent);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(wearableAdapter);

        prepareWearablesContent();
        // listening for Enter Press
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(next.isEnabled()) {
                    next.performClick();
                }
                return false;
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable name) {
                next.setEnabled(name.length() > 0);
            }
        });
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
