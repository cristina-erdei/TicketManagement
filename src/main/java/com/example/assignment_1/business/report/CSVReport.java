package com.example.assignment_1.business.report;

import com.example.assignment_1.business.model.Ticket;

import java.util.List;

public class CSVReport extends Report{


    public CSVReport(List<Ticket> tickets) {
        super(tickets);
    }

    @Override
    public String generateReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("TicketID,ConcertID,ArtistID,ArtistName,ConcertTitle,ConcertMaxNumberOfTickets,ConcertDateAndTime,ConcertGenre,TicketNumberOfSeats\n");
        for(Ticket ticket: tickets){
            builder.append(ticket.toString());
        }
        return builder.toString();
    }
}
