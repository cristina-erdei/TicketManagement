package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.TicketCreateModel;
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
    @Autowired
    private ConcertServiceImpl concertService;


    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll().stream().map(Ticket::new).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findAllByConcert(ConcertDB concert) {
        return ticketRepository.findAllByConcert(concert).stream().map(Ticket::new).collect(Collectors.toList());
    }

    @Override
    public Ticket findById(Long ticketId) {
        Optional<TicketDB> ticket = ticketRepository.findById(ticketId);
        return ticket.map(Ticket::new).orElse(null);
    }

    @Override
    public TicketDB save(Ticket ticket) {
        TicketDB toSave = new TicketDB(ticket);
        return ticketRepository.save(toSave);
    }

    @Override
    public Ticket update(Long id, TicketCreateModel newValue) {
        Optional<TicketDB> aux = ticketRepository.findById(id);
        if(aux.isEmpty()) return null;

        Concert concert = concertService.findById(newValue.getConcertId());
        if(concert == null) return null;

        int availableSeats = concertService.getAvailableSeats(newValue.getConcertId());
        if(availableSeats == -1 || newValue.getNumberOfSeats() > availableSeats) return null;

        TicketDB ticket = aux.get();
        ticket.setConcert(new ConcertDB(concert));
        ticket.setNumberOfSeats(newValue.getNumberOfSeats());


        return new Ticket(ticketRepository.save(ticket));
    }

    @Override
    public void deleteById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
