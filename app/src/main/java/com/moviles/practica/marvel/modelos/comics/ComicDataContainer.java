package com.moviles.practica.marvel.modelos.comics;

import java.util.List;

/**
 * Created by Sergio on 18/11/2015.
 */
public class ComicDataContainer {
    private int limit;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }

    private int total;
    private int count;
    private List<Comic> results;
}
