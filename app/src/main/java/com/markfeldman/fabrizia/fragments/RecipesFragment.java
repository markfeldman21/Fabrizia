package com.markfeldman.fabrizia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements DataRecyclerView.RowClicked {
    public final static String INDEX_VALUE = "recipe index";

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recipe_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DataRecyclerView dataRecyclerView = new DataRecyclerView(this,getActivity(), Data.getRecipeTitles());
        recyclerView.setAdapter(dataRecyclerView);


        return view;
    }

    @Override
    public void onClicked(String cocktailName) {
        Intent showRecipe = new Intent(getActivity(),FoodRecipes.class);
        showRecipe.putExtra(INDEX_VALUE,cocktailName);
        startActivity(showRecipe);
    }
}
