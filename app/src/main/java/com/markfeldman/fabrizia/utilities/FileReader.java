package com.markfeldman.fabrizia.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.data.DataContract;

import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public void readRecipesFromFile(Context context){
        ArrayList<String[]> cocktailsAndIngredients = new ArrayList<>();
        Scanner sc = new Scanner(context.getResources().openRawResource(R.raw.cocktail_recipe));
        while (sc.hasNextLine()){
            String cocktail = sc.nextLine();
            String [] seperated = cocktail.split("-");
            cocktailsAndIngredients.add(seperated);
        }

        insertToDB(cocktailsAndIngredients);

    }

    private void insertToDB(ArrayList<String[]> cocktailsAndIng){
        ContentValues[] contentValuesArray = new ContentValues[cocktailsAndIng.size()];
        for (int i = 0 ; i < cocktailsAndIng.size(); i++){
            ContentValues cv = new ContentValues();
            String[] cocktail = cocktailsAndIng.get(i);
            String cocktailName = cocktail[0];
            String cocktailIngredients = cocktail[1];
            Log.d("FileReader" ,"Name = " + cocktailName + " Ingredients = " + cocktailIngredients + "\n");
            cv.put(DataContract.CocktailData.COLUMN_COCKTAIL_NAME, cocktailName);
            cv.put(DataContract.CocktailData.COLUMN_INGREDIENTS,cocktailIngredients);
            contentValuesArray[i] = cv;
        }
    }
}
