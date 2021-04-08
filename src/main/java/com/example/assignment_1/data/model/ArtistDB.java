package com.example.assignment_1.data.model;

import com.example.assignment_1.business.model.Artist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ArtistDB {

    private Long id;

    private String name;

    private List<ConcertDB> concerts;

    public ArtistDB(){
        this.concerts = new ArrayList<>();
    }

    public ArtistDB(String name, List<ConcertDB> concerts) {
        this.name = name;
        this.concerts = concerts;
    }

    public ArtistDB(Artist artist){
        this.id = artist.getId();
        this.name = artist.getName();
        this.concerts = artist.getConcerts().stream().map(ConcertDB::new).collect(Collectors.toList());
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

    @OneToMany(mappedBy = "artist")
    public List<ConcertDB> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<ConcertDB> concert) {
        this.concerts = concert;
    }
}
