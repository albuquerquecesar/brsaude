package br.com.dimd.brsaude.exception;

public class NoUniqueObjectException extends Exception{

	public NoUniqueObjectException() {
		super("Existe mais de um objeto no banco de dados");
		// TODO Auto-generated constructor stub
	}

	public NoUniqueObjectException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public NoUniqueObjectException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public NoUniqueObjectException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
