package com.doodleblue.dining.cart;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doodleblue.dining.R;
import com.doodleblue.dining.util.AppConstant;
import com.doodleblue.dining.util.FoodItemListAdapter;
import com.doodleblue.dining.util.ItemListData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements FoodItemListAdapter.FoodItemListener, View.OnClickListener {

    private TextView lblCartValue, lblShowMore;
    private ImageView imgCartBack;
    private RecyclerView itemListRecyclerView;


    Bundle args = null;
    private List<ItemListData> foodItemList = null, subItemList = null;
    FoodItemListAdapter foodItemListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getIntentData();
        initView();
    }

    /**
     * Method to initialize the views.
     */
    private void initView() {
        lblCartValue = findViewById(R.id.lblAmount);
        imgCartBack = findViewById(R.id.imgBack);
        itemListRecyclerView = findViewById(R.id.itemListRecyclerView);
        lblShowMore = findViewById(R.id.lblShowMore);
        lblShowMore.setPaintFlags(lblShowMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        lblShowMore.setOnClickListener(this);
        imgCartBack.setOnClickListener(this);
        initAdapter();
        updateCart();

    }

    /**
     * Method to set adapter
     */
    private void initAdapter() {
        if (foodItemList != null && foodItemList.size() > 0) {
           /* if (foodItemList.size() > 2) {
                lblShowMore.setVisibility(View.VISIBLE);
                foodItemListAdapter = new FoodItemListAdapter(getApplicationContext(), AppConstant.CART_VIEW, foodItemList.subList(0,2), this);

            } else {*/
                lblShowMore.setVisibility(View.GONE);
                foodItemListAdapter = new FoodItemListAdapter(getApplicationContext(), AppConstant.CART_VIEW, foodItemList, this);

//            }
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            itemListRecyclerView.setLayoutManager(layoutManager);
            itemListRecyclerView.setAdapter(foodItemListAdapter);
            itemListRecyclerView.setNestedScrollingEnabled(false);
        }
    }


    /**
     * Method to get the intent data
     */
    private void getIntentData() {
        args = getIntent().getExtras();

        if (args != null) {

            if (args.containsKey(AppConstant.ITEM_DATA)) {
                foodItemList = (List<ItemListData>) args.getSerializable(AppConstant.ITEM_DATA);
            }
        }
    }

    /**
     * Method to update cart details
     */
    @Override
    public void updateCart() {
        float cartPrice = 0f;
        for (int counter = 0; counter < foodItemList.size(); counter++) {
            cartPrice = cartPrice + foodItemList.get(counter).getQtyAdded() * Float.parseFloat(foodItemList.get(counter).getItemRate());
        }
        lblCartValue.setText(getString(R.string.currency) + "\t" + String.valueOf(cartPrice));

    }

    @Override
    public void onBackPressed() {

        Intent listIntent = new Intent();
        listIntent.putExtra(AppConstant.ITEM_DATA, (Serializable) foodItemList);
        setResult(AppConstant.RESULT_CODE, listIntent);
        finish();//finishing activity
    }


    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgBack:
                onBackPressed();
                break;

            case R.id.lblShowMore:

                showAllItems();


                break;
        }
    }

    /**
     * Method to update the adapter.
     */
    private void showAllItems() {
        lblShowMore.setVisibility(View.GONE);
        if (foodItemListAdapter != null) {
            foodItemListAdapter.notifyDataSetChanged();
        }


    }

}
