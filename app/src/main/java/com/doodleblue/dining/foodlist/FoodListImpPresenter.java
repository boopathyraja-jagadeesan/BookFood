package com.doodleblue.dining.foodlist;

import com.doodleblue.dining.util.ItemListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boopathy Jagadeesan on 9/3/2020.
 */
class FoodListImpPresenter implements IFoodListPresenter {
    private IFoodListView iFoodListView = null;

    public FoodListImpPresenter(IFoodListView iFoodListView) {
        this.iFoodListView = iFoodListView;
    }

    /**
     * Method to get the list of food items from repo.
     */
    @Override
    public void getItemList() {

        List<ItemListData> foodList = new ArrayList<>();
        foodList.add(new ItemListData("Margerrita", "Chilly& Cheese", 0, true, false, "8"));
        foodList.add(new ItemListData("IceCream", "Chocolate with toppings", 0, true, true, "4"));
        foodList.add(new ItemListData("Briyani", "Chicken", 0, true, false, "12"));
        foodList.add(new ItemListData("Curd Rice", "Curd, Rice and Spices", 0, true, false, "6"));
        foodList.add(new ItemListData("Tomato Rice", "Homemade ", 0, false, true, "6"));

        iFoodListView.showFoodList(foodList);


    }
}
