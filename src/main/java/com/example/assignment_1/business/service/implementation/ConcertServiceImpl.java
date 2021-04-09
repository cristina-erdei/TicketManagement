package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.ConcertCreateModel;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.service.interfaces.ConcertService;
import com.example.assignment_1.data.model.ArtistDB;
import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConcertServiceImpl implements ConcertService {


    @Qualifier("concertRepository")
    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    private ArtistServiceImpl artistService;


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
    public List<Concert> findAllByArtist(Artist artist) {
        return concertRepository.findAllByArtist(new ArtistDB(artist)).stream().map(Concert::new).collect(Collectors.toList());
    }

    @Override
    public ConcertDB save(Concert concert) {
        return concertRepository.save(new ConcertDB(concert));
    }

    @Override
    public Concert update(Long id, ConcertCreateModel newValue) {
        Optional<ConcertDB> aux = concertRepository.findById(id);

        if (aux.isEmpty()) return null;

        ConcertDB concert = aux.get();
        Artist artist = artistService.findById(newValue.getArtistId());


        concert.setArtist(new ArtistDB(artist));
        concert.setTitle(newValue.getTitle());
        concert.setMaximumNumberOfTickets(newValue.getMaximumNumberOfTickets());
        concert.setDateAndTime(LocalDateTime.of(newValue.getYear(),
                newValue.getMonth(),
                newValue.getDay(),
                newValue.getHour(),
                newValue.getMinute()));
        concert.setGenre(newValue.getGenre());
        return new Concert(concertRepository.save(concert));
    }

    @Override
    public void deleteById(Long concertId) {
        concertRepository.deleteById(concertId);
    }

    @Override
    public List<Ticket> getAllTickets(Long concertId) {
        Optional<ConcertDB> optionalConcert = concertRepository.findById(concertId);
        if (optionalConcert.isEmpty()) return null;
        ConcertDB concertDB = optionalConcert.get();

        return ticketService.findAllByConcert(concertDB);
    }

    @Override
    public int getAvailableSeats(Long concertId) {
        Concert concert = findById(concertId);
        if (concert == null || concert.getId() == null) return -1;
        List<Ticket> tickets = getAllTickets(concertId);
        if (tickets == null) return -1;

        int soldTickets = tickets.stream().mapToInt(Ticket::getNumberOfSeats).sum();

        System.out.println("soldTickets = " + soldTickets);


        return concert.getMaximumNumberOfTickets() - soldTickets;
    }
}
