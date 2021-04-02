package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.dao.ProsjektDeltagelseDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.ProsjektDeltagelse;

public class Tekstgrensesnitt {
	AnsattDAO ansattDAO = new AnsattDAO();
	AvdelingDAO avdelingDAO = new AvdelingDAO();
	ProsjektDAO prosjektDAO = new ProsjektDAO();
	ProsjektDeltagelseDAO prosjektDeltagelseDAO = new ProsjektDeltagelseDAO();

	// Meny, her velger man hva man vil gjøre i databasen.
	public static void Meny() {

		Scanner scan = new Scanner(System.in);
		Tekstgrensesnitt tgr = new Tekstgrensesnitt();
		boolean run = true;

		while (run) {
			System.out.println("-------------------");
			System.out.println("|Hva vil du gjøre?|");
			System.out.println("-------------------");
			System.out.println("1: Søke etter ansatt på id");
			System.out.println("2: Søke etter ansatt på brukernavn");
			System.out.println("3: Søke etter avdeling på id");
			System.out.println("4: Utlisting av alle ansatte");
			System.out.println("5: Utlisting av alle ansatte i en avdeling");
			System.out.println("6: Oppdatere en ansatt sin stilling");
			System.out.println("7: Oppdatere en ansatt sin lønn");
			System.out.println("8: Oppdatere en ansatt sin avdeling");
			System.out.println("9: Legge til en ny ansatt");
			System.out.println("10: Legge til en ny avdeling");
			System.out.println("11: Legge til et nytt prosjekt");
			System.out.println("12: Registrere prosjektdeltagelse");
			System.out.println("13: Føre timer for en ansatt");
			System.out.println("14: Skriv ut info om et prosjekt");
			System.out.println("0: Exit.");

			String valg = scan.nextLine();

			switch (valg) {
			case "1":
				System.out.println("Søker etter ansatt på id.");
				tgr.finnAnsattMedID();
				break;
			case "2":
				System.out.println("Søker etter ansatt på brukernavn.");
				tgr.finnAnsattMedBrukernavn();
				break;
			case "3":
				System.out.println("Søker etter en avdeling på id.");
				tgr.finnAvdelingMedID();
				break;
			case "4":
				System.out.println("Utlister alle ansatte.");
				tgr.skrivUtAlleAnsatte();
				break;
			case "5":
				System.out.println("Utlister alle ansatte i en enkelt avdeling.");
				tgr.skrivUtAlleAnsatteIAvdeling();
				break;
			case "6":
				System.out.println("Oppdaterer en ansatt sin stilling.");
				tgr.oppdaterStilling();
				break;
			case "7":
				System.out.println("Oppdaterer en ansatt sin lønn.");
				tgr.oppdaterLonn();
				break;
			case "8":
				System.out.println("Oppdaterer en ansatt sin avdeling.");
				tgr.oppdaterAvdeling();
				break;
			case "9":
				System.out.println("Legger til en ny ansatt.");
				tgr.leggTilAnsatt();
				break;
			case "10":
				System.out.println("Legger til en ny avdeling.");
				tgr.leggTilAvdeling();
				break;
			case "11":
				System.out.println("Legger til et nytt prosjekt.");
				tgr.leggTilProsjekt();
				break;
			case "12":
				System.out.println("Registrerer prosjektdeltagelse.");
				tgr.leggTilProsjektDeltagelse();
				break;
			case "13":
				System.out.println("Fører timer til en ansatte.");
				tgr.leggTilTimer();
				break;
			case "14":
				System.out.println("Skriver ut info om et prosjekt.");
				tgr.skrivUtInfoProsjekt();
				break;
			case "0":
				System.out.println("Avslutter...");
				run = false;
				break;
			default:
				System.out.println("Ukjent komando, prøv igjen.");
			}
		}

		scan.close();
	}

	private void skrivUtInfoProsjekt() {
		Prosjekt prosjekt = finnProsjektMedID();
		prosjektDAO.skrivUtInfo(prosjekt);
	}

	private Ansatt finnAnsattMedID() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Hva er IDnr til den ansatte?");
		String id = scan.nextLine();

		Ansatt ansatt = ansattDAO.hentAnsattMedID(Integer.parseInt(id));

