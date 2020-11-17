package serv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Library")
public class restfull {

	private Library myLibrary;

	@Autowired
	public restfull(Library myLibrary) {
	    this.myLibrary = myLibrary;
	    this.myLibrary.authors.add(new Author("���","�������","����������"));
	    this.myLibrary.books.add(new Book("����� � ���", this.myLibrary.authors.get(0)));
	    this.myLibrary.books.add(new Book("���� ��������", this.myLibrary.authors.get(0)));
	    this.myLibrary.books.add(new Book("����� ����", this.myLibrary.authors.get(0)));
	    this.myLibrary.authors.add(new Author("Ը���","�����������","����������"));
	    this.myLibrary.books.add(new Book("������ ����������", this.myLibrary.authors.get(1)));
	    this.myLibrary.books.add(new Book("�����", this.myLibrary.authors.get(1)));
	    this.myLibrary.books.add(new Book("����", this.myLibrary.authors.get(1)));
	    this.myLibrary.authors.add(new Author("������","�������",""));
	    this.myLibrary.books.add(new Book("�� ���", this.myLibrary.authors.get(2)));
	    this.myLibrary.books.add(new Book("������� ��������", this.myLibrary.authors.get(2)));
	    this.myLibrary.books.add(new Book("���� �������", this.myLibrary.authors.get(2)));
	}

    @RequestMapping(
    		value = "/",
    		method = RequestMethod.GET)
    public Library AsIs() {
        return myLibrary;
    }

    @RequestMapping(
    		value = "/Books",
    		method = RequestMethod.GET)
    public List<Book> getBooks(@RequestParam(name="search", required = false) String search) {
    	if (search != null) {
    		List<Book> sr = new ArrayList<Book>();
    		for (Book b : myLibrary.books)
    			if (b.getCaption().toLowerCase().contains(search.toLowerCase()))
    				sr.add(b);
    		return sr;
    	} else
    		return myLibrary.books;
    }

    @RequestMapping(
    		value = "/Authors",
    		method = RequestMethod.GET)
    public List<Author> getAuthors(@RequestParam(name="search", required = false) String search) {
    	if (search != null) {
    		List<Author> sr = new ArrayList<Author>();
    		for (Author a : myLibrary.authors)
    			if (a.getLastName().toLowerCase().contains(search.toLowerCase())
    			 || a.getName().toLowerCase().contains(search.toLowerCase())
    			 || a.getPatronym().toLowerCase().contains(search.toLowerCase()))
    				sr.add(a);
    		return sr;
    	} else
    		return myLibrary.authors;
    }

    @RequestMapping(value = "/Books/{caption}",
    		method = RequestMethod.GET)
    public Book getBook(@PathVariable("caption") String caption) {
    	for (Book b: myLibrary.books)
    		if (b.getCaption().equalsIgnoreCase(caption))
    	        return b;
    	return null;
    }

    @RequestMapping(value = "/Authors/{LastName}",
    		method = RequestMethod.GET)
    public Antology getAntology(@PathVariable("LastName") String LastName) {
    	for (Author a: myLibrary.authors)
    		if (a.getLastName().equalsIgnoreCase(LastName))
    	        return new Antology(a, myLibrary.books);
    	return new Antology(new Author(null,LastName,null), myLibrary.books);
    }
}
