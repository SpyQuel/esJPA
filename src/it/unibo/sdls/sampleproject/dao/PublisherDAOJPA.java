package it.unibo.sdls.sampleproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PublisherDAOJPA implements PublisherDAO{
	@PersistenceContext(name="sistemiDistribuitiLS")
	private EntityManager em;
	private static PublisherDAOJPA instance = null;
	
	private PublisherDAOJPA() {
		
	}
	
	public static PublisherDAOJPA getInstance() {
		if(instance == null)
			instance = new PublisherDAOJPA();
		return instance;
	}
	
	public int insertPublisher(Publisher publisher) {
		em.persist(publisher);
		return 0;
	}

	public Publisher findPublisherByName(String name) {
		return (Publisher) em.createQuery(
				 "SELECT p FROM Publisher p WHERE p.name LIKE :name")
				 .setParameter("name", name)
				 .getSingleResult();
	}

	public Publisher findPublisherById(int id) {
		return em.find(Publisher.class, id);
	}

	public int removePublisherByName(String name) {
		em.createQuery(
			 "REMOVE p FROM Publisher p WHERE p.name LIKE :name")
			 .setParameter("name", name)
			 .getSingleResult();
		return 0;
	}

	public int removePublisherById(int id) {
		em.remove(em.find(Publisher.class, id));
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> findAllPublishers() {
		return (List<Publisher>) em.createQuery("SELECT p FROM Publisher p")
				.getResultList();
	}

}
