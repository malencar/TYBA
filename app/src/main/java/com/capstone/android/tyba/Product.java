package com.capstone.android.tyba;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    String cheeseBreadTime; //time that bread is ready
    String frenchBreadTime; //time that bread is ready


    public Product(){
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public Product(String cheeseBreadTime, String frenchBreadTime ){
        this.cheeseBreadTime = cheeseBreadTime;
        this.frenchBreadTime = frenchBreadTime;
    }

    public String getCheesebread() {
        return cheeseBreadTime;
    }

    public String getFrenchbread() {
        return frenchBreadTime;
    }

}
