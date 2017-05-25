package com.qtgk.bean;

public class NewsClassify {

    private int drawableId;
    private String name;
    private String namePY;

    public NewsClassify(int drawableId, String name, String namePY){
        this.drawableId = drawableId;
        this.name = name;
        this.namePY = namePY;
    }

    public String getName() {
        return name;
    }

    public String getNamePY() {
        return namePY;
    }
}
