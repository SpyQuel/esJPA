package it.unibo.sdls.sampleproject.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BookDAOJPA implements BookDAO{
	@PersistenceContext(name="sistemiDistribuitiLS")
	private EntityManager em;
	private static BookDAOJPA instance = null;
	
	private BookDAOJPA() {
		
	}
	
	public static BookDAOJPA getInstance() {
		if(instance == null)
			instance = new BookDAOJPA();
		return instance;
	}
	
	public int addBook(Book book) {
		em.persist(book);
		return 0;
	}

	public int deleteBook(int id) {
		em.remove(getBookById(id));
		return 0;
	}

	public Book getBookById(int id) {
		return em.find(Book.class, id);
	}

	public Book getBookByISBN10(String isbn10) {
		return (Book) em.createQuery(
				 "SELECT b FROM Book b WHERE b.isbn10 LIKE :code")
				 .setParameter("code", isbn10)
				 .getSingleResult();
	}

	public Book getBookByISBN13(String isbn13) {
		return (Book) em.createQuery(
				 "SELECT b FROM Book b WHERE b.isbn13 LIKE :code")
				 .setParameter("code", isbn13)
				 .getSingleResult();
	}

	public Book getBookByTitle(String title) {
		return (Book) em.createQuery(
				 "SELECT b FROM Book b WHERE b.title LIKE :name")
				 .setParameter("name", title)
				 .getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		return (List<Book>) em.createQuery("SELECT b FROM Book b")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooksByAuthor(Author author) {
		return (List<Book>) em.createQuery(
				 "SELECT b FROM Book b WHERE b.author LIKE :name")
				 .setParameter("name", author)
				 .getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Book> getAllBooksByPublisher(Publisher publisher) {
		return (List<Book>) em.createQuery(
				 "SELECT b FROM Book b WHERE b.publisher LIKE :name")
				 .setParameter("name", publisher)
				 .getResultList();
	}

}
