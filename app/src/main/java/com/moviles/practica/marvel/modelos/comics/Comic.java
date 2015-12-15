package com.moviles.practica.marvel.modelos.comics;

import com.moviles.practica.marvel.modelos.Image;
import com.moviles.practica.marvel.modelos.Url;

import java.util.List;

/**
 * Created by Sergio on 18/11/2015.
 */
public class Comic {
    private int id;
    private String title;
    private String description;
    private Image thumbnail;
    private List<Url> urls;
    private List<ComicPrice> prices;
    private List<ComicDate> dates;

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

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public List<ComicPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ComicPrice> prices) {
        this.prices = prices;
    }

    public List<ComicDate> getDates() {
        return dates;
    }

    public void setDates(List<ComicDate> dates) {
        this.dates = dates;
    }


}
