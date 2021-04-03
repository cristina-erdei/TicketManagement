package com.example.assignment_1.business.service.interfaces;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArtistService {
    List<Artist> findAll();
    Artist findById(Long artistId);
    void save(Artist artist);
    boolean update(Long id, Artist newValue);
    void deleteById(Long artistId);
    void deleteAll();

    List<Concert> getAllConcerts(Long artistID);
}
