package br.com.dimd.brsaude.exception;

public class NotFoundObjectException extends Exception{

	public NotFoundObjectException() {
		super("Objeto não foi encontrado no banco de dados.");
		// TODO Auto-generated constructor stub
	}

	public NotFoundObjectException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public NotFoundObjectException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public NotFoundObjectException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
