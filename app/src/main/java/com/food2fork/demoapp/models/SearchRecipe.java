package com.food2fork.demoapp.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRecipe {

	@SerializedName("recipes")
	@Expose
	private List<SearchRecipeItem> recipes;

	@SerializedName("count")
	@Expose
	private int count;

	public void setRecipes(List<SearchRecipeItem> recipes){
		this.recipes = recipes;
	}

	public List<SearchRecipeItem> getRecipes(){
		return recipes;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}
}