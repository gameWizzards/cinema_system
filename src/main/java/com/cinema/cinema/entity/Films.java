package com.cinema.cinema.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "films")
public class Films implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "standart_price")
	private Float standartPrice;

	@Column(name = "premier_date")
	private Date premierDate;

	@Column(name = "end_date")
	private Date endDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	private List<Sessions> listSessions = new ArrayList<Sessions>();

	public Films() {
	}

	public int getId() {
		return id;
	}

	public List<Sessions> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Sessions> listSessions) {
		this.listSessions = listSessions;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getStandartPrice() {
		return standartPrice;
	}

	public void setStandartPrice(Float standartPrice) {
		this.standartPrice = standartPrice;
	}

	public Date getPremierDate() {
		return premierDate;
	}

	public void setPremierDate(Date premierDate) {
		this.premierDate = premierDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
