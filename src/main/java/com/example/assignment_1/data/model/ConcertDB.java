package com.example.assignment_1.data.model;

import com.example.assignment_1.business.model.Concert;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ConcertDB {
    private Long id;

    private ArtistDB artist;

    private String title;
    private int maximumNumberOfTickets;
    private LocalDateTime dateAndTime;
    private Genre genre;

    public ConcertDB(){}

    public ConcertDB(ArtistDB artist,  String title, int maximumNumberOfTickets, LocalDateTime dateAndTime, Genre genre) {
        this.artist = artist;
        this.title = title;
        this.maximumNumberOfTickets = maximumNumberOfTickets;
        this.dateAndTime = dateAndTime;
        this.genre = genre;
    }

    public ConcertDB(Concert concert){
        System.out.println("entering concert constructor");
        this.id = concert.getId();
        this.artist = new ArtistDB(concert.getArtist());
        this.title = concert.getTitle();
        this.maximumNumberOfTickets = concert.getMaximumNumberOfTickets();
        this.dateAndTime = concert.getDateAndTime();
        this.genre = concert.getGenre();
        System.out.println("exiting concert constructor");
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @ManyToOne
    public ArtistDB getArtist() {
        return artist;
    }

    public void setArtist(ArtistDB artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaximumNumberOfTickets() {
        return maximumNumberOfTickets;
    }

    public void setMaximumNumberOfTickets(int maximumNumberOfTickets) {
        this.maximumNumberOfTickets = maximumNumberOfTickets;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "ConcertDB{" +
                "id=" + id +
                ", artist=" + artist +
                ", title='" + title + '\'' +
                ", maximumNumberOfTickets=" + maximumNumberOfTickets +
                ", dateAndTime=" + dateAndTime +
                ", genre=" + genre +
                '}';
    }
}
