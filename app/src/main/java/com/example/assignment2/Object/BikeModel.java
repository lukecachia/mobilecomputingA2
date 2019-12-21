package com.example.assignment2.Object;

public class BikeModel {

    private String bikeBrand;
    private String bikeModel;

    public BikeModel(String bikeBrand, String bikeModel) {
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
    }


    public String getBikeBrand() {
        return bikeBrand;
    }

    public void setBikeBrand(String bikeBrand) {
        this.bikeBrand = bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    @Override
    public String toString(){
        return bikeBrand + bikeModel;
    }
}
