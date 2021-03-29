package no.hvl.dat107.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig_JPA") // kan fjernes så lenge tabellene er i public, og navnet er likt
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ansattID;
	private String brukerNavn;
	private String forNavn;
	private String etterNavn;
	private LocalDate ansattDato;
	private String stilling;
	private int manedslonn;

	public Ansatt() {

	}

	public Ansatt(int ansattID, String brukerNavn, String fornavn, String etternavn, LocalDate ansattDato,
			String stilling, int manedslonn) {
		super();
		this.ansattID = ansattID;
		this.brukerNavn = brukerNavn;
		this.forNavn = fornavn;
		this.etterNavn = etternavn;
		this.ansattDato = ansattDato;
		this.stilling = stilling;
		this.manedslonn = manedslonn;
	}

	public int getAnsattID() {
		return ansattID;
	}

	public void setAnsattid(int ansattid) {
		this.ansattID = ansattid;
	}

	public String getBrukerNavn() {
		return brukerNavn;
	}

	public void setBrukerNavn(String brukerNavn) {
		this.brukerNavn = brukerNavn;
	}

	public String getForNavn() {
		return forNavn;
	}

	public void setForNavn(String forNavn) {
		this.forNavn = forNavn;
	}

	public String getEtterNavn() {
		return etterNavn;
	}

	public void setEtterNavn(String etterNavn) {
		this.etterNavn = etterNavn;
	}

	public LocalDate getAnsattDato() {
		return ansattDato;
	}

	public void setAnsattDato(LocalDate ansattDato) {
		this.ansattDato = ansattDato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public int getManedslonn() {
		return manedslonn;
	}

	public void setManedslonn(int manedslonn) {
		this.manedslonn = manedslonn;
	}

	@Override
	public String toString() {
		return "Ansatt [ansattid=" + ansattID + ", brukerNavn=" + brukerNavn + ", forNavn=" + forNavn + ", etterNavn="
				+ etterNavn + ", ansattDato=" + ansattDato + ", stilling=" + stilling + ", manedslonn=" + manedslonn
				+ "]";
	}
}
