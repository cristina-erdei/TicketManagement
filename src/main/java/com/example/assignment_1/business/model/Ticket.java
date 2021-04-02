package com.example.assignment_1.business.model;


import com.example.assignment_1.data.model.TicketDB;

public class Ticket {
    private Long id;

    private Concert concert;
    private int numberOfSeats;

    public Ticket() {
    }

    public Ticket(Concert concert, int numberOfSeats) {
        this.concert = concert;
        this.numberOfSeats = numberOfSeats;
    }

    public Ticket(TicketDB ticketDB){
        this.id = ticketDB.getId();
        this.numberOfSeats = ticketDB.getNumberOfSeats();
        this.concert = new Concert(ticketDB.getConcert());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
