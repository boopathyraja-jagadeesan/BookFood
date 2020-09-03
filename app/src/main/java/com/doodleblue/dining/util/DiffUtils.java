package com.doodleblue.dining.util;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

/**
 * Created by Boopathy Jagadeesan on 9/3/2020.
 */
public class DiffUtils extends DiffUtil.Callback {

    private List<ItemListData> foodItemList1;
    private List<ItemListData> foodItemList2;

    public DiffUtils(List<ItemListData> foodItemList1, List<ItemListData> foodItemList2) {
        this.foodItemList1 = foodItemList1;
        this.foodItemList2 = foodItemList2;
    }

    /**
     * Returns the size of the old list.
     *
     * @return The size of the old list.
     */
    @Override
    public int getOldListSize() {
        return foodItemList1 != null ? foodItemList1.size() : 0;
    }

    /**
     * Returns the size of the new list.
     *
     * @return The size of the new list.
     */
    @Override
    public int getNewListSize() {
        return foodItemList2 != null ? foodItemList2.size() : 0;
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * <p>
     * For example, if your items have unique ids, this method should check their id equality.
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return foodItemList2.get(newItemPosition).getItemName() == foodItemList1.get(oldItemPosition).getItemName();

    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * <p>
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     *                        oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return foodItemList2.get(newItemPosition).equals(foodItemList1.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        ItemListData newItem = foodItemList1.get(newItemPosition);
        ItemListData oldItem = foodItemList2.get(oldItemPosition);

        Bundle diff = new Bundle();

        if (oldItem.getQtyAdded() != newItem.getQtyAdded()) {
            diff.putInt("qtyAdded", oldItem.getQtyAdded());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
