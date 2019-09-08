package com.cinema.cinema.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cinema.cinema.entity.Seats;
import com.cinema.cinema.entity.Sessions;
import com.cinema.cinema.repository.SeatsRepository;

@Service
public class SeatsService {

	private SeatsRepository seatsRepository;

	public SeatsService(SeatsRepository seatsRepository) {
		this.seatsRepository = seatsRepository;
	}

	public List<Seats> findAll() {
		return seatsRepository.findAll();
	}

	public String getAllBookedAsString(Sessions s) {

		StringBuffer str = new StringBuffer();

		for (Seats seat : seatsRepository.findAll()) {

			// TODO дебаг почему нул
			if (seat.getTicket() == null)
				continue;

			if (seat.getTicket().getSession() == s && seat.getIsBooked()) {
				str.append(seat.toString() + ", ");
			}
		}

		return str.toString();
	}

	public boolean isBooked(int row, int place) {

		// TODO poisk

		return true;
	}

	public Seats parseSeats(String s) {
		// R2-3

		String r = String.valueOf(s.charAt(1));
		String p = String.valueOf(s.charAt(3));

		int row = Integer.valueOf(r);

		int place = Integer.valueOf(p);

		return new Seats(row, place, true);

	}

}
