package com.cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.cinema.entity.Halls;
import com.cinema.cinema.repository.HallsRepository;

@Service
public class HallsService {

	private HallsRepository hallsRepository;

	public HallsService(HallsRepository hallsRepository) {
		this.hallsRepository = hallsRepository;
	}

	public List<Halls> findAll() {
		return hallsRepository.findAll();
	}

}
