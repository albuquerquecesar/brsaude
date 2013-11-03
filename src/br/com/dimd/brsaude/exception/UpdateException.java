package br.com.dimd.brsaude.exception;

public class UpdateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5224724807314914105L;

	public UpdateException() {
		super("Impossível atualizar o objeto!");
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	
}
