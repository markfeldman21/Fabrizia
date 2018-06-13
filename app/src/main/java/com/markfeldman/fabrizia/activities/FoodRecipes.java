package com.markfeldman.fabrizia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.fragments.RecipesFragment;

public class FoodRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipes);

        Intent i = getIntent();
        String value = i.getStringExtra(RecipesFragment.INDEX_VALUE);

        Toast.makeText(this,value, Toast.LENGTH_LONG).show();
    }
}
