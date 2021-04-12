package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.TicketCreateModel;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.report.Report;
import com.example.assignment_1.business.report.ReportFactory;
import com.example.assignment_1.business.service.implementation.ConcertServiceImpl;
import com.example.assignment_1.business.service.implementation.TicketServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.data.model.TicketDB;
import com.example.assignment_1.helper.ReportType;
import com.example.assignment_1.helper.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;
    @Autowired
    private ConcertServiceImpl concertService;
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/getAll")
    public @ResponseBody
    List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/getById/{id}")
    public
    ResponseEntity<Ticket> findById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        if(ticket == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDB> create(@RequestBody TicketCreateModel ticket) {
        if (ticket == null || ticket.getConcertId() == null || ticket.getNumberOfSeats() <= 0) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        Concert concert = concertService.findById(ticket.getConcertId());
        if(concert == null || concert.getId() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        int availableSeats = concertService.getAvailableSeats(ticket.getConcertId());

        if(availableSeats == -1){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        if (availableSeats < ticket.getNumberOfSeats()) {
            System.out.println("Not enough tickets.");
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }

        Ticket ticket1 = new Ticket();
        ticket1.setNumberOfSeats(ticket.getNumberOfSeats());
        ticket1.setConcert(concert);

        TicketDB saved = ticketService.save(ticket1);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PostMapping("updateById/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @RequestBody TicketCreateModel newTicket) {

        Ticket updated = ticketService.update(id, newTicket);

        if(updated == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Ticket> deleteById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        if(ticket == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        ticketService.deleteById(id);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @GetMapping(value = "/generateReport/{type}", produces = MediaType.TEXT_PLAIN_VALUE)
    public  ResponseEntity<byte[]> getReport(@PathVariable ReportType type, @RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<Ticket> tickets = findAll();
        String report = ReportFactory.generateReport(type, tickets);

        return new ResponseEntity<>(report.getBytes(StandardCharsets.UTF_8), HttpStatus.OK);
    }
}
