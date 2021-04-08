package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.ArtistServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.business.service.interfaces.ArtistService;
import com.example.assignment_1.business.service.interfaces.UserService;
import com.example.assignment_1.data.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/getAll")
    public @ResponseBody List<Artist> findAll(){
        return artistService.findAll();
    }

    @GetMapping("/getById/{artistId}")
    public @ResponseBody Artist findById(@PathVariable Long artistId){
        return artistService.findById(artistId);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Artist artist, @RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        artistService.save(artist);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(@RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        artistService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{artistId}")
    public ResponseEntity deleteById(@PathVariable Long artistId, @RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        artistService.deleteById(artistId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/updateById/{artistId}")
    public ResponseEntity update(@PathVariable Long artistId, @RequestBody Artist newValue,@RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        artistService.update(artistId, newValue);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getArtistConcertsById/{artistId}")
    public ResponseEntity<List<Concert>> getAllConcerts(@PathVariable Long artistId){
        List<Concert> concerts = artistService.getAllConcerts(artistId);

        if(concerts == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(concerts, HttpStatus.OK);
    }
}
