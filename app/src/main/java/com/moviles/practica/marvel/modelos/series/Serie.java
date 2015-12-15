package com.moviles.practica.marvel.modelos.series;

import com.moviles.practica.marvel.modelos.Image;
import com.moviles.practica.marvel.modelos.Url;

import java.util.List;

/**
 * Created by Sergio on 17/11/2015.
 */
public class Serie {
    private int id;
    private String title;
    private String description;
    private Image thumbnail;
    private List<Url> urls;

    private int startYear;
    private int endYear;

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }


    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }


}
