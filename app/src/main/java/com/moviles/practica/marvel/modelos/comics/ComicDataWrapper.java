package com.moviles.practica.marvel.modelos.comics;

/**
 * Created by Sergio on 18/11/2015.
 */
public class ComicDataWrapper {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ComicDataContainer getData() {
        return data;
    }

    public void setData(ComicDataContainer data) {
        this.data = data;
    }

    private String status;
    private ComicDataContainer data;
}
