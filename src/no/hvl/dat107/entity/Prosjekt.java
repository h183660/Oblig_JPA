package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig_JPA")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektid;
	private String navn;
	private String beskrivelse;

	@OneToMany(mappedBy = "prosjekt")
	private List<ProsjektDeltagelse> deltagelser;

	public Prosjekt() {

	}

	public Prosjekt(int prosjektid, String navn, String beskrivelse) {
		super();
		this.prosjektid = prosjektid;
		this.navn = navn;
		this.beskrivelse = beskrivelse;
	}

	public void skrivUtMedAnsatte() {
		System.out.println();
		System.out.println(toString());
		for (ProsjektDeltagelse prosjektdeltagelse : deltagelser) {
			System.out.println(prosjektdeltagelse.toString());
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

	public int getProsjektid() {
		return prosjektid;
	}

	public void setProsjektid(int prosjektid) {
		this.prosjektid = prosjektid;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	@Override
	public String toString() {
		return "Prosjekt [prosjektid=" + prosjektid + ", navn=" + navn + ", beskrivelse=" + beskrivelse + "]";
	}

}
