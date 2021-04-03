package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.service.interfaces.TicketService;
import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.model.TicketDB;
import com.example.assignment_1.data.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {


    @Qualifier("ticketRepository")
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll().stream().map(Ticket::new).collect(Collectors.toList());
    }

    @Override
    public Ticket findById(Long ticketId) {
        Optional<TicketDB> ticket = ticketRepository.findById(ticketId);
        return ticket.map(Ticket::new).orElse(null);
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(new TicketDB(ticket));
    }

    @Override
    public boolean update(Long id, Ticket newValue) {
        Optional<TicketDB> aux = ticketRepository.findById(id);

        if(aux.isEmpty()) return false;

        TicketDB ticket = aux.get();
        ticket.setConcert(new ConcertDB(newValue.getConcert()));
        ticket.setNumberOfSeats(newValue.getNumberOfSeats());
        ticketRepository.save(ticket);
        return true;

    }

    @Override
    public void deleteAll() {
        ticketRepository.deleteAll();
    }

    @Override
    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
