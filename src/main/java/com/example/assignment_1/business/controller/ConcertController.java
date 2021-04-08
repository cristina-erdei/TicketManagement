package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.ConcertServiceImpl;
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
@RequestMapping("/concert")
public class ConcertController {
    @Autowired
    private ConcertServiceImpl concertService;
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/getAll")
    public @ResponseBody
    List<Concert> findAll() {
        return concertService.findAll();
    }

    @GetMapping("/getById/{concertId}")
    public @ResponseBody
    Concert findById(@PathVariable Long concertId) {
        return concertService.findById(concertId);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Concert concert, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        concertService.save(concert);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Concert updatedValue, @RequestHeader("Token") String token) {
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        boolean success = concertService.update(id, updatedValue);
        if (!success) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(@RequestHeader("Token") String token) {

        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        concertService.deleteAll();

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{concertId}")
    public ResponseEntity delete(@PathVariable Long concertId, @RequestHeader("Token") String token) {

        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        concertService.deleteById(concertId);
        return new ResponseEntity(HttpStatus.OK);
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