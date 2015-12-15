package com.moviles.practica.marvel.modelos.series;

/**
 * Created by Sergio on 17/11/2015.
 */
public class SerieDataWrapper {
    private int code;
    private String status;
    private SerieDataContainer data;


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

    public SerieDataContainer getData() {
        return data;
    }

    public void setData(SerieDataContainer data) {
        this.data = data;
    }


}
