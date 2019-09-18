package com.mulaitrip.mulaitrip.API.model;

/**
 * Created by Master on 21/07/2019.
 */

public class ScreenItem {
    String Title;
    int Description, ScreenImg;

    public ScreenItem(String title, int description, int screenImg) {
        Title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getDescription() {
        return Description;
    }

    public void setDescription(int description) {
        Description = description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
