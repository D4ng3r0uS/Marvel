package com.moviles.practica.marvel.modelos.personajes;


import android.os.Parcel;
import android.os.Parcelable;

import com.moviles.practica.marvel.modelos.Image;

import java.io.Serializable;

public class Character implements Serializable, Parcelable  {

    private int id;
    private String name;
    private String description;
    private Image thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Character(Parcel in){
        this.name = in.readString();
        this.description = in.readString();
        this.thumbnail = (Image) in.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeSerializable((Serializable) thumbnail);

    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
