package com.markfeldman.fabrizia.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class DataContract {

    //Inner Class For Each Seperate Table
    public static class CocktailData implements BaseColumns {
        public static final String TABLE_NAME = "cocktails_table";
        public static final String COLUMN_COCKTAIL_NAME = "cocktail_name";
        public static final String COLUMN_INGREDIENTS = "ingredients";

    }
}
