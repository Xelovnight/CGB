package cgb.transfer.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.IBANValidator;

import cgb.transfer.exceptions.ExceptionInvalidIbanFormat;
import cgb.transfer.exceptions.ExceptionInvalidUnCheckableIban;

public class Utilitaire {

	private static Utilitaire instance = null;

	private Utilitaire() {
	}

	public static Utilitaire getInstanceValidator() {
		if (instance == null) {
			instance = new Utilitaire();
		}
		return instance;
	}

	public boolean isIbanStructureValide(String iban) throws ExceptionInvalidUnCheckableIban {
		String regex = "^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(iban);
		boolean matchFound = matcher.find();
		if (matchFound) {
			System.out.println("Voici la structure : " + " || Code Pays : " + getCodePays(iban) + " || Nombres de controler : " + getControlNumbers(iban) + " || Nombre de compte en banque : " + getBasicBankNumbers(iban));
			return true;
		} else {
			throw new ExceptionInvalidUnCheckableIban(ExceptionInvalidUnCheckableIban.FailureType.UNVERIFIABLE);
		}
	}

	public boolean isIbanValide(String iban) throws Exception {
		IBANValidator ibv = new IBANValidator();
		if (iban.length() == 27) {
			return ibv.isValid(iban);
		} else {
			throw new ExceptionInvalidIbanFormat(ExceptionInvalidIbanFormat.FailureType.INVALIDCARACTERCOUNT);
		}

	}

	public String getCodePays(String iban) {
		return iban.substring(0, 2);
	}
	
	public String getControlNumbers(String iban) {
		return iban.substring(2, 4);
	}

	public String getBasicBankNumbers(String iban) {
		return iban.substring(14, 25);
	}
}
