package com.moviles.practica.marvel.modelos.eventos;

import java.util.List;

/**
 * Created by Sergio on 17/11/2015.
 */
public class EventDataContainer {
    private int limit;
    private int total;
    private int count;
    private List<Event> results;

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

    public List<Event> getResults() {
        return results;
    }

    public void setResults(List<Event> results) {
        this.results = results;
    }


}
