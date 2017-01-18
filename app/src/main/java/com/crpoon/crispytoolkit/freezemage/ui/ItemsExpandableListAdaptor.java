package com.crpoon.crispytoolkit.freezemage.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.crpoon.crispytoolkit.R;
import com.crpoon.crispytoolkit.freezemage.data.Card;
import com.crpoon.crispytoolkit.freezemage.data.ItemGroup;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by crpoon on 2017-01-17.
 */
public class ItemsExpandableListAdaptor extends BaseExpandableListAdapter{

    private Activity context;
    private List<ItemGroup> itemGroups;

    public ItemsExpandableListAdaptor(Activity context, List<ItemGroup> itemGroups) {
        this.context = context;
        this.itemGroups = itemGroups;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Card> cards = itemGroups.get(groupPosition).getCards();
        return cards.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemGroups.get(groupPosition).getCards().size();
    }

    // Where the card is displayed
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        Card card = (Card) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.child_items, null);
        }

        TextView cardName = (TextView) view.findViewById(R.id.cardName);
        cardName.setText(card.getName().trim());

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemGroups.get(groupPosition);
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return itemGroups.size();
    }

    // Where the Group is displayed
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        ItemGroup itemGroup = (ItemGroup) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(itemGroup.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
