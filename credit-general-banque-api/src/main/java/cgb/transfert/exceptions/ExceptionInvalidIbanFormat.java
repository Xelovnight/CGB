package cgb.transfert.exceptions;

import cgb.transfert.exceptions.ExceptionInvalidUnCheckableIban.FailureType;

public class ExceptionInvalidIbanFormat extends ExceptionInvalideIBAN{

private static final long serialVersionUID = 1L;
	
	public enum FailureType{INVALIDCARACTERCOUNT, INVALIDCOUNTRYCODE};
	
	public ExceptionInvalidIbanFormat(FailureType f) {
		super(f.name());
	}

}
