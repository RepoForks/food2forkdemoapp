package com.food2fork.demoapp.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.food2fork.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recipesItemContainer)
    RelativeLayout recipesItemContainer;

    @BindView(R.id.icon)
    ImageView icon;

    @BindView(R.id.recipesTitle)
    TextView recipesTitle;

    @BindView(R.id.chevron)
    ImageView chevron;

    public RecipesItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public RelativeLayout getRecipesItemContainer() {
        return recipesItemContainer;
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getRecipesTitle() {
        return recipesTitle;
    }

    public ImageView getChevron() {
        return chevron;
    }
}
