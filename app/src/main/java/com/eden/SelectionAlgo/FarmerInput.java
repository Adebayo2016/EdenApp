package com.eden.SelectionAlgo;

import com.eden.Crop;
import com.eden.States;

public class FarmerInput {

    public Crop crop;
    public   String state;
    public Double erosionTendency;
   // public States ForestType;

    public FarmerInput(Crop crop,   String state, Double erosionTendency){

        this.crop = crop;
        this.state = state;
        this.erosionTendency = erosionTendency;
    }
}
