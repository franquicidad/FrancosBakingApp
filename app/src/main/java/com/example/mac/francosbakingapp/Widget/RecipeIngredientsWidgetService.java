package com.example.mac.francosbakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mac.francosbakingapp.MainActivity;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;
import com.example.mac.francosbakingapp.RetrofitBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RecipeIngredientsWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new ListViewFactory(this.getApplicationContext()) );
    }

    class ListViewFactory implements RemoteViewsService.RemoteViewsFactory{

        private ArrayList<Ingredient> mIngredientsList;
        private Context context;

        public ListViewFactory( Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            loadData();

        }

        private void loadData() {
            Call <ArrayList<Recipe>> arrayListCall= RetrofitBuilder.getRecipes();
            try{
                Response<ArrayList<Recipe>> arrayListResponse=arrayListCall.execute();
                if (arrayListResponse!=null){
                    SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
                    int position=sharedPreferences.getInt(MainActivity.POSITION_KEY,0);
                    Recipe recipe= arrayListResponse.body().get(position);
                    mIngredientsList= (ArrayList<Ingredient>) recipe.getIngredients();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onDestroy() {
            if(mIngredientsList !=null){
                mIngredientsList=null;
            }

        }

        @Override
        public int getCount() {
            return  mIngredientsList==null ? 0: mIngredientsList.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            Ingredient actIngredient=mIngredientsList.get(position);
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.ingredient_list_item_widget_layout);
            String ingredientName= actIngredient.getIngredient();
            double ingredientQuantity=actIngredient.getQuantity();
            String ingredientMeasure=actIngredient.getMeasure();
            String ingredientString=String.format("${title}",ingredientName,ingredientQuantity,ingredientMeasure);
            remoteViews.setTextViewText(R.id.tv_widget_population,ingredientString);

            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
