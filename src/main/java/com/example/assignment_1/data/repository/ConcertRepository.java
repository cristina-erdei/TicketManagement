package com.example.assignment_1.data.repository;

import com.example.assignment_1.data.model.ArtistDB;
import com.example.assignment_1.data.model.ConcertDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository<ConcertDB, Long> {
    public List<ConcertDB> findAllByArtist(ArtistDB artistDB);
}
