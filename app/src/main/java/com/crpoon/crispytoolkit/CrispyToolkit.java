package com.crpoon.crispytoolkit;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crpoon.crispytoolkit.freezemage.ui.FreezeMageActivity;


public class CrispyToolkit extends ActionBarActivity {

    private Button coinFlipperButton;

    private Button freezeMageButton;

    public void coinFlipperButton(View v) {
        startActivity(new Intent(this, CoinFlipper.class));
    }

    public void freezeMageButton(View v) {
        startActivity(new Intent(this, FreezeMageActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crispy_toolkit);
        coinFlipperButton = (Button) findViewById(R.id.coinFlipperButton);
        freezeMageButton = (Button) findViewById(R.id.freezeMageButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crispy_toolkit, menu);
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
