package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.ProsjektDeltagelse;

public class ProsjektDAO {
	private EntityManagerFactory emf;

	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("ObligPU");
	}

	/**
	 * Finner et prosjekt fra søk med id.
	 * 
	 * @param id
	 * @return Prosjekt-objekt
	 */
	public Prosjekt finnProsjektMedID(int id) {
		EntityManager em = emf.createEntityManager();
		Prosjekt prosjekt;

		try {
			prosjekt = em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
		return prosjekt;

	}

	/**
	 * Legger til et nytt Prosjekt i Prosjekttabellen.
	 * 
	 * @param prosjekt
	 */
	public void leggTilProsjekt(Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(prosjekt);

			tx.commit();
		} catch (Throwable e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Skriver ut informasjon om prosjektet, inkl. liste av deltagere med rolle og
	 * timer, og totalt timetall for prosjektet
	 * 
	 * @param prosjekt
	 */
	public void skrivUtInfo(Prosjekt prosjekt) {
		prosjekt.skrivUtMedAnsatte();

		List<ProsjektDeltagelse> prosjektDeltagelse = prosjekt.getDeltagelser();

		int sumTimer = 0;
		for (ProsjektDeltagelse deltagelser : prosjektDeltagelse) {
			sumTimer += deltagelser.getTimer();
		}
		System.out.println("Totalt antall timer: " + sumTimer);
	}

}
