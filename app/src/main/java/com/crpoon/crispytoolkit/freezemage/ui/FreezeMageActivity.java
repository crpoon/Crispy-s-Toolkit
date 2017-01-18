package com.crpoon.crispytoolkit.freezemage.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.crpoon.crispytoolkit.R;
import com.crpoon.crispytoolkit.freezemage.data.Card;
import com.crpoon.crispytoolkit.freezemage.data.DamageSpell;
import com.crpoon.crispytoolkit.freezemage.data.ItemGroup;
import com.crpoon.crispytoolkit.freezemage.data.SpellDamageMinion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class FreezeMageActivity extends AppCompatActivity {

    // Constants
    protected final String LOG_TAG = this.getClass().getSimpleName();
    protected final String DAMAGE_SPELLS = "Direct Damage Spells";
    protected final String SPELL_DAMAGE_MINIONS = "Spell Damage Minions";

    private ItemsExpandableListAdaptor listAdaptor;
    private ExpandableListView listView;

    private List<ItemGroup> itemGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeze_mage);

        // Initialize the Dataset
        loadData();

        // Setup the ListView
        listView = (ExpandableListView) findViewById(R.id.cardList);
        listAdaptor = new ItemsExpandableListAdaptor(FreezeMageActivity.this, itemGroups);
        listView.setAdapter(listAdaptor);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) listAdaptor.getChild(groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG).show();

                return true;
            }
        });

        // Expand All the Item Groups
        expandOrCollapseAll(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_freeze_mage, menu);
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

    private void loadData() {
        // First, we need to add our Groups
        ItemGroup damageSpellGroup = new ItemGroup(DAMAGE_SPELLS);
        ItemGroup spellDamageGroup = new ItemGroup(SPELL_DAMAGE_MINIONS);

        // Second, we need to get our cards from the JSON data
        List<Card> damageSpells = getDamageSpells();
        List<Card> spellDamageMinions = getSpellDamageMinions();

        // Third, we have to add the cards to the parent groups
        damageSpellGroup.setCards(damageSpells);
        spellDamageGroup.setCards(spellDamageMinions);

        itemGroups.add(damageSpellGroup);
        itemGroups.add(spellDamageGroup);
    }

    private List<Card> getDamageSpells() {
        List<Card> damageSpells = new ArrayList<>();
        String jsonString = getJsonFromResources(R.raw.damage_spell);
        try {
            JSONArray spellArr = new JSONArray(jsonString);
            for (int i = 0; i < spellArr.length(); i++) {
                JSONObject jsonSpell = spellArr.getJSONObject(i);
                String name = jsonSpell.getString("name");
                int damage = jsonSpell.getInt("baseDamage");
                DamageSpell spell = new DamageSpell(name, damage);
                damageSpells.add(spell);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Exception when parsing JSON", e);
        }
        return damageSpells;
    }

    private List<Card> getSpellDamageMinions() {
        List<Card> minions = new ArrayList<>();
        String jsonString = getJsonFromResources(R.raw.spell_damage);
        try {
            JSONArray minionArr = new JSONArray(jsonString);
            for (int i = 0; i < minionArr.length(); i++) {
                JSONObject jsonSpell = minionArr.getJSONObject(i);
                String name = jsonSpell.getString("name");
                int spellDamage = jsonSpell.getInt("spellDamage");
                SpellDamageMinion minion = new SpellDamageMinion(name, spellDamage);
                minions.add(minion);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Exception when parsing JSON", e);
        }
        return minions;
    }

    private String getJsonFromResources(int id) {
        InputStream is = getResources().openRawResource(id);
        Writer sw = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                sw.write(line);
                line = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Exception when reading JSON", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Exception when reading JSON", e);
        }

        return sw.toString();
    }

    private void expandOrCollapseAll(boolean expand) {
        int count = listAdaptor.getGroupCount();
        for (int i = 0; i < count; i++) {
            if (expand) {
                listView.expandGroup(i);
            } else {
                listView.collapseGroup(i);
            }
        }
    }
}
