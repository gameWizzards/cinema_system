package com.cinema.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.cinema.entity.Seats;
import com.cinema.cinema.entity.Sessions;
import com.cinema.cinema.entity.Tickets;
import com.cinema.cinema.repository.TicketsRepository;

@Service
public class TicketsService {

	private TicketsRepository ticketsRepository;

	public TicketsService(TicketsRepository ticketsRepository) {
		this.ticketsRepository = ticketsRepository;
	}

	public void newTicket(float finalPrice, Sessions session, Seats seat) {
		ticketsRepository.save(new Tickets(finalPrice, session, seat));
	}

	public List<Tickets> findAll() {
		return ticketsRepository.findAll();
	}

}
