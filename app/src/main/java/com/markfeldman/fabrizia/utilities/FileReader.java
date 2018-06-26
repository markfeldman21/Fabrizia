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
    public void readRecipesFromFile(Context context){
        ArrayList<String[]> cocktailsAndIngredients = new ArrayList<>();
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

    private void insertToDB(ArrayList<String[]> cocktailsAndIng, Context context, Database database){
        ContentValues[] contentValuesArray = new ContentValues[cocktailsAndIng.size()];

        for (int i = 0 ; i < cocktailsAndIng.size(); i++){
            ContentValues cv = new ContentValues();
            String[] cocktail = cocktailsAndIng.get(i);
            String cocktailName = cocktail[0];
            String cocktailIngredients = cocktail[1];
            Log.d("FileReader" ,"Name = " + cocktailName + " Ingredients = " + cocktailIngredients + "\n");
            cv.put(DataContract.CocktailData.COLUMN_COCKTAIL_NAME, cocktailName);
            cv.put(DataContract.CocktailData.COLUMN_INGREDIENTS,cocktailIngredients);
            database.insertRowToCocktail(cv);
        }
    }
}
