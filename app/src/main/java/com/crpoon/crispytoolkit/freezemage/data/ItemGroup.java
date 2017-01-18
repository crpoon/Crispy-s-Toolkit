package com.crpoon.crispytoolkit.freezemage.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crpoon on 2017-01-17.
 */
public class ItemGroup {

    private String name;
    private List<Card> cards = new ArrayList<>();

    public ItemGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
