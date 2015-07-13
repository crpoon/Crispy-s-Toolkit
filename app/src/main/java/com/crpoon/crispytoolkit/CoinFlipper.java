package com.crpoon.crispytoolkit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class CoinFlipper extends ActionBarActivity {

    private int times;

    private TextView timesText;

    private TextView coinFlipText;

    public void flipACoin(View v) {
        if (produceRandomBoolean()) {
            coinFlipText.setText("Heads");
        } else {
            coinFlipText.setText("Tails");
        }
        times++;
        setUiTimesText();
    }

    private boolean produceRandomBoolean() {
        return Math.random() < 0.5;
    }

    private void setUiTimesText() {
        timesText.setText(getResources().getString(R.string.coin_flip_times_text) + " " + times);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flipper);

        times = 0;
        coinFlipText = (TextView) findViewById(R.id.coinFlipText);
        timesText = (TextView) findViewById(R.id.coinTimesText);

        setUiTimesText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coin_flipper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
