package no.hvl.dat107.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig_JPA")
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

	@OneToOne(mappedBy = "sjef")
	private Avdeling avdelingSjef;

	@ManyToOne
	@JoinColumn(name = "avdeling", referencedColumnName = "avdelingsid")
	private Avdeling avdeling;

	@OneToMany(mappedBy = "ansatt")
	private List<ProsjektDeltagelse> deltagelser;

	public Ansatt() {

	}

	public Ansatt(int ansattID, String brukerNavn, String fornavn, String etternavn, LocalDate ansattDato,
			String stilling, int manedslonn, Avdeling avdeling) {
		super();
		this.ansattID = ansattID;
		this.brukerNavn = brukerNavn;
		this.forNavn = fornavn;
		this.etterNavn = etternavn;
		this.ansattDato = ansattDato;
		this.stilling = stilling;
		this.manedslonn = manedslonn;
		this.avdeling = avdeling;
	}

	public void skrivUtMedProsjekter() {
		System.out.println();
		System.out.println(toString());
		for (ProsjektDeltagelse deltagelser : deltagelser) {
			System.out.println(deltagelser.toString());
		}
	}

	public void leggTilProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektdeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}

	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
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

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	@Override
	public String toString() {
		return "Ansatt [ansattid=" + ansattID + ", brukerNavn=" + brukerNavn + ", forNavn=" + forNavn + ", etterNavn="
				+ etterNavn + ", ansattDato=" + ansattDato + ", stilling=" + stilling + ", manedslonn=" + manedslonn
				+ "avdeling=" + avdeling + "]";
	}
}