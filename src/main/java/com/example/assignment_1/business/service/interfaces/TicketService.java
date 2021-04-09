package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.TicketCreateModel;
import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.model.TicketDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    List<Ticket> findAll();

    List<Ticket> findAllByConcert(ConcertDB concert);

    Ticket findById(Long ticketId);

    TicketDB save(Ticket ticket);

    Ticket update(Long id, TicketCreateModel newValue);

    void deleteById(Long ticketId);
}
