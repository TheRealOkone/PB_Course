package serv.service;

public class Note {
	String text;
	
	public Note(String text) {
		this.text = text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
}
