package com.food2fork.demoapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeResponse{

	@SerializedName("recipe")
	@Expose
	private Recipe recipe;

	public void setRecipe(Recipe recipe){
		this.recipe = recipe;
	}

	public Recipe getRecipe(){
		return recipe;
	}
}