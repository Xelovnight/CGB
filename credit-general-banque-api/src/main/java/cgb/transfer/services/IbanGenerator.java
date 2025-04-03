package cgb.transfer.services;
import java.util.Random;

public class IbanGenerator {

    private static final String COUNTRY_CODE = "FR";
    private static final int IBAN_LENGTH = 27; // Longueur totale d'un IBAN français
    private static final Random RANDOM = new Random();

    /**
     * Génère un IBAN fictif valide pour la France.
     *
     * @return Un IBAN fictif valide.
     */
    public static String generateValidIban() {
        // Générer un BBAN fictif (sans les chiffres de contrôle)
        StringBuilder bban = new StringBuilder(IBAN_LENGTH - 4); // 4 = 2 lettres pays + 2 chiffres de contrôle
        for (int i = 0; i < bban.capacity(); i++) {
            bban.append(RANDOM.nextInt(10)); // Utiliser des chiffres pour simplifier car on peut aussi utiliser des lettres.
        }

        // Calculer les chiffres de contrôle
        String checkDigits = calculateCheckDigits(COUNTRY_CODE, bban.toString());

        // Assembler l'IBAN complet
        return COUNTRY_CODE + checkDigits + bban.toString();
    }

    /**
     * Calcule les chiffres de contrôle pour un IBAN donné.
     *
     * @param countryCode Le code du pays.
     * @param bban Le BBAN (sans les chiffres de contrôle).
     * @return Les chiffres de contrôle calculés.
     */
    private static String calculateCheckDigits(String countryCode, String bban) {
        // Déplacer les 4 premiers caractères à la fin et convertir les lettres en chiffres (A=10, B=11, ..., Z=35)
        StringBuilder rearranged = new StringBuilder();
        rearranged.append(bban).append(countryCode);

        // Convertir les lettres en chiffres
        for (int i = 0; i < rearranged.length(); i++) {
            char c = rearranged.charAt(i);
            if (Character.isLetter(c)) {
                rearranged.setCharAt(i, (char) (c - 'A' + '0' + 10));
            }
        }

        // Calculer le modulo 97
        long number = 0;
        for (char c : rearranged.toString().toCharArray()) {
            number = number * 10 + Character.getNumericValue(c);
        }

        long remainder = number % 97;
        int checkDigits = (int) (98 - remainder);

        // Formater les chiffres de contrôle en deux chiffres
        return String.format("%02d", checkDigits);
    }

    public static void main(String[] args) {
        String validIban = generateValidIban();
        System.out.println("Generated Valid IBAN: " + validIban);
    }
}

