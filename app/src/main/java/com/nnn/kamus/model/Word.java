package com.nnn.kamus.model;

import java.io.Serializable;

/**
 * Created by ridhaaaaazis on 06/02/18.
 */

public class Word implements Serializable{
    int id;
    String origin;
    String meaning;

    public Word() {
    }

    public Word(String origin, String meaning) {
        this.origin = origin;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
