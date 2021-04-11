package com.example.assignment_1.business.report;

import com.example.assignment_1.business.model.Ticket;

import java.util.List;

public abstract class Report {
    protected List<Ticket> tickets;

    public Report(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public abstract String generateReport();
}
