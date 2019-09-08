package com.cinema.cinema.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "halls")
public class Halls implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public Halls() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "max_places")
	private int maxPlaces;

	@Column(name = "max_vip_places")
	private int maxVipPlaces;

	@Column(name = "rows_number")
	private int rowsNumber;

	@Column(name = "column_number")
	private int columnNumber;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	private List<Sessions> listSessions = new ArrayList<Sessions>();

	public List<Sessions> getListSessions() {
		return listSessions;
	}

	public void setListSessions(List<Sessions> listSessions) {
		this.listSessions = listSessions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMaxPlaces() {
		return maxPlaces;
	}

	public void setMaxPlaces(int maxPlaces) {
		this.maxPlaces = maxPlaces;
	}

	public int getMaxVipPlaces() {
		return maxVipPlaces;
	}

	public void setMaxVipPlaces(int maxVipPlaces) {
		this.maxVipPlaces = maxVipPlaces;
	}

	public int getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(int rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

}
