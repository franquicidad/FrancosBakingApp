package com.example.mac.francosbakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mac.francosbakingapp.IngredientActivity;
import com.example.mac.francosbakingapp.Model.Ingredient;
import com.example.mac.francosbakingapp.R;

import java.util.ArrayList;
import java.util.List;




public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ingredientAdapterViewHolder> {

    private Context mContext;
    private List<Ingredient> mIngredientList;

    public IngredientAdapter(List<Ingredient> mIngredientList) {
        this.mIngredientList = mIngredientList;
    }

    @NonNull
    @Override
    public IngredientAdapter.ingredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.ingredient_textview, parent, false);
        return new IngredientAdapter.ingredientAdapterViewHolder(itemView);
    }


    public void setmIngredientList(List<Ingredient> ingredientList) {
        mIngredientList = ingredientList;
        notifyDataSetChanged();
    }

    public class ingredientAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView ing_name, ing_quantity, ing_measure,ing_whatever;
        private List<Ingredient> mIngredientList;

        public ingredientAdapterViewHolder(View itemView) {
            super(itemView);

            ing_name = itemView.findViewById(R.id.ingredient_name);
            ing_measure = itemView.findViewById(R.id.ingredient_measure);
            ing_quantity = itemView.findViewById(R.id.ingredient_quantity);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientAdapterViewHolder holder, int position) {

        Ingredient ingObject = mIngredientList.get(position);
        holder.ing_name.setText(ingObject.getIngredient());
        holder.ing_quantity.setText(String.valueOf(ingObject.getQuantity()));
        String measure_ori = ingObject.getMeasure().toLowerCase();
        holder.ing_measure.setText(String.format("%s%s", Character.toUpperCase(measure_ori.charAt(0)), measure_ori.substring(1)));

    }


    @Override
    public int getItemCount() {
        if (mIngredientList == null) {
            return 0;
        } else {
            return mIngredientList.size();

        }
    }
}
