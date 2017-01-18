package com.crpoon.crispytoolkit.freezemage.data;

/**
 * Created by crpoon on 2017-01-17.
 */
public class SpellDamageMinion implements Card {

    private String name;
    private int spellDamage;

    public SpellDamageMinion(String name, int spellDamage) {
        this.name = name;
        this.spellDamage = spellDamage;
    }

    public String getName() {
        return name;
    }

    public int getSpellDamage() {
        return spellDamage;
    }
}
