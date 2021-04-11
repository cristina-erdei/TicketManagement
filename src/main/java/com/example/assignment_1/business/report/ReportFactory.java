package com.example.assignment_1.business.report;

import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.helper.ReportType;

import java.util.List;

public class ReportFactory {
    public static String generateReport(ReportType type, List<Ticket> tickets){
        System.out.println("type = " + type);
        switch (type){
            case CSV:
                return new CSVReport(tickets).generateReport();
            case XML:
                return new XMLReport(tickets).generateReport();
        }

        return null;
    }
}
