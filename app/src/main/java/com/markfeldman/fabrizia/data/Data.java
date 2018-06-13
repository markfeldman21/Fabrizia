package com.markfeldman.fabrizia.data;

public class Data {
    private static final String[] cocktails = {"Whiskey Limoncello Smash","Kentucky Limoncello Mule",
    "Blood Orange Margarita"};

    private static final String[] cocktailRecipes = {"Limoncello\n Whiskey\n Soda Water ","Kentucky Limoncello Mule",
            "Blood Orange Margarita"};

    private static final String[] recipeTitles = {"Limoncello Shrimp", "Limoncello Ice Cream", "Limoncello Chicken",
    "Limoncello Pops"};


    public static String[] getCocktails() {
        return cocktails;
    }

    public static String[] getCocktailRecipes() {
        return cocktailRecipes;
    }

    public static String[] getRecipeTitles() {
        return recipeTitles;
    }
}
