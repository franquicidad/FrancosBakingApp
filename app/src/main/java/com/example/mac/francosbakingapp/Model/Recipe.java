package com.example.mac.francosbakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable{

    private int id;
    private String name;
    private List<Ingredient> ingredientList=null;
    private List<Process> processList=null;


    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeList(this.ingredientList);
        parcel.writeList(this.processList);
    }

    protected Recipe(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.ingredientList = new ArrayList<>();
        in.readList(this.ingredientList,Ingredient.class.getClassLoader());
        this.processList = new ArrayList<>();
        in.readList(this.processList, Process.class.getClassLoader());

    }

    public static final Parcelable.Creator<Recipe> CREATOR=new Parcelable.Creator<Recipe>(){

        @Override
        public Recipe createFromParcel(Parcel parcel) {
            return new Recipe(parcel);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
