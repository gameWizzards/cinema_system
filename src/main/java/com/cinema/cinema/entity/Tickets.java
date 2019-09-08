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
@Table(name = "tickets")
public class Tickets implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	// TODO sessions, seats

	@Column(name = "final_price")
	private Float finalPrice;

	public Tickets() {
	}

	public Tickets(Float finalPrice, Sessions session, Seats seat) {
		this.finalPrice = finalPrice;
		this.session = session;
		this.seat = seat;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Session", referencedColumnName = "ID")
	private Sessions session;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Seat", referencedColumnName = "ID")
	private Seats seat;

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

	public Sessions getSession() {
		return session;
	}

	public void setSession(Sessions session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}
}
