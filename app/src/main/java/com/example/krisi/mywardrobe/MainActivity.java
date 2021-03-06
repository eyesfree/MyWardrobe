package com.example.krisi.mywardrobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private TextView welcomeMessage;
    private Button next;
    private EditText name;
    private boolean firstClick;
    private TextView date;
    private Button showContextMenu;
    private TextView menuText;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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
        showContextMenu = (Button) findViewById(R.id.show);
        menuText = (TextView) findViewById(R.id.menuText);

        welcomeMessage.setText(R.string.willkommen);
        next.setText(R.string.weiter);
        next.setEnabled(false); // is enabled when there is a name entered by the user
        firstClick = true;

        registerForContextMenu(showContextMenu);

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
                    // finish();
                    Intent goToWardrobe;
                    goToWardrobe = new Intent(MainActivity.this, WardrobeActivity.class);
                    startActivity(goToWardrobe);
                }
            }
        });


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

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.optionsmenu, menu);
        }

        public boolean onContextItemSelected(MenuItem item) {
            switch(item.getItemId()) {
                case R.id.item1:
                    menuText.setText(item.getTitle());
                    return true;
                case R.id.item2:
                    menuText.setText(item.getTitle());
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        }

}
