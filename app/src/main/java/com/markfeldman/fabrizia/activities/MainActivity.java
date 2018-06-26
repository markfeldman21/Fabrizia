package com.markfeldman.fabrizia.activities;

import android.content.ContentValues;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.adapters.ViewPagerAdapter;
import com.markfeldman.fabrizia.data.DataContract;
import com.markfeldman.fabrizia.fragments.CocktailFragment;
import com.markfeldman.fabrizia.fragments.ContactFragment;
import com.markfeldman.fabrizia.fragments.HomeFragment;
import com.markfeldman.fabrizia.fragments.ProductsFragment;
import com.markfeldman.fabrizia.fragments.RecipesFragment;
import com.markfeldman.fabrizia.utilities.FileReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;
    //1. Create Database that stores cocktail name, recipe, and id + Create 2nd Database for Recipe names + ingredients
    //2. Load data upon launch of app.
    //3. Create Loader in the fragment class that returns Cursor
    //4. Change RecyclerView to query database
    //5. Do same for recipes
    //6. Load 3 cocktail photos and 3 recipe photos to drawable for use.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.containerViewPager);
        setUpViewPagerWithFrags(viewPager);
        new FileReader().readRecipesFromFile(this);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setUpViewPagerWithFrags(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(),"HOME");
        viewPagerAdapter.addFragment(new ProductsFragment(), "Products");
        viewPagerAdapter.addFragment(new CocktailFragment(), "Cocktails");
        viewPagerAdapter.addFragment(new RecipesFragment(),"Recipes");
        viewPagerAdapter.addFragment(new ContactFragment(),"Contact");
        viewPager.setAdapter(viewPagerAdapter);
    }

}
