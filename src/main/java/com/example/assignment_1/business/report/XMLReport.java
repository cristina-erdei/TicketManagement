package com.example.assignment_1.business.report;

import com.example.assignment_1.business.model.Ticket;

import java.util.List;

public class XMLReport extends Report{
    public XMLReport(List<Ticket> tickets) {
        super(tickets);
    }

    @Override
    public String generateReport() {
       return "xml";
    }
}
