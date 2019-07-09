package com.bms.module;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "screen")
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "capasity")
	private Long capasity;

	@ManyToOne
	@JoinColumn(name = "multiplex_id")
	private Multiplex multiplex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCapasity() {
		return capasity;
	}

	public void setCapasity(Long capasity) {
		this.capasity = capasity;
	}

	public Multiplex getMultiplex() {
		return multiplex;
	}

	public void setMultiplex(Multiplex multiplex) {
		this.multiplex = multiplex;
	}
	
}
