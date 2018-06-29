package com.example.mac.francosbakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientsWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new ListViewFactory(this.getApplicationContext(),intent) );
    }

    class ListViewFactory implements RemoteViewsService.RemoteViewsFactory{

        private ArrayList<Ingredient> mIngredientsList;
        private Context context;

        public ListViewFactory( Context context,Intent intent) {
            this.context = context;
            mIngredientsList=intent.getStringArrayListExtra(UpdateWidgetService.KEY_WIDGET_INGREDIENTS_LIST);
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            this.mIngredientsList= BankingAppWidgetProvider.mIngredientsList;

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(mIngredientsList!= null){
                return mIngredientsList.size();
            }else{
                return 1;
            }
        }

        @Override
        public RemoteViews getViewAt(int i) {

            RemoteViews widgetItem=new RemoteViews(context.getPackageName(), R.layout.ingredient_list_item_widget_layout);
            if(mIngredientsList==null){
                widgetItem.setTextViewText(R.id.tv_widget_population,"No data to display,Select a recipe in app");
            }else {
                widgetItem.setTextViewText(R.id.tv_widget_population,mIngredientsList.get(i));
            }

            Intent fillin=new Intent();
            widgetItem.setOnClickFillInIntent(R.id.tv_widget_population,fillin);
            return widgetItem;
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
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
