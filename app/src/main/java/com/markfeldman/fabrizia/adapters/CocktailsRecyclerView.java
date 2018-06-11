package com.markfeldman.fabrizia.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.markfeldman.fabrizia.R;

public class CocktailsRecyclerView extends RecyclerView.Adapter<CocktailsRecyclerView.CocktailsAdapterViewHolder>{
    private String[]cocktails;
    private Context context;
    private CocktailRowClicked cocktailRowClicked;

    public interface CocktailRowClicked{
        void onClicked(String cocktailName);
    }


    public CocktailsRecyclerView(CocktailRowClicked cocktailRowClicked,Context context,String[]cocktails){
        this.context = context;
        this.cocktails = cocktails;
        this.cocktailRowClicked = cocktailRowClicked;
    }


    @NonNull
    @Override
    public CocktailsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutID = R.layout.individual_cocktail;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutID,parent,false);
        return new CocktailsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailsAdapterViewHolder holder, int position) {
        String cocktail = cocktails[position];
        holder.cocktailName.setText(cocktail);

    }

    @Override
    public int getItemCount() {
        return cocktails.length;
    }



    class CocktailsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView cocktailName;

        private CocktailsAdapterViewHolder(View itemView) {
            super(itemView);
            cocktailName = itemView.findViewById(R.id.cocktail_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            cocktailRowClicked.onClicked("Position = " + adapterPosition);
        }
    }

}
