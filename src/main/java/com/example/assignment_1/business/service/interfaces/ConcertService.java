package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConcertService {
    List<Concert> findAll();
    Concert findById(Long concertId);
    void save(Concert concert);
    boolean update(Long id, Concert newValue);
    void deleteAll();
    void deleteById(Long concertId);

    List<Ticket> getAllTickets(Long concertId);
    int getAvailableSeats(Long concertId);
}
