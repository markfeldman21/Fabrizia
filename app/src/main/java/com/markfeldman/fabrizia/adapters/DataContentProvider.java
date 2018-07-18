package com.markfeldman.fabrizia.adapters;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.markfeldman.fabrizia.data.DataContract;
import com.markfeldman.fabrizia.data.Database;

public class DataContentProvider extends ContentProvider {
    public final static int CODE_COCKTAIL = 100;
    public final static int CODE_COCKTAIL_WITH_ID = 101;
    public final static int CODE_RECIPE = 200;
    public final static  int CODE_RECIPE_WITH_ID = 201;

    private UriMatcher sUriMatcher = buildURIMatcher();
    private Database database;

    public static UriMatcher buildURIMatcher(){
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DataContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority,DataContract.CocktailData.TABLE_NAME,CODE_COCKTAIL);
        uriMatcher.addURI(authority,DataContract.CocktailData.TABLE_NAME + "/#",CODE_COCKTAIL_WITH_ID);
        uriMatcher.addURI(authority,DataContract.RecipeData.TABLE_NAME,CODE_RECIPE);
        uriMatcher.addURI(authority, DataContract.RecipeData.TABLE_NAME + "/#",CODE_RECIPE_WITH_ID);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        database = new Database(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        database.openReadable();
        Cursor cursor;
        int match = sUriMatcher.match(uri);

        switch (match){
            case CODE_COCKTAIL:{
                Log.d("ContentProvider","INSIDE COCKTAIL QUERY" + match);
                cursor = database.getAllCocktailRows();
                break;
            }
            case CODE_COCKTAIL_WITH_ID:{
                Log.d("ContentProvider","INSIDE COCKTAIL WITH ID");
                cursor = database.getSpecificCocktailRow(projection,selectionArgs);
                break;
            }
            case CODE_RECIPE:{
                Log.d("ContentProvider","INSIDE RECIPE! + " + match);
                cursor = database.getAllRecipeRows();
                break;
            }
            case CODE_RECIPE_WITH_ID:{
                Log.d("ContentProvider","INSIDE RECIPE WITH ID!");
                cursor = database.getSpecificRecipeRow(projection,selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("UKNOWN URI:" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
