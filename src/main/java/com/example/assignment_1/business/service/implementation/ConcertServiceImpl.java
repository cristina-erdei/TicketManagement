package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.service.interfaces.ConcertService;
import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.repository.ConcertRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }


    @Override
    public List<Concert> findAll() {
        return concertRepository.findAll().stream().map(Concert::new).collect(Collectors.toList());
    }

    @Override
    public Concert findById(Long concertId) {
        Optional<ConcertDB> concert = concertRepository.findById(concertId);
        return concert.map(Concert::new).orElse(null);
    }

    @Override
    public void save(Concert concert) {
        concertRepository.save(new ConcertDB(concert));
    }

    @Override
    public void update(Long id, Concert newValue) {
        Concert concert = findById(id);
        concert.setArtist(newValue.getArtist());
        concert.setTickets(newValue.getTickets());
        concert.setTitle(newValue.getTitle());
        concert.setMaximumNumberOfTickets(newValue.getMaximumNumberOfTickets());
        concert.setDateAndTime(newValue.getDateAndTime());
        concert.setGenre(newValue.getGenre());
        concertRepository.save(new ConcertDB(concert));
    }

    @Override
    public void deleteAll() {
        concertRepository.deleteAll();
    }

    @Override
    public void deleteById(Long concertId) {
        concertRepository.deleteById(concertId);
    }

    @Override
    public List<Ticket> getAllTickets(Long concertId) {
        Concert concert = findById(concertId);
        return concert.getTickets();
    }

    @Override
    public int getAvailableSeats(Long concertId) {
        Concert concert = findById(concertId);
        int soldTickets = getAllTickets(concertId).size();
        return concert.getMaximumNumberOfTickets() - soldTickets;
    }
}
