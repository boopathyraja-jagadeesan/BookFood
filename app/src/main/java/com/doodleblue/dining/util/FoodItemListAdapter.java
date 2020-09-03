package com.doodleblue.dining.util;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.doodleblue.dining.R;

import java.util.List;

import static android.view.View.GONE;


public class FoodItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemListData> foodItemList = null;
    private int viewType = -1;
    private Context context = null;
    private FoodItemListener foodItemListener = null;

    public FoodItemListAdapter(Context context, int viewType, List<ItemListData> foodItemList, FoodItemListener foodItemListener) {
        this.viewType = viewType;
        this.foodItemList = foodItemList;
        this.context = context;
        this.foodItemListener = foodItemListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View layoutView = null;
        switch (viewType) {
            case AppConstant.ITEM_VIEW:
                layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_recyclerview_food_items, null);
                return new FoodItemViewHolder(layoutView);
            case AppConstant.CART_VIEW:
                layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_recyclerview_food_items, null);
                return new FoodItemViewHolder(layoutView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final FoodItemViewHolder foodItemViewHolder = (FoodItemViewHolder) viewHolder;
        final ItemListData itemListData = foodItemList.get(position);
        foodItemViewHolder.lblItemName.setText(itemListData.getItemName());
        foodItemViewHolder.lblItemDesc.setText(itemListData.getItemDesc());
        foodItemViewHolder.lblItemRate.setText(context.getString(R.string.currency) + "\t" + itemListData.getItemRate());

        if (itemListData.getQtyAdded() < 1) {
            foodItemViewHolder.consQty.setVisibility(GONE);
            foodItemViewHolder.btnAddQty.setVisibility(View.VISIBLE);
        } else {
            foodItemViewHolder.consQty.setVisibility(View.VISIBLE);
            foodItemViewHolder.btnAddQty.setVisibility(GONE);
            foodItemViewHolder.lblitemCnt.setText(String.valueOf(itemListData.getQtyAdded()));
        }

        if (itemListData.isD()) {
            foodItemViewHolder.lblD.setVisibility(View.VISIBLE);
        } else {
            foodItemViewHolder.lblD.setVisibility(GONE);
        }

        foodItemViewHolder.lblIncQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodCount = foodItemList.get(foodItemViewHolder.getAdapterPosition()).getQtyAdded();
                prodCount = prodCount + 1;
                foodItemList.get(foodItemViewHolder.getAdapterPosition()).setQtyAdded(prodCount);
                notifyItemChanged(foodItemViewHolder.getAdapterPosition());
                foodItemListener.updateCart();

            }
        });

        foodItemViewHolder.lblDecQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodCount = foodItemList.get(foodItemViewHolder.getAdapterPosition()).getQtyAdded();
                prodCount = prodCount - 1;
                foodItemList.get(foodItemViewHolder.getAdapterPosition()).setQtyAdded(prodCount);
                notifyItemChanged(foodItemViewHolder.getAdapterPosition());
                foodItemListener.updateCart();
            }
        });

        foodItemViewHolder.btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prodCount = 1;
                foodItemList.get(foodItemViewHolder.getAdapterPosition()).setQtyAdded(prodCount);
                notifyItemChanged(foodItemViewHolder.getAdapterPosition());
                foodItemListener.updateCart();

            }
        });

        if (viewType == AppConstant.ITEM_VIEW) {
            foodItemViewHolder.imgChat.setVisibility(GONE);
            foodItemViewHolder.lineDivider.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        } else if (viewType == AppConstant.CART_VIEW) {
            foodItemViewHolder.imgChat.setVisibility(View.VISIBLE);
            foodItemViewHolder.lineDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        final FoodItemViewHolder foodItemViewHolder = (FoodItemViewHolder) holder;

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("qtyAdded")) {
                    foodItemViewHolder.lblitemCnt.setText(String.valueOf(foodItemList.get(position).getQtyAdded()));
                }
            }
        }
    }


    @Override
    public int getItemViewType(int position) {

        return viewType;
    }

    @Override
    public int getItemCount() {
        return foodItemList != null ? foodItemList.size() : 0;
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView lblItemName, lblItemDesc, lblN, lblD, lblItemRate, lblIncQty, lblDecQty, lblRate, lblitemCnt;
        Button btnAddQty;
        ConstraintLayout consQty;
        ImageView imgChat;
        View lineDivider;


        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            lblItemName = itemView.findViewById(R.id.lblItemName);
            lblItemDesc = itemView.findViewById(R.id.lblItemDesc);
            lblN = itemView.findViewById(R.id.lblN);
            lblD = itemView.findViewById(R.id.lblD);
            lblItemRate = itemView.findViewById(R.id.lblItemRate);
            lblIncQty = itemView.findViewById(R.id.lblIncreaseCnt);
            lblDecQty = itemView.findViewById(R.id.lblDecreaseCnt);
            lblRate = itemView.findViewById(R.id.lblItemRate);
            consQty = itemView.findViewById(R.id.consQty);
            imgChat = itemView.findViewById(R.id.imgChat);
            lineDivider = itemView.findViewById(R.id.viewDivider);
            btnAddQty = itemView.findViewById(R.id.btnAdd);
            lblitemCnt = itemView.findViewById(R.id.lblitemCnt);


        }
    }


    public interface FoodItemListener {
        /**
         * Method to update cart details (qty and price)
         */
        void updateCart();

    }

    public void onNewData(List<ItemListData> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtils(newData,foodItemList ));
        diffResult.dispatchUpdatesTo(this);
        this.foodItemList.clear();
        this.foodItemList.addAll(newData);
    }
}
