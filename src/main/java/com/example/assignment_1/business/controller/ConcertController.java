package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.service.implementation.ArtistServiceImpl;
import com.example.assignment_1.business.service.implementation.ConcertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concert")
public class ConcertController {
    @Autowired
    private ConcertServiceImpl concertService;

    @GetMapping("/getAll")
    public List<Concert> findAll() {
        return concertService.findAll();
    }

    @GetMapping("/get/{concertId}")
    public Concert findById(@PathVariable Long concertId) {
        return concertService.findById(concertId);
    }

    @PostMapping("/create")
    public void create(@RequestBody Concert concert) {
        concertService.save(concert);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Concert updatedValue){
        concertService.update(id, updatedValue);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        concertService.deleteAll();
    }

    @DeleteMapping("/delete/{concertId}")
    public void delete(@PathVariable Long concertId) {
        concertService.deleteById(concertId);
    }

    @GetMapping("/getTickets/{id}")
    public List<Ticket> getTickets(@PathVariable Long id){
        return concertService.getAllTickets(id);
    }

    @GetMapping("/getAvailableSeats/{id}")
    public int getAvailableSeats(@PathVariable Long id){
        return concertService.getAvailableSeats(id);
    }

}