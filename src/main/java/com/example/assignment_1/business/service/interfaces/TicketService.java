package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    List<Ticket> findAll();

    Ticket findById(Long ticketId);

    void save(Ticket ticket);

    boolean update(Long id, Ticket newValue);

    void deleteAll();

    void deleteById(Long ticketId);
}
