package com.doodleblue.dining.foodlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doodleblue.dining.R;
import com.doodleblue.dining.cart.CartActivity;
import com.doodleblue.dining.util.AppConstant;
import com.doodleblue.dining.util.FoodItemListAdapter;
import com.doodleblue.dining.util.ItemListData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity implements IFoodListView, FoodItemListAdapter.FoodItemListener {

    private RecyclerView foodListRecyclerView = null;
    private ConstraintLayout consViewCart = null;
    private TextView lblViewCart = null;
    private List<ItemListData> foodItemList = null, selectedItem = null;

    private IFoodListPresenter foodListImpPresenter = null;
    private FoodItemListAdapter foodItemListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        foodListRecyclerView = findViewById(R.id.itemListRecycler);
        consViewCart = findViewById(R.id.consViewCart);
        lblViewCart = findViewById(R.id.lblViewCart);

        consViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null && selectedItem.size() > 0) {
                    Intent cartIntent = new Intent(FoodListActivity.this, CartActivity.class);
                    cartIntent.putExtra(AppConstant.ITEM_DATA, (Serializable) selectedItem);
                    startActivityForResult(cartIntent, AppConstant.REQUEST_CODE);
                } else {
                    Toast.makeText(FoodListActivity.this, getString(R.string.pls_select_one_product), Toast.LENGTH_SHORT).show();
                }

            }
        });

        foodListImpPresenter = new FoodListImpPresenter(this);
        foodListImpPresenter.getItemList();
    }

    @Override
    public void showFoodList(List<ItemListData> foodList) {
        foodItemList = foodList;
        if (foodList != null && foodList.size() > 0) {
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            foodListRecyclerView.setLayoutManager(layoutManager);
            foodItemListAdapter = new FoodItemListAdapter(getApplicationContext(), AppConstant.ITEM_VIEW, foodItemList, this);
            foodListRecyclerView.setAdapter(foodItemListAdapter);
            foodListRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    @Override
    public void updateCart() {
        if (foodItemList != null) {
            int prodCount = 0;
            selectedItem = new ArrayList<>();
            for (int counter = 0; counter < foodItemList.size(); counter++) {
                if (foodItemList.get(counter).getQtyAdded() > 0) {
                    prodCount = prodCount + foodItemList.get(counter).getQtyAdded();
                    selectedItem.add(foodItemList.get(counter));
                }
            }
            lblViewCart.setText(getString(R.string.view_cart).concat(String.format(getString(R.string.items), String.valueOf(prodCount))));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstant.REQUEST_CODE && resultCode == AppConstant.RESULT_CODE) {
            updateList((List<ItemListData>) data.getSerializableExtra(AppConstant.ITEM_DATA));

        }
    }

    private void updateList(List<ItemListData> cartItemList) {
        if (cartItemList == null) {
            return;
        }
        for (int cartCount = 0; cartCount < cartItemList.size(); cartCount++) {
            for (int itemsCount = 0; itemsCount < foodItemList.size(); itemsCount++) {
                if (cartItemList.get(cartCount).getItemName().equalsIgnoreCase(foodItemList.get(itemsCount).getItemName())) {
                    foodItemList.get(itemsCount).setQtyAdded(cartItemList.get(cartCount).getQtyAdded());
                }
            }
        }
        updateCart();
        if (foodItemListAdapter != null) {
            foodItemListAdapter.notifyDataSetChanged();
        }


    }
}