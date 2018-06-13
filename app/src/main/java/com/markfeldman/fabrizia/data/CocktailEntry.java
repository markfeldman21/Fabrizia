package com.markfeldman.fabrizia.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "cocktails")
public class CocktailEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cocktailName;
    private String cocktailRecipe;

    @Ignore
    public CocktailEntry(String cocktailName, String cocktailRecipe) {
        this.cocktailName = cocktailName;
        this.cocktailRecipe = cocktailRecipe;
    }

    public CocktailEntry(int id, String cocktailName, String cocktailRecipe) {
        this.id = id;
        this.cocktailName = cocktailName;
        this.cocktailRecipe = cocktailRecipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getCocktailRecipe() {
        return cocktailRecipe;
    }

    public void setCocktailRecipe(String cocktailRecipe) {
        this.cocktailRecipe = cocktailRecipe;
    }
}
