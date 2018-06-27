package com.example.mac.francosbakingapp.Widget;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class UpdateWidgetService {
    public static final String KEY_WIDGET_INGREDIENTS_LIST="key_widget_ingredients_List";
    public static final String ACTION_UP_WIDGET="android.appwidget.action.APPWIDGET_UPDATE";

    public  UpdateWidgetService(){
        super();
    }

    public static void startUpdateWidgetService(Context context, ArrayList<String> ingredientsList){
        Intent intent =new Intent(context,UpdateWidgetService.class);
        intent.putExtra(KEY_WIDGET_INGREDIENTS_LIST,ingredientsList);
        context.startService(intent);

    }




}
