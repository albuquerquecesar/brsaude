package br.com.dimd.brsaude.exception;

public class SaveException extends Exception{

	
	public SaveException() {
		super("Impossível salvar o objeto!");
		// TODO Auto-generated constructor stub
	}

	public SaveException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public SaveException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public SaveException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
