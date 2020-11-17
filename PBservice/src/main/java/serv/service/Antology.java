package serv.service;

import java.util.ArrayList;
import java.util.List;

public class Antology {
	serv.service.Author wtiter;
	List<serv.service.Book> books;
	
	public Antology(serv.service.Author writer, List<serv.service.Book> abooks) {
		this.books = new ArrayList<serv.service.Book>();
		this.wtiter = writer;
		for (serv.service.Book b: abooks)
			if (b.writer == writer)
				this.books.add(b);
	}

	public serv.service.Author getWriter() {
		return wtiter;
	}
	
	public List<serv.service.Book> getBooks() {
		return books;
	}
}
