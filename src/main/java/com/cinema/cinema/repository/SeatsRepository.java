package com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.cinema.entity.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Integer> {

}
