package com.cinema.cinema.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cinema.cinema.entity.Films;
import com.cinema.cinema.repository.FilmsRepository;

@Service
public class FilmsService {

	private FilmsRepository filmsRepository;

	public FilmsService(FilmsRepository filmsRepository) {
		this.filmsRepository = filmsRepository;
	}

	public Optional<Films> findFilmById(String id) {
		return filmsRepository.findById(Integer.valueOf(id));
	}

	public List<Films> findAll() {
		return filmsRepository.findAll();
	}

	public List<Films> findDistrFilms() {
		Date currDate = new Date();
		List<Films> list = new ArrayList<Films>();
		filmsRepository.findAll().forEach(film -> {
			if (currDate.after(film.getPremierDate()) && currDate.before(film.getEndDate())) {
				list.add(film);
			}
		});
		return list;
	}

}
