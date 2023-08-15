package com.example.project.repositories;

import com.example.project.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT obj FROM Ticket obj WHERE obj.user.id = :userId ")
    List<Ticket> findTicketsByUserId (Long userId);

}
