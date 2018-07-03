package com.example.mac.francosbakingapp.Widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.mac.francosbakingapp.Model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class UpdateWidgetService extends IntentService {
    public static final String KEY_WIDGET_INGREDIENTS_LIST="key_widget_ingredients_List";
    public static final String ACTION_UP_WIDGET="android.appwidget.action.APPWIDGET_UPDATE";

    public  UpdateWidgetService(){
        super("UpdateWidgetService");
    }

    public static void startUpdateWidgetService(Context context, ArrayList<Ingredient> ingredientsList){
        Intent intent =new Intent(context,UpdateWidgetService.class);
        intent.putExtra(KEY_WIDGET_INGREDIENTS_LIST,String.valueOf(ingredientsList));
        context.startService(intent);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent != null){
            ArrayList<Ingredient> ingredientsList=intent.getParcelableArrayListExtra(KEY_WIDGET_INGREDIENTS_LIST);
            Intent updateWidgetIntent=new Intent(ACTION_UP_WIDGET);
            updateWidgetIntent.setAction(ACTION_UP_WIDGET);
            updateWidgetIntent.putExtra(KEY_WIDGET_INGREDIENTS_LIST,ingredientsList);
            sendBroadcast(updateWidgetIntent);
        }

    }
}
