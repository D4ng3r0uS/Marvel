package com.moviles.practica.marvel.modelos.eventos;

/**
 * Created by Sergio on 17/11/2015.
 */
public class EventDataWrapper {
    private int code;
    private String status;
    private EventDataContainer data;


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

    public EventDataContainer getData() {
        return data;
    }

    public void setData(EventDataContainer data) {
        this.data = data;
    }


}
