package com.markfeldman.fabrizia.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.adapters.DataRecyclerView;
import com.markfeldman.fabrizia.data.Data;
import com.markfeldman.fabrizia.utilities.PopUpUtility;

import java.util.ArrayList;
import java.util.Scanner;


public class CocktailFragment extends Fragment implements DataRecyclerView.RowClicked {

    public CocktailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_cocktail, container, false);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = readFromFile(arrayList);
        RecyclerView recyclerView = view.findViewById(R.id.cocktail_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        DataRecyclerView dataRecyclerView = new DataRecyclerView(this,getActivity(),arrayList);
        recyclerView.setAdapter(dataRecyclerView);

        return view;
    }

    @Override
    public void onClicked(String cocktailName) {
        PopUpUtility.addTaskPopUpWindow(getActivity());
    }


    private ArrayList<String> readFromFile(ArrayList<String> arrayList){
        Scanner sc = new Scanner(getResources().openRawResource(R.raw.cocktail_names)).useDelimiter("\n");
        while (sc.hasNextLine()){
            arrayList.add(sc.next());
        }
        return arrayList;
    }

}
