package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.service.implementation.ArtistServiceImpl;
import com.example.assignment_1.business.service.interfaces.ArtistService;
import com.example.assignment_1.data.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistServiceImpl artistService;

    @GetMapping("/getAll")
    public List<Artist> findAll(){
        return artistService.findAll();
    }

    @GetMapping("/get/{artistId}")
    public Artist findById(@PathVariable Long artistId){
        return artistService.findById(artistId);
    }

    @PostMapping("/create")
    public void create(@RequestBody Artist artist){
//        if (user.role != UserRole.Administrator) {
//            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT);
//        }

        artistService.save(artist);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(){
        artistService.deleteAll();
    }

    @DeleteMapping("/delete/{artistId}")
    public void deleteById(@PathVariable Long artistId){
        artistService.deleteById(artistId);
    }

    @PostMapping("/update/{artistId}")
    public void update(@PathVariable Long artistId, @RequestBody Artist newValue){
        artistService.update(artistId, newValue);
    }

    @GetMapping("/getConcerts/{artistId}")
    public List<Concert> getAllConcerts(@PathVariable Long artistId){
        return artistService.getAllConcerts(artistId);
    }
}
