package com.example.mac.francosbakingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

    double quantity;
    String measure;
    String ingredient;

    public Ingredient() {
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.quantity);
        parcel.writeString(this.measure);
        parcel.writeString(this.ingredient);

    }
    protected Ingredient(Parcel in){
        this.quantity= in.readDouble();
        this.measure=in.readString();
        this.ingredient=in.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR=new Parcelable.Creator<Ingredient>(){

        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
