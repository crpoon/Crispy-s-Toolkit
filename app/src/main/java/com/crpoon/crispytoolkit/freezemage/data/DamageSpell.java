package com.crpoon.crispytoolkit.freezemage.data;

/**
 * Created by crpoon on 2017-01-17.
 */
public class DamageSpell {

    private String name;
    private int baseDamage;

    public DamageSpell(String name, int baseDamage) {
        this.name = name;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
