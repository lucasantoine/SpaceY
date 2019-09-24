package fr.spacey.exceptions;

public class TypeUnknownException extends Exception {

	private static final long serialVersionUID = 1L;

	public TypeUnknownException(String s) {
		super(s);
	}
	
	public TypeUnknownException() {
		super("Ce type n'existe pas");
	}
}
