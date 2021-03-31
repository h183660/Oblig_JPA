package no.hvl.dat107.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig_JPA")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int avdelingsid;
	private String navn;

	@OneToOne
	@JoinColumn(name = "sjef", referencedColumnName = "ansattID")
	private Ansatt sjef;

	@OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, orphanRemoval = true)
	private List<Ansatt> ansatte;

	public Avdeling() {
		this.ansatte = new ArrayList<>();
	}

	public Avdeling(int avdelingsid, String navn, Ansatt sjef) {
		super();
		this.avdelingsid = avdelingsid;
		this.navn = navn;
		this.sjef = sjef;
		this.ansatte = new ArrayList<>();
		ansatte.add(sjef);
	}

	public void leggTil(Ansatt ansatt) {
		ansatte.add(ansatt);
	}

	public int getAvdelingsid() {
		return avdelingsid;
	}

	public void setAvdelingsid(int avdelingsid) {
		this.avdelingsid = avdelingsid;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}

	@Override
	public String toString() {
		return "Avdeling [avdelingsid=" + avdelingsid + ", navn=" + navn + ", sjef=" + "Ansatt [ansattid="
				+ sjef.getAnsattID() + ", brukerNavn=" + sjef.getBrukerNavn() + ", forNavn=" + sjef.getForNavn()
				+ ", etterNavn=" + sjef.getEtterNavn() + "]" + "]";
	}

}