package cgb.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerTransferApp {

	public static void main(String[] args) {
		SpringApplication.run(ServerTransferApp.class, args);
		try {
			Utilitaire u = Utilitaire.getInstanceValidator();

			String touc = "FR7630004000031234567890143";

			System.out.println("resultat !!!!!!!!! : " + u.isIbanStructureValide(touc));

			System.out.println("resultat is valid !!!!!!!!! : " + u.isIbanValide(touc));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
