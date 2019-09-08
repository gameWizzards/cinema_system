package com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.cinema.entity.Halls;

@Repository
public interface HallsRepository extends JpaRepository<Halls, Integer> {
}
