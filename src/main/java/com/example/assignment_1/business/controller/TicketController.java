package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.TicketRequestModel;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.TicketServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.business.service.interfaces.UserService;
import com.example.assignment_1.data.model.TicketDB;
import com.example.assignment_1.data.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @Autowired
    private ConcertController concertController;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/getAll")
    public @ResponseBody
    List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/getById/{id}")
    public @ResponseBody
    Ticket findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDB> create(@RequestBody TicketRequestModel ticket, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);
        System.out.println("found user");
        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("authorized user");


        if (ticket == null || ticket.getConcertId() == null || ticket.getNumberOfSeats() <= 0) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

        System.out.println("ticket params ok");

        Concert concert = concertController.findById(ticket.getConcertId());
        if(concert == null || concert.getId() == null){
            System.out.println("bad concert");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        System.out.println("found ok concert");
        System.out.println("concert = " + concert);


        ResponseEntity<Integer> availableSeatsResponse = concertController.getAvailableSeats(ticket.getConcertId());
        Integer availableSeats = availableSeatsResponse.getBody();

        if(availableSeats == -1){
            System.out.println("cannot get available seats");
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        System.out.println("found available seats = ");
        System.out.println("availableSeats = " + availableSeats);

        if (availableSeats < ticket.getNumberOfSeats()) {
            System.out.println("not enough seats");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        System.out.println("enoguh seats");

        Ticket ticket1 = new Ticket();
        ticket1.setNumberOfSeats(ticket.getNumberOfSeats());
        ticket1.setConcert(concert);

        System.out.println("ticket1 = " + ticket1);

        TicketDB saved = ticketService.save(ticket1);

        System.out.println("saved = " + saved);

        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @PostMapping("updateById/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Ticket newTicket) {
        boolean success = ticketService.update(id, newTicket);

        if(!success) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(@RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
         ticketService.deleteAll();

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}
