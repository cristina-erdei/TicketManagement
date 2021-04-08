package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.model.Genre;

import java.time.LocalDateTime;

public class ConcertRequestModel {
    private String title;
    private int maximumNumberOfTickets;
    private LocalDateTime dateAndTime;
    private Genre genre;
    private Long artistId;


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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
}
