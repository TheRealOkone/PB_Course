package serv.service;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//אאאאאאאאאאאאאאאאאאאאאאאאאאאאאלללללללללללללללללללל

@Repository
public class Library {
	List<Author> authors;
	List<Book> books;
	
	public Library() {
		this.authors = new ArrayList<Author>();
		this.books = new ArrayList<Book>();
	}
	
	public List<Author> getAuthors() {
		return authors; 
	}
	
	public List<Book> getBooks() {
		return books; 
	}
}
