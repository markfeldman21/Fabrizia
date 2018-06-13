package com.markfeldman.fabrizia.utilities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.data.Data;

public class PopUpUtility {
    public static void addTaskPopUpWindow(final Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.recipe_pop_up, null);
        final TextView popUpGetTask = view.findViewById(R.id.recipePopUp);
        String[]recipes = Data.getCocktailRecipes();
        popUpGetTask.setText(recipes[0]);
        popUpGetTask.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        popUpGetTask.setTextSize(20);


        AlertDialog.Builder alert = new AlertDialog.Builder(new ContextThemeWrapper(context,R.style.AlertDialogCustom));
        alert.setView(view);
        alert.setTitle("RECIPE");
        alert.setCancelable(true);

        alert.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.create();
        alert.show();
    }
}
