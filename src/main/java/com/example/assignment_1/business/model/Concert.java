package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.helper.Genre;

import java.time.LocalDateTime;

public class Concert {
    private Long id;

    private Artist artist;

    private String title;
    private int maximumNumberOfTickets;
    private LocalDateTime dateAndTime;
    private Genre genre;


    public Concert() {
    }

    public Concert(Artist artist, String title, int maximumNumberOfTickets, LocalDateTime dateAndTime, Genre genre) {
        this.artist = artist;
        this.title = title;
        this.maximumNumberOfTickets = maximumNumberOfTickets;
        this.dateAndTime = dateAndTime;
        this.genre = genre;
    }

    public Concert(ConcertDB concertDB){
        this.id = concertDB.getId();
        this.artist = new Artist(concertDB.getArtist());
        this.title = concertDB.getTitle();
        this.maximumNumberOfTickets = concertDB.getMaximumNumberOfTickets();
        this.dateAndTime = concertDB.getDateAndTime();
        this.genre = concertDB.getGenre();
;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", artist=" + artist +
                ", title='" + title + '\'' +
                ", maximumNumberOfTickets=" + maximumNumberOfTickets +
                ", dateAndTime=" + dateAndTime +
                ", genre=" + genre +
                '}';
    }
}
