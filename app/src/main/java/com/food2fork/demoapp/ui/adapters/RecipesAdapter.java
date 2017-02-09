package com.food2fork.demoapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.food2fork.demoapp.R;
import com.food2fork.demoapp.models.SearchRecipeItem;
import com.food2fork.demoapp.ui.viewholders.RecipesItemViewHolder;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SearchRecipeItem> recipesItems;
    private RecipesListener recipesListener;

    public interface RecipesListener {
        void onRecipesClicked(SearchRecipeItem item);
    }

    public RecipesAdapter(Context context, List<SearchRecipeItem> items) {
        this.context = context;
        this.recipesItems = items;
    }

    public void setListener(RecipesListener listener) {
        recipesListener = listener;
    }

    private void onRecipesClicked(SearchRecipeItem item) {
        if (recipesListener != null) {
            recipesListener.onRecipesClicked(item);
        }
    }

    public SearchRecipeItem getItemAtPosition(int position) {
        return recipesItems.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipes_item, parent, false);
        return new RecipesItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecipesItemViewHolder viewHolder = (RecipesItemViewHolder) holder;
        final SearchRecipeItem item = recipesItems.get(position);

        viewHolder.getRecipesTitle().setText(item.getTitle());
        viewHolder.getRecipesItemContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecipesClicked(item);
            }
        });

        Glide
                .with(context)
                .load(item.getImageUrl())
                .into(viewHolder.getIcon());
    }

    @Override
    public int getItemCount() {
        return recipesItems.size();
    }

    @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        recipesListener = null;
    }
}
