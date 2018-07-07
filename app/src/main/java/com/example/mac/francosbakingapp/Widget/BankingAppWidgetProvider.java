package com.example.mac.francosbakingapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mac.francosbakingapp.IngredientActivity;
import com.example.mac.francosbakingapp.MainActivity;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 */
public class BankingAppWidgetProvider extends AppWidgetProvider {

    public static ArrayList<Ingredient> mIngredientsList;

    private static Recipe mRecipe;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateWidgets(context,appWidgetManager,appWidgetIds);
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds){
        for(int appWidgetId : appWidgetIds){
            updateWidget(context,appWidgetManager,appWidgetId);
        }
    }
    static void updateWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId){
        loadRecipes(context,appWidgetManager,appWidgetId);
    }

    private static void loadRecipes(final Context context,final AppWidgetManager appWidgetManager,final int appWidgetId){

        Call <ArrayList<Recipe>> call = (Call<ArrayList<Recipe>>) RetrofitBuilder.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                int position=preferences.getInt(MainActivity.POSITION_KEY,0);
                mRecipe=response.body().get(position);
                Intent detailLaunch= new Intent(context,IngredientActivity.class);
                Bundle extras =new Bundle();
                extras.putParcelable(MainActivity.RECIPE_KEY,mRecipe);
                detailLaunch.putExtras(extras);
                PendingIntent pendingIntent=PendingIntent.getActivity(context,0,detailLaunch,PendingIntent.FLAG_UPDATE_CURRENT);

                RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.banking_app_widget_provider);
                Intent remoteViewsIntent=new Intent(context,RecipeIngredientsWidgetService.class);
                views.setRemoteAdapter(R.id.ListView_ingredients_for_widget,remoteViewsIntent);
                views.setTextViewText(R.id.recipe_widget_name,mRecipe.getName());

                views.setOnClickPendingIntent(R.id.widget_container_layout,pendingIntent);

                appWidgetManager.updateAppWidget(appWidgetId,views);
            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {

                RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.banking_app_widget_provider);
                Intent remoteViewsIntent=new Intent(context,RecipeIngredientsWidgetService.class);
                views.setRemoteAdapter(R.id.ListView_ingredients_for_widget,remoteViewsIntent);
                views.setTextViewText(R.id.recipe_widget_name,mRecipe.getName());

                appWidgetManager.updateAppWidget(appWidgetId,views);


            }
        });
    }

    public static void sendUpdateBroadcast(Context context){
        Intent intent =new Intent (AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context,BankingAppWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        final  String action=intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
            ComponentName componentName=new ComponentName(context,BankingAppWidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName),R.id.ListView_ingredients_for_widget);
            int[] appWidgetIds=appWidgetManager.getAppWidgetIds(componentName);
            updateWidgets(context,appWidgetManager,appWidgetIds);
        }
        super.onReceive(context, intent);
    }
}

