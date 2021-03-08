package com.eden.Models;

public class OrnamentalModel {

    public String OrnamentalName;
    public String OrnamentalBotanical;
    public String OrnamentalImageUrl;
    public  String OrnamentalDesc;

    private OrnamentalModel(){

    }

    public void setOrnamentalDesc(String ornamentalDesc) {
        OrnamentalDesc = ornamentalDesc;
    }

    public String getOrnamentalDesc() {
        return OrnamentalDesc;
    }

    public String getOrnamentalName() {
        return OrnamentalName;
    }

    public String getOrnamentalBotanical() {
        return OrnamentalBotanical;
    }

    public String getOrnamentalImageUrl() {
        return OrnamentalImageUrl;
    }

    public void setOrnamentalName(String ornamentalName) {
        OrnamentalName = ornamentalName;
    }

    public void setOrnamentalBotanical(String ornamentalBotanical) {
        OrnamentalBotanical = ornamentalBotanical;
    }

    public void setOrnamentalImageUrl(String ornamentalImageUrl) {
        OrnamentalImageUrl = ornamentalImageUrl;
    }

    private OrnamentalModel(String ornamentalName, String OrnamentalBotanical, String OrnamentalImageUrl,String OrnamentalDesc){



    }

}
