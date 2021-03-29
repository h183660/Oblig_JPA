package no.hvl.dat107.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import no.hvl.dat107.entity.Ansatt;

public class AnsattDAO {
	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("ObligPU");
	}

	/**
	 * Henter en ansatt ut fra databasen
	 * 
	 * @param id
	 * @return et Ansatt Objekt
	 */
	public Ansatt hentAnsattMedID(int id) {
		EntityManager em = emf.createEntityManager();
		Ansatt ansatt = null;

		try {
			ansatt = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return ansatt;
	}

	/**
	 * Henter en ansatt ut fra databasen
	 * 
	 * @param brukernavn
	 * @return et Ansatt Objekt
	 */
	public Ansatt hentAnsattMedBrukernavn(String brukernavn) {
		EntityManager em = emf.createEntityManager();
		Ansatt ansatt = null;

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukerNavn = :brukerNavn",
					Ansatt.class);
			query.setParameter("brukerNavn", brukernavn);
			ansatt = query.getSingleResult();
		} finally {
			em.close();
		}
		return ansatt;
	}

	/**
	 * Henter alle ansatte i databasen
	 * 
	 * @return en liste (List) over alle ansatte som Ansatt objekter
	 */
	public List<Ansatt> listAlleAnsatte() {
		EntityManager em = emf.createEntityManager();

		try {
			TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a", Ansatt.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Oppdaterer en ansatt sin lønn i databasen.
	 * 
	 * @param ansatt objekt som skal gjøres endringer på.
	 * @param lonn,  som er den nye lønnen til den ansatte
	 */
	public void oppdaterLonn(Ansatt ansatt, int lonn) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansatt.getAnsattID());
			a.setManedslonn(lonn);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Oppdaterer en ansatt sin stilling i databasen.
	 * 
	 * @param ansatt    objekt som skal gjøres endringer på.
	 * @param stilling, som er den nye stillingen til den ansatte
	 */
	public void oppdaterStilling(Ansatt ansatt, String stilling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansatt.getAnsattID());
			a.setStilling(stilling);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Legger til en ny rad i ansatt-tabellen i databasen.
	 * 
	 * @param ansatt objekt som skal legges til
	 */
	public void leggTil(Ansatt ansatt) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(ansatt); // Oppretter en ny rad i databasen

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
