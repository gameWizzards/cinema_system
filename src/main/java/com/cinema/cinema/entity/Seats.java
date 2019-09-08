package com.cinema.cinema.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class Seats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Seats() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "seat_row")
	private int seatRow;

	@Column(name = "place_in_row")
	private int placeInRow;

	// TODO change default value (now is true)
	@Column(name = "is_booked")
	private Boolean isBooked;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID", referencedColumnName = "Seat")
	private Tickets ticket;

	public Seats(int seatRow, int placeInRow, Boolean isBooked) {
		super();
		this.seatRow = seatRow;
		this.placeInRow = placeInRow;
		this.isBooked = isBooked;
	}

	public Tickets getTicket() {
		return ticket;
	}

	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getPlaceInRow() {
		return placeInRow;
	}

	public void setPlaceInRow(int placeInRow) {
		this.placeInRow = placeInRow;
	}

	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}

	@Override
	public String toString() {
		return "R" + seatRow + "-" + placeInRow;
	}

}
