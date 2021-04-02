package com.example.assignment_1.data.repository;

import com.example.assignment_1.data.model.ArtistDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistDB, Long> {
}
