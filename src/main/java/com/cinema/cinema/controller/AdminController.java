package com.cinema.cinema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinema.cinema.entity.Films;
import com.cinema.cinema.entity.Sessions;
import com.cinema.cinema.service.FilmsService;
import com.cinema.cinema.service.SessionsService;

@Controller
public class AdminController {

	@Autowired
	FilmsService filmsService;

	@Autowired
	SessionsService sessionsService;

	@GetMapping("/admin")
	public String admin(Model model) {
		List<Films> test = new ArrayList<>();
		model.addAttribute("test", test);
		List<Films> tests = filmsService.findAll();
		model.addAttribute("tests", tests);

		List<Sessions> session = new ArrayList<>();
		model.addAttribute("seans", session);
		List<Sessions> sessions = sessionsService.findAll();
		model.addAttribute("allSeans", sessions);

//		List<Films> list = filmsService.findAll();
//		for(Films film: list)
//			System.out.println(film.getTitle());
		return "admin";
	}

	@PostMapping("getSessionsAdmin")
	public String getSessionsAdmin(@RequestParam String films, ModelMap model) {
		System.out.println(films);
		if (films.equals("null"))
			return "main"; // new ModelAndView("redirect:/main", model);

		String film = filmsService.findFilmById(films).get().getTitle();
		model.addAttribute("film", film);

		List<Sessions> sessionList = sessionsService.findAll();
		List<Sessions> sessionListByFilm = new ArrayList<Sessions>();

		for (Sessions sessions : sessionList) {
			System.out.println(sessions.getId() + " + " + sessions.getFilm().getId());

			if (sessions.getFilm().getId() == Integer.valueOf(films)) {
				sessionListByFilm.add(sessions);
				System.out.println(sessions.getId());
			}

		}

		model.addAttribute("sessionListByFilm", sessionListByFilm);
		// return new ModelAndView("redirect:/main", model);

		return "admin";
	}

	@PostMapping("reset")
	public String reset() {

		return "redirect:/admin";
	}

}
