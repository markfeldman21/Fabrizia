package com.markfeldman.fabrizia.utilities;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.markfeldman.fabrizia.R;
import com.markfeldman.fabrizia.data.Data;
import com.markfeldman.fabrizia.data.DataContract;
import com.markfeldman.fabrizia.data.Database;

public class PopUpUtility {


    public static void addTaskPopUpWindow(final Context context, int id){
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.recipe_pop_up, null);
        final TextView popUpGetTask = view.findViewById(R.id.recipePopUp);
        Database database = new Database(context);
        database.openReadable();
        String ing = getIngredients(context,id);
        popUpGetTask.setText(ing);
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

    private static String getIngredients(Context context, int id){
        String [] projection = {DataContract.CocktailData._ID, DataContract.CocktailData.COLUMN_INGREDIENTS};
        Uri authority = DataContract.CocktailData.CONTENT_URI;
        String idConvert = Integer.toString(id);
        authority = authority.buildUpon().appendPath(idConvert).build();
        Cursor cursor = context.getContentResolver().query(authority,projection,null,new String[]{idConvert},null);
        String ingredients = cursor.getString(cursor.getColumnIndex(DataContract.CocktailData.COLUMN_INGREDIENTS));
        cursor.close();
        return ingredients;
    }
}
