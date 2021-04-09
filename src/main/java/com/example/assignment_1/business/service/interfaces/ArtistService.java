package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.data.model.ArtistDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    List<Artist> findAll();
    Artist findById(Long artistId);
    ArtistDB save(Artist artist);
    Artist update(Long id, Artist newValue);
    void deleteById(Long artistId);

    List<Concert> getAllConcerts(Long artistID);
}
