package com.example.assignment_1.business.service.implementation;

import com.example.assignment_1.business.model.Artist;
import com.example.assignment_1.business.model.Concert;
import com.example.assignment_1.business.service.interfaces.ArtistService;
import com.example.assignment_1.data.model.ArtistDB;
import com.example.assignment_1.data.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {


    @Qualifier("artistRepository")
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ConcertServiceImpl concertService;


    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll().stream().map(Artist::new).collect(Collectors.toList());
    }

    @Override
    public Artist findById(Long artistId) {
        Optional<ArtistDB> artist = artistRepository.findById(artistId);
        return artist.map(Artist::new).orElse(null);
    }

    @Override
    public ArtistDB save(Artist artist) {
        return artistRepository.save(new ArtistDB(artist));
    }

    @Override
    public Artist update(Long id, Artist newValue) {
        Optional<ArtistDB> aux = artistRepository.findById(id);

        if(aux.isEmpty()) return null;

        ArtistDB artist = aux.get();
        artist.setName(newValue.getName());
        return new Artist(artistRepository.save(artist));
    }

    @Override
    public void deleteById(Long artistId) {
        artistRepository.deleteById(artistId);
    }

    @Override
    public List<Concert> getAllConcerts(Long artistId) {
        Artist artist = findById(artistId);
        if(artist == null || artist.getId() == null) return null;

        return concertService.findAllByArtist(artist);
    }
}