		if (ansatt != null) {
			System.out.println("Ansatt med id " + id + " funnet.");
			System.out.println(ansatt.toString());
			return ansatt;
		} else {
			System.out.println("Ansatt med id " + id + " ikke funnet.");
			return null;
		}
	}

	private Ansatt finnAnsattMedBrukernavn() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Hva er IDnr til den ansatte?");
		String brukernavn = scan.nextLine();

		Ansatt ansatt = ansattDAO.hentAnsattMedBrukernavn(brukernavn);

		if (ansatt != null) {
			System.out.println("Ansatt med brukernavn " + brukernavn + " funnet.");
			System.out.println(ansatt.toString());
			return ansatt;
		} else {
			System.out.println("Ansatt med brukernavn " + brukernavn + " ikke funnet.");
			return null;
		}
	}

	private Avdeling finnAvdelingMedID() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Hva er IDnr til avdelingen?");
		String id = scan.nextLine();

		Avdeling avdeling = avdelingDAO.finnAvdelingMedID(Integer.parseInt(id));

		if (avdeling != null) {
			System.out.println("Avdeling med id " + id + " funnet.");
			System.out.println(avdeling.toString());
			return avdeling;
		} else {
			System.out.println("Avdeling med id " + id + " ikke funnet.");
			return null;
		}
	}

	private Prosjekt finnProsjektMedID() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Hva er IDnr til prosjektet?");
		String id = scan.nextLine();

		Prosjekt prosjekt = prosjektDAO.finnProsjektMedID(Integer.parseInt(id));

		if (prosjekt != null) {
			System.out.println("Prosjekt med id " + id + " funnet.");
			System.out.println(prosjekt.toString());
			return prosjekt;
		} else {
			System.out.println("Prosjekt med id " + id + " ikke funnet.");
			return null;
		}
	}

	private void skrivUtAlleAnsatte() {
		List<Ansatt> ansatte = ansattDAO.listAlleAnsatte();

		for (Ansatt ansatt : ansatte) {
			System.out.println(ansatt);
		}
	}

	private void skrivUtAlleAvdelinger() {
		List<Avdeling> avdelinger = avdelingDAO.listAlleAvdelinger();

		for (Avdeling avdeling : avdelinger) {
			System.out.println(avdeling);
		}
	}

	private void skrivUtAlleAnsatteIAvdeling() {
		skrivUtAlleAvdelinger();
		System.out.println("Hvilken avdeling velger du?");
		Avdeling avdeling = finnAvdelingMedID();
		List<Ansatt> ansatte = ansattDAO.listAlleAnsatteIAvdeling(avdeling);

		for (Ansatt ansatt : ansatte) {
			System.out.println(ansatt);
		}
	}

	private void oppdaterLonn() {
		Ansatt ansatt = finnAnsattMedID();
		Scanner scan = new Scanner(System.in);

		if (ansatt != null) {
			System.out.println("Hva er den nye månedslønnen til " + ansatt.getAnsattID() + "?");
			String lonn = scan.nextLine();
			if (Integer.parseInt(lonn) > 0) {
				ansattDAO.oppdaterLonn(ansatt, Integer.parseInt(lonn));
				System.out.println("Lønnen til " + ansatt.getAnsattID() + " er oppdatert til " + lonn);
			} else {
				System.out.println("Ugyldig lønn.");
			}
		}
	}

	private void oppdaterStilling() {
		Ansatt ansatt = finnAnsattMedID();
		Scanner scan = new Scanner(System.in);

		if (ansatt != null) {
			System.out.println("Hva er den nye stillingen til " + ansatt.getAnsattID() + "?");
			String stilling = scan.nextLine();

			ansattDAO.oppdaterStilling(ansatt, stilling);
			System.out.println("Stillingen til " + ansatt.getAnsattID() + " er oppdatert til " + stilling);
		}
	}

	private void oppdaterAvdeling() {
		Ansatt ansatt = finnAnsattMedID();
		Avdeling avdeling = finnAvdelingMedID();
		Scanner scan = new Scanner(System.in);

		if (ansatt != null && avdeling != null) {

			ansattDAO.oppdaterAvdeling(ansatt, avdeling);
			System.out.println("Avdeling til " + ansatt.getAnsattID() + " er oppdatert til " + avdeling);
		}
	}

	private ProsjektDeltagelse finnProsjektDeltagelse() {
		System.out.println("Finner prosjektdeltagelse");
		Ansatt ansatt = finnAnsattMedID();
		Prosjekt prosjekt = finnProsjektMedID();
		return prosjektDeltagelseDAO.finnProsjektDeltagelse(ansatt, prosjekt);

	}

	private void leggTilTimer() {
		System.out.println("Legger til timer til prosjektdeltagelse");
		ProsjektDeltagelse prosjektDeltagelse = finnProsjektDeltagelse();

		Scanner scan = new Scanner(System.in);

		System.out.println("Hvor mange timer vil du legge til?");
		String timer = scan.nextLine();
		prosjektDeltagelseDAO.leggTilTimer(prosjektDeltagelse, Integer.parseInt(timer));
	}

	private void leggTilAnsatt() {
		Scanner scan = new Scanner(System.in);
		Ansatt a = new Ansatt();

		System.out.println("Velg et brukernavn (Initsialer, maks 4 bokstaver) : ");
		String brukernavn = scan.nextLine();
		a.setBrukerNavn(brukernavn);

		System.out.println("Hva er ditt fornavn?");
		String fornavn = scan.nextLine();
		a.setForNavn(fornavn);

		System.out.println("Hva er ditt etternavn?");
		String etternavn = scan.nextLine();
		a.setEtterNavn(etternavn);

		// Bruker bare dagens dato for å slippe å parse osv.
		a.setAnsattDato(LocalDate.now());

		System.out.println("Hva er din stilling?");
		String stilling = scan.nextLine();
		a.setStilling(stilling);

		System.out.println("Hva er din månedslønn?");
		int monedslonn = Integer.parseInt(scan.nextLine());
		a.setManedslonn(monedslonn);

		System.out.println("Hva avdeling tilhører du?");
		int avdeling = Integer.parseInt(scan.nextLine());
		a.setAvdeling(avdelingDAO.finnAvdelingMedID(avdeling));

		// Id blir her autogenerert
		ansattDAO.leggTil(a);

	}

	private void leggTilProsjekt() {
		Scanner scan = new Scanner(System.in);
		Prosjekt prosjekt = new Prosjekt();

		System.out.println("Hva skal prosjektet hete?");
		String navn = scan.nextLine();
		prosjekt.setNavn(navn);

		System.out.println("Legg til en beskrivelse");
		String beskrivelse = scan.nextLine();
		prosjekt.setBeskrivelse(beskrivelse);

		prosjektDAO.leggTilProsjekt(prosjekt);
	}

	private void leggTilProsjektDeltagelse() {
		Scanner scan = new Scanner(System.in);
		ProsjektDeltagelse prosjektdeltagelse = new ProsjektDeltagelse();

		System.out.println("En prosjektdeltagelse må være knyttet til en ansatt.");
		Ansatt ansatt = finnAnsattMedID();
		prosjektdeltagelse.setAnsattid(ansatt);

		System.out.println("En prosjektdeltagelse må være knyttet til et prosjekt.");
		Prosjekt prosjekt = finnProsjektMedID();
		prosjektdeltagelse.setProsjektid(prosjekt);

		System.out.println("Hva er rollen til den ansatte i dette prosjektet?");
		String rolle = scan.nextLine();
		prosjektdeltagelse.setRolle(rolle);
		prosjektdeltagelse.setTimer(0);

		prosjektDeltagelseDAO.leggTilProsjektDeltagelse(prosjektdeltagelse);

	}

	private void leggTilAvdeling() {
		Scanner scan = new Scanner(System.in);
		Avdeling avdeling = new Avdeling();
		Ansatt sjef = new Ansatt();

		System.out.println("En avdeling må ha en sjef, hva er idnr til sjefen?");
		sjef = finnAnsattMedID();
		avdeling.setSjef(sjef);

		System.out.println("Hva er navnet på avdelingen?");
		String navn = scan.nextLine();
		avdeling.setNavn(navn);

		avdelingDAO.leggTilAvdeling(avdeling, sjef);
	}
}
