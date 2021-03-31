package no.hvl.dat107.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.ProsjektDeltagelse;
import no.hvl.dat107.entity.ProsjektDeltagelsePK;

public class ProsjektDeltagelseDAO {
	private EntityManagerFactory emf;

	public ProsjektDeltagelseDAO() {
		emf = Persistence.createEntityManagerFactory("ObligPU");
	}

	/**
	 * Finner en ansatt sin deltagelse i et prosjekt.
	 * 
	 * @param Prosjekt objekt
	 * @param Ansatt   objekt
	 * @return Et prosjektdeltagelse objekt
	 */
	public ProsjektDeltagelse finnProsjektDeltagelse(Ansatt ansatt, Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		ProsjektDeltagelse prosjektDeltagelse;

		ProsjektDeltagelsePK pk = new ProsjektDeltagelsePK(ansatt.getAnsattID(), prosjekt.getProsjektid());
		try {
			prosjektDeltagelse = em.find(ProsjektDeltagelse.class, pk);
		} finally {
			em.close();
		}
		return prosjektDeltagelse;

	}

	/**
	 * Legger til en linje i ProsjektDeltagelse tabellen.
	 * 
	 * @param prosjektDeltagelse
	 */
	public void leggTilProsjektDeltagelse(ProsjektDeltagelse prosjektDeltagelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(prosjektDeltagelse);

			tx.commit();
		} catch (Throwable e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Legger til timer til en ansatt i et prosjekt.
	 * 
	 * @param prosjektDeltagelse
	 * @param timer
	 */
	public void leggTilTimer(ProsjektDeltagelse prosjektDeltagelse, int timer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			prosjektDeltagelse = em.merge(prosjektDeltagelse);
			prosjektDeltagelse.setTimer(prosjektDeltagelse.getTimer() + timer);

			tx.commit();
		} catch (Throwable e) {
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
