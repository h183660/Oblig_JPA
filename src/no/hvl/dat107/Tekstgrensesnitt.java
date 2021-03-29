package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.entity.Ansatt;

public class Tekstgrensesnitt {
	AnsattDAO ansattDAO = new AnsattDAO();

	//Meny, her velger man hva man vil gjøre i databasen.
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
			System.out.println("3: Utlisting av alle ansatte");
			System.out.println("4: Oppdatere en ansatt sin stilling");
			System.out.println("5: Oppdatere en ansatt sin lønn");
			System.out.println("6: Legge til en ny ansatt");
			System.out.println("0: Exit.");

			int valg = scan.nextInt();

			switch (valg) {
			case 1:
				System.out.println("Søker etter ansatt på id.");
				tgr.finnAnsattMedID();
				break;
			case 2:
				System.out.println("Søker etter ansatt på brukernavn.");
				tgr.finnAnsattMedBrukernavn();
				break;
			case 3:
				System.out.println("Utlister alle ansatte.");
				tgr.skrivUtAlleAnsatte();
				break;
			case 4:
				System.out.println("Oppdaterer en ansatt sin stilling.");
				tgr.oppdaterStilling();
				break;
			case 5:
				System.out.println("Oppdaterer en ansatt sin lønn.");
				tgr.oppdaterLonn();
				break;
			case 6:
				System.out.println("Legger til en ny ansatt.");
				tgr.leggTilAnsatt();
				break;
			case 0:
				System.out.println("Avslutter...");
				run = false;
				break;
			default:
				System.out.println("Ukjent komando, prøv igjen.");
			}
		}

		scan.close();
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

	private void skrivUtAlleAnsatte() {
		// Henter listen av ansatte
		List<Ansatt> ansatte = ansattDAO.listAlleAnsatte();

		// Skriver ut en enkelt linje for hver ansatt i listen
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

		//Bruker bare dagens dato for å slippe å parse osv.
		a.setAnsattDato(LocalDate.now());

		System.out.println("Hva er din stilling?");
		String stilling = scan.nextLine();
		a.setStilling(stilling);

		System.out.println("Hva er din månedslønn?");
		int monedslonn = Integer.parseInt(scan.nextLine());
		a.setManedslonn(monedslonn);

		//Id blir her autogenerert
		ansattDAO.leggTil(a);

	}
}
