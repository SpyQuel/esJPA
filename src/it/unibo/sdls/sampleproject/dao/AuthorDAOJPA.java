package it.unibo.sdls.sampleproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AuthorDAOJPA implements AuthorDAO{
	@PersistenceContext(name="sistemiDistribuitiLS")
	private EntityManager em;
	private static AuthorDAOJPA instance = null;
	
	private AuthorDAOJPA() {
		
	}
	
	public static AuthorDAOJPA getInstance() {
		if(instance == null)
			instance = new AuthorDAOJPA();
		return instance;
	}
	
	public int insertAuthor(Author author) {
		em.persist(author);
		return 0;
	}

	public int removeAuthorByName(String name) {
		em.createQuery(
		 "REMOVE a FROM Author a WHERE a.name LIKE :name")
		 .setParameter("name", name)
		 .getSingleResult();
		return 0;
	}

	public int removeAuthorById(int id) {
		em.remove(em.find(Author.class, id));
		return 0;
	}

	public Author findAuthorByName(String name) {
		return (Author) em.createQuery(
				 "SELECT a FROM Author a WHERE a.name LIKE :name")
				 .setParameter("name", name)
				 .getSingleResult();
	}

	public Author findAuthorById(int id) {
		return em.find(Author.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Author> findAllAuthors() {
		return (List<Author>) em.createQuery("SELECT a FROM Author a")
				.getResultList();
	}

}
