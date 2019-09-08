package com.cinema.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinema.cinema.entity.Sessions;

@Repository
public interface SessionsRepository extends JpaRepository<Sessions, Integer> {
}
