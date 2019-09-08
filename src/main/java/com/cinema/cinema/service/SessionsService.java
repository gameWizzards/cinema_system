package com.cinema.cinema.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cinema.cinema.entity.Films;
import com.cinema.cinema.entity.Sessions;
import com.cinema.cinema.repository.SessionsRepository;

@Service
public class SessionsService {

	private SessionsRepository sessionsRepository;

	public SessionsService(SessionsRepository sessionsRepository) {
		this.sessionsRepository = sessionsRepository;
	}

	public List<Sessions> findAll() {
		// TODO validation current time
		return sessionsRepository.findAll();
	}

	public Optional<Sessions> findSessionsById(int id) {

		return sessionsRepository.findById(id);
	}

	public void addAmount(int id, float amount) {

		Sessions ss = sessionsRepository.findById(id).get();

		ss.setAmountCash(ss.getAmountCash() + amount);

		sessionsRepository.save(ss);

	}

}
