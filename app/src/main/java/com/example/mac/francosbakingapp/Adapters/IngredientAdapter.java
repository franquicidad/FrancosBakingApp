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

        mContext=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View itemView=inflater.inflate(R.layout.ingredient_layout,parent,false);
        return new IngredientAdapter.ingredientAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ingredientAdapterViewHolder holder, int position) {

        holder.ing_textview.setText(position);

    }


    @Override
    public int getItemCount() {
        if (mIngredientList==null){
            return 0;
        }else{
            return mIngredientList.size();

        }

    }

    public void setmIngredientList(List<Ingredient> ingredientList){
        mIngredientList=ingredientList;
        notifyDataSetChanged();
    }

    public class ingredientAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView ing_textview;

        public ingredientAdapterViewHolder(View itemView) {
            super(itemView);

            ing_textview=itemView.findViewById(R.id.ingredients);
        }
    }
}
