package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.*;
import com.example.assignment_1.business.service.implementation.ArtistServiceImpl;
import com.example.assignment_1.business.service.implementation.ConcertServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.helper.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/concert")
public class ConcertController {
    @Autowired
    private ConcertServiceImpl concertService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ArtistServiceImpl artistService;


    @GetMapping("/getAll")
    public @ResponseBody
    List<Concert> findAll() {
        return concertService.findAll();
    }

    @GetMapping("/getById/{concertId}")
    public ResponseEntity<Concert> findById(@PathVariable Long concertId) {
        Concert concert = concertService.findById(concertId);
        if(concert == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(concert, HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<ConcertDB> create(@RequestBody ConcertCreateModel concertCreateModel, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);
        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }


        Concert concert = new Concert();
        concert.setGenre(concertCreateModel.getGenre());
        concert.setTitle(concertCreateModel.getTitle());
        concert.setMaximumNumberOfTickets(concertCreateModel.getMaximumNumberOfTickets());
        Artist artist = artistService.findById(concertCreateModel.getArtistId());

        if (artist == null || artist.getId() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        concert.setArtist(artist);
        concert.setDateAndTime(LocalDateTime.of(concertCreateModel.getYear(),
                                                concertCreateModel.getMonth(),
                                                concertCreateModel.getDay(),
                                                concertCreateModel.getHour(),
                                                concertCreateModel.getMinute()));

        return new ResponseEntity<>(concertService.save(concert), HttpStatus.OK);
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<Concert> update(@PathVariable Long id, @RequestBody ConcertCreateModel updateValue, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);
        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        Concert updated = concertService.update(id, updateValue);
        if (updated == null) return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{concertId}")
    public ResponseEntity<Concert> deleteById(@PathVariable Long concertId, @RequestHeader("Token") String token) {

        User requestingUser = userService.findByToken(token);
        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

        Concert concert = concertService.findById(concertId);
        if(concert == null || concert.getId() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        concertService.deleteById(concertId);
        return new ResponseEntity<>(concert, HttpStatus.OK);
    }

    @GetMapping("/getTickets/{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Long id) {

        List<Ticket> tickets = concertService.getAllTickets(id);

        if (tickets == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/getAvailableSeats/{id}")
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable Long id) {
        int result = concertService.getAvailableSeats(id);
        if (result == -1) {
            return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}