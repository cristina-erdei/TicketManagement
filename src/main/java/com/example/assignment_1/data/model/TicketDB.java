package com.example.assignment_1.data.model;

import com.example.assignment_1.business.model.Ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TicketDB {
    private Long id;

    private ConcertDB concert;
    private int numberOfSeats;

    public TicketDB() {
    }

    public TicketDB(ConcertDB concert, int numberOfSeats) {
        this.concert = concert;
        this.numberOfSeats = numberOfSeats;
    }

    public TicketDB(Ticket ticket){
        this.concert = new ConcertDB(ticket.getConcert());
        this.numberOfSeats = ticket.getNumberOfSeats();
    }

    @ManyToOne
    public ConcertDB getConcert() {
        return concert;
    }

    public void setConcert(ConcertDB concert) {
        this.concert = concert;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
