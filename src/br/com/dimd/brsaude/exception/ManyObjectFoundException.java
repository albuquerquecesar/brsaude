package br.com.dimd.brsaude.exception;

public class ManyObjectFoundException extends Exception{

	public ManyObjectFoundException() {
		super("Existe mais de objeto!");
		// TODO Auto-generated constructor stub
	}

	public ManyObjectFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

	public ManyObjectFoundException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	public ManyObjectFoundException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
