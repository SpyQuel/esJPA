package it.unibo.sdls.sampleproject.dao;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Author {
	@Id
	protected int id;
	
	protected String name;
	@ManyToMany(cascade=CascadeType.REMOVE, mappedBy="book")
	protected Set<Book> books;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
