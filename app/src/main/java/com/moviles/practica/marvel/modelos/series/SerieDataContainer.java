package com.moviles.practica.marvel.modelos.series;

import java.util.List;

/**
 * Created by Sergio on 17/11/2015.
 */
public class SerieDataContainer {
    private int limit;
    private int total;
    private int count;
    private List<Serie> results;

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

    public List<Serie> getResults() {
        return results;
    }

    public void setResults(List<Serie> results) {
        this.results = results;
    }


}
