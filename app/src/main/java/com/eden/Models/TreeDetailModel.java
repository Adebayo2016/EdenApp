package com.eden.Models;

public class TreeDetailModel {

    public String tree_name;
    public String common_name;
    public String description;
    public  String imageUrl;


    //for firebase
    private TreeDetailModel(){

    }


    public TreeDetailModel(String tree_name, String common_name, String description, String imageUrl) {
        this.tree_name = tree_name;
        this.common_name = common_name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTree_name() {
        return tree_name;
    }

    public void setTree_name(String tree_name) {
        this.tree_name = tree_name;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
