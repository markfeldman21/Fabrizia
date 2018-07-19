package com.markfeldman.fabrizia.fragments;


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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.adapters.DataRecyclerView;
import com.markfeldman.fabrizia.data.Data;
import com.markfeldman.fabrizia.data.DataContract;
import com.markfeldman.fabrizia.data.Database;
import com.markfeldman.fabrizia.utilities.PopUpUtility;

import java.util.ArrayList;
import java.util.Scanner;


public class CocktailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, DataRecyclerView.RowClicked {
    private static final int LOADER_ID = 1;
    private DataRecyclerView dataRecyclerView;
    private String[] projection = {DataContract.CocktailData._ID,DataContract.CocktailData.COLUMN_COCKTAIL_NAME,
            DataContract.CocktailData.COLUMN_INGREDIENTS};
    public CocktailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_cocktail, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cocktail_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        dataRecyclerView = new DataRecyclerView(this,getActivity());
        recyclerView.setAdapter(dataRecyclerView);
        Log.d("Recipes","INSIDE COCKTAIL FRAG MAIN! ");
        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        return view;
    }

    @Override
    public void onClicked(int rowID) {
        PopUpUtility.addTaskPopUpWindow(getActivity(), rowID);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri cocktailQuery = DataContract.CocktailData.CONTENT_URI;
        return new CursorLoader(getActivity(),cocktailQuery,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.getCount()!=0){
            Log.d("Recipes","INSIDE COCKTAIL FRAG BEFORE SWAP! " +
            data.getColumnName(1));
            dataRecyclerView.swap(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }
}
