package com.markfeldman.fabrizia.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.data.DataContract;
import com.markfeldman.fabrizia.data.Database;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public void readCocktailsFromFile(Context context){

        Database database = new Database(context);
        database.openWritable();
        Scanner sc = new Scanner(context.getResources().openRawResource(R.raw.cocktail_recipe));

        while (sc.hasNextLine()){
            ContentValues cv = new ContentValues();
            String cocktail = sc.nextLine();
            String [] seperated = cocktail.split("-");
            String cocktailName = seperated[0];
            String cocktailIngredients = seperated[1];
            cv.put(DataContract.CocktailData.COLUMN_COCKTAIL_NAME, cocktailName);
            cv.put(DataContract.CocktailData.COLUMN_INGREDIENTS,cocktailIngredients);
            database.insertRowToCocktail(cv);

        }
        database.close();
    }


    public void readRecipesFromFile(Context context){
        Database database = new Database(context);
        database.openWritable();
        Scanner sc = new Scanner(context.getResources().openRawResource(R.raw.food_recipes));

        while (sc.hasNextLine()){
            ContentValues cv = new ContentValues();
            String food_recipe = sc.nextLine();
            String [] seperated = food_recipe.split("-");
            String recipeName = seperated[0];
            String foodRecipeIngredients = seperated[1];
            Log.d("FileReader", " INSIDE READER = " + recipeName + " "  + foodRecipeIngredients);
            cv.put(DataContract.RecipeData.COLUMN_RECIPE_NAME, recipeName);
            cv.put(DataContract.RecipeData.COLUMN_RECIPE_INGREDIENTS,foodRecipeIngredients);
            database.insertRowToRecipes(cv);

        }
        database.close();
    }

}
