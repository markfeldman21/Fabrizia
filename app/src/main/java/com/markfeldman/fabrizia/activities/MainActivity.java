package com.markfeldman.fabrizia.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.adapters.ViewPagerAdapter;
import com.markfeldman.fabrizia.fragments.CocktailFragment;
import com.markfeldman.fabrizia.fragments.ContactFragment;
import com.markfeldman.fabrizia.fragments.HomeFragment;
import com.markfeldman.fabrizia.fragments.ProductsFragment;
import com.markfeldman.fabrizia.fragments.RecipesFragment;

public class MainActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AIzaSyB5HaUownHX8usW9OLP7280ncJwxaMWgiI

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.containerViewPager);
        setUpViewPagerWithFrags(viewPager);

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
