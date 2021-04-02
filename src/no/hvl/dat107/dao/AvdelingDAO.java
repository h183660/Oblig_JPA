package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class AvdelingDAO {
	private EntityManagerFactory emf;

	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("ObligPU");
	}

	/**
	 * Finner en avdeling
	 * 
	 * @param id til avdelingen
	 * @return Avdeling objekt
	 */
	public Avdeling finnAvdelingMedID(int id) {
		EntityManager em = emf.createEntityManager();
		Avdeling avdeling = null;

		try {
			avdeling = em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
		return avdeling;
	}

	/**
	 * Lister alle avdelingene
	 * 
	 * @return En liste av avdelings-objekter
	 */
	public List<Avdeling> listAlleAvdelinger() {
		EntityManager em = emf.createEntityManager();
		List<Avdeling> avdelinger;

		try {
			TypedQuery<Avdeling> query = em.createQuery("SELECT a FROM Avdeling a", Avdeling.class);
			avdelinger = query.getResultList();
		} finally {
			em.close();
		}
		return avdelinger;
	}

	/**
	 * legger til en ny rad i avdelings tabellen
	 * 
	 * @param avdeling-objekt
	 * @param ansatt-objekt   som skal bli sjef
	 */
	public void leggTilAvdeling(Avdeling avdeling, Ansatt sjef) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.persist(avdeling);

			em.merge(sjef).setAvdeling(avdeling);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
