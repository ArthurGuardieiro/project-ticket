package com.example.project.repositories;

import com.example.project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT obj FROM Event obj WHERE obj.city.id = :cityId ")
    List<Event> findEventsByCityId(Long cityId);

}
