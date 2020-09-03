package com.doodleblue.dining.foodlist;

import com.doodleblue.dining.util.ItemListData;

import java.util.List;

/**
 * Created by Boopathy Jagadeesan on 9/3/2020.
 */
public interface IFoodListView {

    /**
     * Method to show list of food items.
     * @param foodList - List of food items.
     */
    void showFoodList(List<ItemListData> foodList);

}
