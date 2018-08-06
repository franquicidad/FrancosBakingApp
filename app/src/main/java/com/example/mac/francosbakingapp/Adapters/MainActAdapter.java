package com.example.mac.francosbakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.francosbakingapp.Model.Recipe;
import com.example.mac.francosbakingapp.R;

import java.util.ArrayList;

public class MainActAdapter extends RecyclerView.Adapter<MainActAdapter.RecipesAdapterViewHolder>{

    private Context mContext;

    private ArrayList<Integer> arrayListImages;
    private ArrayList<Recipe> mRecipeArrayListData;
    private RecipesAdapterOnClickHandler recipesAdapterOnClickHandler;
    private Recipe recipe;

    public interface RecipesAdapterOnClickHandler {
        void onRecipeClick(Recipe recipe,int position);
    }

    public MainActAdapter(RecipesAdapterOnClickHandler OnClickHandler){
        recipesAdapterOnClickHandler=OnClickHandler;
    }


    @NonNull
    @Override
    public MainActAdapter.RecipesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext=parent.getContext();

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        int resource= R.layout.adapter_main_design;
        View view=layoutInflater.inflate(resource,parent,false);

        return new RecipesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainActAdapter.RecipesAdapterViewHolder holder, int position) {

        holder.recipeTitle.setText(mRecipeArrayListData.get(position).getName());


        arrayListImages=new ArrayList<>();


        arrayListImages.add(R.drawable.nutella);
        arrayListImages.add(R.drawable.brownie);
        arrayListImages.add(R.drawable.yellowcake);
        arrayListImages.add(R.drawable.cheesecake);

       int imagePosition= arrayListImages.get(position);

       holder.recipePhoto.setImageResource(imagePosition);

    }

    public void setRecipesData(ArrayList<Recipe> recipesData){
        mRecipeArrayListData =recipesData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        if(mRecipeArrayListData==null){
            return 0;
        }else {
            return mRecipeArrayListData.size();
        }
    }

    public class RecipesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView recipePhoto;
        TextView recipeTitle;


        public RecipesAdapterViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeTitle=itemView.findViewById(R.id.cardView_textView_recipe_name);

            recipePhoto=itemView.findViewById(R.id.cardview_imageView);


        }


        @Override
        public void onClick(View view) {
            Recipe recipe=mRecipeArrayListData.get(getAdapterPosition());

            recipesAdapterOnClickHandler.onRecipeClick(recipe,getAdapterPosition());

        }
    }
}
