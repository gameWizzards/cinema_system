package com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.cinema.entity.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

}
