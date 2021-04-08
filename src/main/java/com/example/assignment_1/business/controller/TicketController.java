package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.TicketServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.business.service.interfaces.UserService;
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
    public ResponseEntity create(@RequestBody Ticket ticket) {
        if (ticket == null || ticket.getConcert() == null || ticket.getNumberOfSeats() <= 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Concert concert = concertController.findById(ticket.getConcert().getId());
        if(concert == null || concert.getId() == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }


        ResponseEntity<Integer> availableSeatsResponse = concertController.getAvailableSeats(ticket.getConcert().getId());
        Integer availableSeats = availableSeatsResponse.getBody();

        if(availableSeats == -1){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        if (availableSeats < ticket.getNumberOfSeats()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        ticketService.save(ticket);

        return new ResponseEntity(HttpStatus.OK);
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
