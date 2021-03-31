package no.hvl.dat107.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig_JPA")
@IdClass(ProsjektDeltagelsePK.class)
public class ProsjektDeltagelse {

	@Id
	@ManyToOne
	@JoinColumn(name = "prosjektid")
	private Prosjekt prosjekt;

	@Id
	@ManyToOne
	@JoinColumn(name = "ansattID")
	private Ansatt ansatt;

	private String rolle;
	private int timer;

	public ProsjektDeltagelse() {

	}

	public ProsjektDeltagelse(Prosjekt prosjekt, Ansatt ansatt, String rolle, int timer) {
		super();
		this.prosjekt = prosjekt;
		this.ansatt = ansatt;
		this.rolle = rolle;
		this.timer = timer;
	}

	public Prosjekt getProsjekt() {
		return prosjekt;
	}

	public void setProsjektid(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsattid(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return "ProsjektDeltagelse [prosjekt=" + prosjekt + ", ansatt=" + ansatt + ", rolle=" + rolle + ", timer="
				+ timer + "]";
	}

}
