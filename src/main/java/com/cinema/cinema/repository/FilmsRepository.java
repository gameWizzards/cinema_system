package com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.cinema.entity.Films;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Integer> {
}
