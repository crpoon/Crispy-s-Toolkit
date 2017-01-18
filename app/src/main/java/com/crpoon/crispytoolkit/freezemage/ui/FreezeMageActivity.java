package com.crpoon.crispytoolkit.freezemage.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;


public class FreezeMageActivity extends AppCompatActivity {

    // Constants
    protected final String DAMAGE_SPELLS = "Direct Damage Spells";
    protected final String SPELL_DAMAGE_MINIONS = "Spell Damage Minions";
    protected final String TEXT_COLOR = "#FFFFFFFF";

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
        // TODO: To implement JSON parser and import real data

        // First, we need to add our Groups
        ItemGroup damageSpellGroup = new ItemGroup(DAMAGE_SPELLS);
        ItemGroup spellDamageGroup = new ItemGroup(SPELL_DAMAGE_MINIONS);
        // TODO: Add cards to each group

        // Start of temporary code
        List<Card> damageSpells = new ArrayList<>();
        Card fireball = new DamageSpell("Fireball", 6);
        damageSpells.add(fireball);
        Card frostbolt = new DamageSpell("Frostbolt", 3);
        damageSpells.add(frostbolt);

        List<Card> spellDamageMinions = new ArrayList<>();
        Card bloodmageThalnos = new SpellDamageMinion("Bloodmage Thalnos", 1);
        spellDamageMinions.add(bloodmageThalnos);

        damageSpellGroup.setCards(damageSpells);
        spellDamageGroup.setCards(spellDamageMinions);
        // End of temporary code

        itemGroups.add(damageSpellGroup);
        itemGroups.add(spellDamageGroup);
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
