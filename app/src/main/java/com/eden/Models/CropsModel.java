package com.eden.Models;

public class CropsModel {

    private String CropName;
    private String BotanicalName;
    public Double shadeRequirement;

    public CropsModel(){}


    public Double getShadeRequirement() {
        return shadeRequirement;
    }

    public void setShadeRequirement(Double shadeRequirement) {
        this.shadeRequirement = shadeRequirement;
    }

    public CropsModel(String CropName, String BotanicalName, double shadeRequirement){

        this.CropName=CropName;
        this.BotanicalName=BotanicalName;
        this.shadeRequirement=shadeRequirement;

    }

    public String getCropName() {
        return CropName;
    }

    public void setCropName(String cropName) {
        CropName = cropName;
    }

    public String getBotanicalName() {
        return BotanicalName;
    }

    public void setBotanicalName(String botanicalName) {
        BotanicalName = botanicalName;
    }
}
