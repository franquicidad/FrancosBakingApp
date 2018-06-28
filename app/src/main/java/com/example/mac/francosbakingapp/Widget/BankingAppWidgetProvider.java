package com.example.mac.francosbakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.mac.francosbakingapp.IngredientActivity;
import com.example.mac.francosbakingapp.R;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class BankingAppWidgetProvider extends AppWidgetProvider {

    public static ArrayList<String> mIngredientsList;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<String> mIngredientsList) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.banking_app_widget_provider);
        Intent ingredientsIntent =new Intent (context, IngredientActivity.class);
        PendingIntent ingredientsPendingIntent=PendingIntent.getActivity(context,10,ingredientsIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.ListView_ingredients_for_widget,ingredientsPendingIntent);

        Intent widgetServiceIntent =new Intent (context,RecipeIngredientsWidgetService.class);
        widgetServiceIntent.putStringArrayListExtra(UpdateWidgetService.KEY_WIDGET_INGREDIENTS_LIST,mIngredientsList);
        views.setRemoteAdapter(R.id.ListView_ingredients_for_widget,widgetServiceIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        //for (int appWidgetId : appWidgetIds) {
           // updateAppWidget(context, appWidgetManager, appWidgetId);
        //}
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void updateWidgets(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds,ArrayList<String> mIngredientsList){
        for(int appWidgetId:appWidgetIds){
            updateAppWidget(context,appWidgetManager,appWidgetId,mIngredientsList);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
        int[] appWidgetIds=appWidgetManager.getAppWidgetIds(new ComponentName(context,BankingAppWidgetProvider.class));
        if(intent.getAction().equals(UpdateWidgetService.ACTION_UP_WIDGET)){
            mIngredientsList=intent.getStringArrayListExtra(UpdateWidgetService.KEY_WIDGET_INGREDIENTS_LIST);
        }

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,R.id.ListView_ingredients_for_widget);
        updateWidgets(context,appWidgetManager,appWidgetIds,mIngredientsList);
        super.onReceive(context, intent);
    }
}

