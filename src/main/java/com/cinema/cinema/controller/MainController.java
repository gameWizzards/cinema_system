package com.cinema.cinema.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinema.cinema.entity.Films;
import com.cinema.cinema.entity.Sessions;
import com.cinema.cinema.entity.Tickets;
import com.cinema.cinema.service.FilmsService;
import com.cinema.cinema.service.SeatsService;
import com.cinema.cinema.service.SessionsService;
import com.cinema.cinema.service.TicketsService;

@Controller
public class MainController {

	@Autowired
	FilmsService filmsService;

	@Autowired
	SessionsService sessionsService;

	@Autowired
	TicketsService ticketsService;

	@Autowired
	SeatsService seatsService;

	int sessionId;
	float price;
	float amount;
	List<Tickets> tickets;
	float vip_price;

	
	@GetMapping("/main")
	public String main(Model model) {

		//init film list
		List<Films> test = new ArrayList<>();
		model.addAttribute("test", test);
		List<Films> tests = filmsService.findDistrFilms();
		model.addAttribute("tests", tests);

		//init session list
		List<Sessions> session = new ArrayList<>();
		model.addAttribute("seans", session);
		List<Sessions> sessions = sessionsService.findAll();
		model.addAttribute("allSeans", sessions);

		model.addAttribute("film", filmA);
		model.addAttribute("sessionListByFilm", sessionListByFilmA);
		
		model.addAttribute("booked", bookedA);
		model.addAttribute("hall_number", hallNumberA);
		model.addAttribute("price", price);
		model.addAttribute("vip_price", vip_price);
		
		model.addAttribute("seats", seatsA);
		model.addAttribute("amount", amount);
		
		return "main";
	}

	 String filmA = "";
	 List<Sessions> sessionListByFilmA;
	
	
	@PostMapping("/getSessions") 
	public String getSessions(@RequestParam String films) {
		System.out.println(films);
		
		//TODO выдать сообщения об ошибке если не выбран фильм
		if (films.equals("null"))
			return "main";

		//получаем название фильма
		filmA = filmsService.findFilmById(films).get().getTitle();


	    //получаем список сеансов нужного фильма 
		List<Sessions> sessionList = sessionsService.findAll();
		sessionListByFilmA = new ArrayList<Sessions>();

		for (Sessions sessions : sessionList) {
			System.out.println(sessions.getId() + " + " + sessions.getFilm().getId());

			if (sessions.getFilm().getId() == Integer.valueOf(films)) {
				sessionListByFilmA.add(sessions);
				System.out.println(sessions.getId());
			}

		}

		
		return "redirect:/main";
	}

	 String bookedA = "";
	 int hallNumberA;
	
	@PostMapping("/check")
	public String check(@RequestParam String seans) {
		System.out.println(seans);
		
		//TODO Сообщение об ошибке если не выбран сеанс
		if (seans.equals("null"))
			return "main"; 

		
		sessionId = Integer.valueOf(seans);

		Sessions cur_ses = sessionsService.findSessionsById(Integer.valueOf(seans)).get();

		/** TODO отрисовка занятых мест
		*  Можно заюзать jquery 
		*  В любом случае через js
		*  Пока что просто выведем как список (одной строкой)
		*/
		
		//Получаем список уже купленных билетов
		bookedA = seatsService.getAllBookedAsString(cur_ses);

		//определяем зал 
		hallNumberA = cur_ses.getHall().getNumber();
		
		/**
		 * Если значение для определенного сеанса нужно поменять
		 * Например на утренние сеансы или вечерние 
		 * То меняем session_price из табл sessions
		 * Если специальной цены нет - подтягиваем стандартную цену из табл films
		 */
		
		if (cur_ses.getSessionPrice() == 0) 
			price = cur_ses.getFilm().getStandartPrice();
		else
			price = cur_ses.getSessionPrice();

		

		//если вип не указано то считать все обычные
		//TODO перенести это в табл halls 
		if (cur_ses.getVipPrice() == 0)
			vip_price = price;
		else 
			vip_price = cur_ses.getVipPrice();
	
		

		return "redirect:/main";
	}

	String seatsA = "";
	
	@RequestMapping(value = "/selectSeats", method = RequestMethod.GET)
	public String selectSeats(HttpServletRequest request) {

		tickets = new ArrayList<Tickets>();

		String st[];
		st = request.getParameterValues("seat");

		// TODO Сообщение пользователю что не выбрано ни одного места
		if (st.length == 0)
			return "redirect:/";

		int vipCount = 0;
		float buffPrice = price;

		

		//количество выбранных ситс
		long count = st.length;

		for (int i = 0; i < count; i++) {
			
			//смотрим какой ряд
			String ss = String.valueOf(st[i].charAt(1));
			int row = Integer.valueOf(ss);
			//если последний ряд - цена выше
			if (row == 7) {
				buffPrice = price;
				price = vip_price;
				vipCount++;
			}

			//создаем новый тикет
			ticketsService.newTicket(price, 
					sessionsService.findSessionsById(sessionId).get(),
					seatsService.parseSeats(st[i]));

			price = buffPrice;

			//формируем строку с выбраными ситс
			seatsA += (i < count - 1) ? (st[i] + ", ") : (st[i]);

		}

		amount = ((count - vipCount) * price) + (vipCount * vip_price);

		return "redirect:/main";
	}

	@PostMapping("/passTickets")
	public String passTickets(Model model) {

		//TODO Вынести в отдельную табличку все бухгалтерские функции
		
		sessionsService.addAmount(sessionId, amount);

		return "redirect:/";
	}
	
	@GetMapping("")
	public String main() {
		
		filmA ="";
		bookedA = "";
		hallNumberA = 0;
		price = 0;
		vip_price = 0;
		seatsA = "";
		amount = 0;
		
		return "redirect:/main";
	}
	
	@GetMapping("/main2") //TEST
	public String main2(Model model) {
		List<Films> list = filmsService.findAll();
		for(Films film: list)
			System.out.println(film.getTitle());
		
		List<Sessions> sessionList = sessionsService.findAll();
		for (Sessions sessions : sessionList) {
			System.out.println(sessions.getId() + " + " + sessions.getFilm().getTitle());
		}
		List<Films> listd = filmsService.findAll();
		for (Films film : listd) {
			System.out.println(film.getTitle());
			for (Sessions sessions : film.getListSessions()) {
				System.out.println(sessions.getId() + " + " + sessions.getFilm().getTitle());
			}
		}
		return "main";
	}
	
}
