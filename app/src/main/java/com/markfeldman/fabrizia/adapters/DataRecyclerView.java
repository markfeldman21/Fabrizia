package com.markfeldman.fabrizia.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.data.Data;
import com.markfeldman.fabrizia.data.DataContract;

import java.util.ArrayList;

public class DataRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Cursor cursor;
    private Context context;
    private RowClicked rowClicked;
    final private int VIEW_TYPE_COCKTAILS = 0;
    final private int VIEW_TYPE_RECIPES = 1;



    public interface RowClicked{
        void onClicked(int rowID);
    }


    public DataRecyclerView(RowClicked rowClicked, Context context){
        this.context = context;
        this.rowClicked = rowClicked;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        switch (viewType) {
            case VIEW_TYPE_RECIPES: {
                Log.d("Recycler", "RecylerView: INSIDE RECIPE LAYOUT ON CREATE VIEW HOLDER");
                layoutId = R.layout.individual_recipe;
                View view = inflater.inflate(layoutId,parent,false);
                return new RecipesAdapterViewHolder(view);

            }

            case VIEW_TYPE_COCKTAILS: {
                Log.d("Recycler", "RecylerView: INSIDE COCKTAILS LAYOUT ON CREATE VIEW HOLDER");
                layoutId = R.layout.individual_cocktail;
                View view = inflater.inflate(layoutId,parent,false);
                return new CocktailsAdapterViewHolder(view);

            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_COCKTAILS:
                CocktailsAdapterViewHolder holder1 = (CocktailsAdapterViewHolder) holder;
                cursor.moveToPosition(position);
                String cocktail = cursor.getString(cursor.getColumnIndex(DataContract.CocktailData.COLUMN_COCKTAIL_NAME));
                holder1.cocktailName.setText(cocktail);
                break;
            case VIEW_TYPE_RECIPES:
                RecipesAdapterViewHolder holder2 = (RecipesAdapterViewHolder) holder;
                cursor.moveToPosition(position);
                String recipeName = cursor.getString(cursor.getColumnIndex(DataContract.RecipeData.COLUMN_RECIPE_NAME));
                holder2.recipe.setText(recipeName);
                break;
            default:
                throw new IllegalArgumentException("Invalid view type, value of ");
        }

    }


    @Override
    public int getItemCount() {

        if (cursor==null){
            return 0;
        }
        Log.d("Recycler", "INSIDE RECYCLERVIEW's GET ITEM COUNT = " + cursor.getCount());
        return cursor.getCount();
    }




    private class CocktailsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView cocktailName;

        private CocktailsAdapterViewHolder(View itemView) {
            super(itemView);
            cocktailName = itemView.findViewById(R.id.cocktail_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            cursor.moveToPosition(adapterPosition);
            int id = cursor.getInt(cursor.getColumnIndex(DataContract.CocktailData._ID));
            rowClicked.onClicked(id);
        }
    }


    private class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView recipe;

        private RecipesAdapterViewHolder(View itemView) {
            super(itemView);
            recipe = itemView.findViewById(R.id.recipe_individual_name);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            cursor.moveToPosition(adapterPosition);
            int id = cursor.getInt(cursor.getColumnIndex(DataContract.CocktailData._ID));
            rowClicked.onClicked(id);
        }
    }


    @Override
    public int getItemViewType(int position) {
        String cocktailCheck = null;
        String recipeCheck = null;
        cursor.moveToFirst();

        //Bug Fixed Here


        if (cursor.getColumnName(1).equals("cocktail_name")) {
            Log.d("Recycler", "****INSIDE RECYCLERVIEW's GET ITEM = COCKTAIL****");
            return VIEW_TYPE_COCKTAILS;
        } else if (cursor.getColumnName(1).equals("recipe_name")){
            Log.d("Recycler", "****INSIDE RECYCLERVIEW's GET ITEM = RECIPES****");
            return VIEW_TYPE_RECIPES;
        }else{
            return 2;
        }
    }

    public void swap (Cursor c){
        this.cursor = c;
        notifyDataSetChanged();
    }
}
