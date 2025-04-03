package cgb.transfer.exceptions;

public class ExceptionInvalidUnCheckableIban extends ExceptionInvalideIBAN{

	private static final long serialVersionUID = 1L;
	
	public enum FailureType{CRCNOTCOMPLIANT, UNVERIFIABLE};
	
	public ExceptionInvalidUnCheckableIban(FailureType f) {
		super(f.name());
	}
}
