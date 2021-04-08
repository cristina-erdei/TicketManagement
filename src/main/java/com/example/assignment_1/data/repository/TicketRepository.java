package com.example.assignment_1.data.repository;

import com.example.assignment_1.data.model.ConcertDB;
import com.example.assignment_1.data.model.TicketDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketDB, Long> {
    public List<TicketDB> findAllByConcert(ConcertDB concertDB);
}
