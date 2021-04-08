package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.ArtistDB;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.stream.Collectors;

public class Artist {
    private Long id;

    private String name;
    ArtistDB artistDB;
//    private List<Concert> concerts;

    public Artist() {
    }

    public Artist(String name, List<Concert> concerts) {
        this.name = name;
//        this.concerts = concerts;
    }

    public Artist(ArtistDB artistDB){
        this.id = artistDB.getId();
        this.name = artistDB.getName();
        this.artistDB = artistDB;
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

//    public List<Concert> getConcerts() {
//        return artistDB.getConcerts().stream().map(Concert::new).collect(Collectors.toList());
//    }

//    public void setConcerts(List<Concert> concerts) {
//        this.concerts = concerts;
//    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", concerts=" + concerts +
                '}';
    }
}
