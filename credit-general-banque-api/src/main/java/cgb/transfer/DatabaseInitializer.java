package cgb.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cgb.transfer.entity.Account;
import cgb.transfer.repository.AccountRepository;
import cgb.transfer.services.IbanGenerator;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

	@Autowired
	private AccountRepository accountRepository;

	@PostConstruct
	public void init() {
		// Vérifiez si la base de données est vide avant d'insérer des données
		if (accountRepository.count() == 0) {
			insertSampleData(accountRepository);
		}
	}

	public static void insertSampleData(AccountRepository accountRepository) {
		// Insérer des comptes d'exemple
		for (int i = 0; i < 20; i++) {
			Account account = new Account();
			IbanGenerator ibanGenerator = new IbanGenerator();
			account.setAccountNumber(ibanGenerator.generateValidIban());
			account.setSolde(300.00 + (i * 100.00));
			accountRepository.save(account);
		}

		Account account2 = new Account();
		account2.setAccountNumber("FR7630001007941234567890185");
		account2.setSolde(60000.00);
		accountRepository.save(account2);

		Account account3 = new Account();
		account3.setAccountNumber("FR7630004000031234567890143");
		account3.setSolde(90000.00);
		accountRepository.save(account3);

	}
}
