package com.markfeldman.fabrizia.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.activities.FoodRecipes;
import com.markfeldman.fabrizia.adapters.DataRecyclerView;
import com.markfeldman.fabrizia.data.Data;
import com.markfeldman.fabrizia.data.DataContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements DataRecyclerView.RowClicked,
        LoaderManager.LoaderCallbacks<Cursor>{
    private static final int LOADER_ID = 2;
    public final static String INDEX_VALUE = "recipe index";
    private DataRecyclerView dataRecyclerView;
    private String[] projection = {DataContract.RecipeData._ID,DataContract.RecipeData.COLUMN_RECIPE_NAME,
    DataContract.RecipeData.COLUMN_RECIPE_INGREDIENTS};

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

/*
        RecyclerView recyclerView = view.findViewById(R.id.recipe_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        dataRecyclerView = new DataRecyclerView(this,getActivity());
        recyclerView.setAdapter(dataRecyclerView);
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        */

        return view;
    }

    @Override
    public void onClicked(int cocktailName) {
        Intent showRecipe = new Intent(getActivity(),FoodRecipes.class);
        showRecipe.putExtra(INDEX_VALUE,cocktailName);
        startActivity(showRecipe);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri cocktailQuery = DataContract.RecipeData.CONTENT_URI_RECIPES;
        return new CursorLoader(getActivity(),cocktailQuery,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.getCount()!=0){
            dataRecyclerView.swap(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

}
