package com.eftimoff.bakingapp.app.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Ingredient implements Parcelable {

    private double quantity;
    private String measure;
    private String ingredient;

    public Ingredient(double quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    protected Ingredient(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    @Override
    public String toString() {

        String quantityString = formatQuantity();
        String measureString = measure.toLowerCase();
        String ingredientString = ingredient.toLowerCase();

        return String.format(Locale.getDefault(), "%s %s %s", quantityString, measureString, ingredientString);
    }

    private String formatQuantity() {
        String quantityString = String.valueOf(quantity);

        if (Integer.valueOf(quantityString.substring(quantityString.indexOf(".") + 1)) == 0) {
            return String.valueOf((int) quantity);
        }

        int denominator = (int) (1 / Math.abs(quantity - (int) quantity));  //- 0.0001 so the division doesn't get affected by the float point aproximated representation
        int units = (int) quantity;


        int numerator = units * denominator + 1;
        if (numerator > units + 1) {
            return String.format(Locale.getDefault(), "%d 1/%d", units, denominator);
        }

        return String.format(Locale.getDefault(), "%d %d", numerator, denominator);
    }
}
