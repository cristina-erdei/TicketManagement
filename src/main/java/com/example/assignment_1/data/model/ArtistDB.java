package com.example.assignment_1.data.model;

import com.example.assignment_1.business.model.Artist;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ArtistDB {

    private Long id;

    private String name;

    public ArtistDB(){
    }

    public ArtistDB(String name){
        this.name = name;
    }

    public ArtistDB(Artist artist){
        System.out.println("entering artist constructor");
        this.id = artist.getId();
        this.name = artist.getName();
        System.out.println("exiting artist constructor");
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArtistDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
