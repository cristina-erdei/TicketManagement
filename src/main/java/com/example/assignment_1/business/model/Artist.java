package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.ArtistDB;

public class Artist {
    private Long id;

    private String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public Artist(ArtistDB artistDB){
        this.id = artistDB.getId();
        this.name = artistDB.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
