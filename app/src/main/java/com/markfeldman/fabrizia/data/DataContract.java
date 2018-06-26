package com.markfeldman.fabrizia.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {
    public static final String CONTENT_AUTHORITY = "com.markfeldman.fabrizia";
    //Uri for othet apps to be able to access
    public final static Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //Possible paths that can be appended to BASE_CONTENT_URI to form valid URI
    public final static String PATH_COCKTAILS = "cocktails_table";
    public final static String PATH_RECIPES = "recipes_table";

    //Inner Class For Each Seperate Table
    public static class CocktailData implements BaseColumns {
        public final static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COCKTAILS).build();
        public static final String TABLE_NAME = "cocktails_table";
        public static final String COLUMN_COCKTAIL_NAME = "cocktail_name";
        public static final String COLUMN_INGREDIENTS = "ingredients";
    }

    public static class RecipeData implements BaseColumns {
        public final static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();
        public static final String TABLE_NAME = "recipes_table";
        public static final String COLUMN_RECIPE_NAME = "recipe_name";
        public static final String COLUMN_RECIPE_INGREDIENTS = "recipe_ingredients";
    }
}
