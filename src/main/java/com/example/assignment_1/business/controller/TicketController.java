package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Ticket;
import com.example.assignment_1.business.service.implementation.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketService;

    @GetMapping("/getAll")
    public @ResponseBody List<Ticket> findAll(){
        return ticketService.findAll();
    }

    @GetMapping("/get/{id}")
    public Ticket findById(@PathVariable Long id){
        return ticketService.findById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody Ticket ticket){
        ticketService.save(ticket);
    }

    @PostMapping("update/{id}")
    public void update(@PathVariable Long id, @RequestBody Ticket newTicket){
        ticketService.update(id, newTicket);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        ticketService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        ticketService.deleteById(id);
    }
}
