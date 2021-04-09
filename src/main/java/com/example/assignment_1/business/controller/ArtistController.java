package com.example.assignment_1.business.controller;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.model.User;
import com.example.assignment_1.business.service.implementation.ArtistServiceImpl;
import com.example.assignment_1.business.service.implementation.UserServiceImpl;
import com.example.assignment_1.data.model.ArtistDB;
import com.example.assignment_1.helper.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Artist> findById(@PathVariable Long artistId){
        Artist artist = artistService.findById(artistId);
        if(artist == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Artist> create(@RequestBody Artist artist, @RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Artist savedArtist = new Artist(artistService.save(artist));
        return new ResponseEntity<>(savedArtist, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{artistId}")
    public ResponseEntity<Artist> deleteById(@PathVariable Long artistId, @RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Artist artist = artistService.findById(artistId);
        if(artist == null || artist.getId() == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        artistService.deleteById(artistId);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PostMapping("/updateById/{artistId}")
    public ResponseEntity<Artist> update(@PathVariable Long artistId, @RequestBody Artist newValue,@RequestHeader("Token") String token){
        User requestingUser = userService.findByToken(token);

        if (requestingUser.getRole() != UserRole.Administrator) {
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }

        Artist updated =  artistService.update(artistId, newValue);
        if(updated == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(updated, HttpStatus.OK);
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
