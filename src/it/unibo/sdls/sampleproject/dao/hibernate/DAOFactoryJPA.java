package it.unibo.sdls.sampleproject.dao.hibernate;

import it.unibo.sdls.sampleproject.dao.*;

public class DAOFactoryJPA extends TxDAOFactory{

	@Override
	protected void terminate() {
		
	}

	@Override
	public AuthorDAO getAuthorDAO() {
		return AuthorDAOJPA.getInstance();
	}

	@Override
	public BookDAO getBookDAO() {
		return BookDAOJPA.getInstance();
	}

	@Override
	public PublisherDAO getPublisherDAO() {
		return PublisherDAOJPA.getInstance();
	}

}
