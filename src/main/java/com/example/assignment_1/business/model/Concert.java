package com.example.assignment_1.business.model;

import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.model.Genre;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Concert {
    private Long id;

    private Artist artist;
    private List<Ticket> tickets;

    private String title;
    private int maximumNumberOfTickets;
    private LocalDateTime dateAndTime;
    private Genre genre;


    public Concert() {
    }

    public Concert(Artist artist, List<Ticket> tickets, String title, int maximumNumberOfTickets, LocalDateTime dateAndTime, Genre genre) {
        this.artist = artist;
        this.tickets = tickets;
        this.title = title;
        this.maximumNumberOfTickets = maximumNumberOfTickets;
        this.dateAndTime = dateAndTime;
        this.genre = genre;
    }

    public Concert(ConcertDB concertDB){
        this.id = concertDB.getId();
        this.artist = new Artist(concertDB.getArtist());
        this.tickets = concertDB.getTickets().stream().map(Ticket::new).collect(Collectors.toList());
        this.title = concertDB.getTitle();
        this.maximumNumberOfTickets = concertDB.getMaximumNumberOfTickets();
        this.dateAndTime = concertDB.getDateAndTime();
        this.genre = concertDB.getGenre();
;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
}
