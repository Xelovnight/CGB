package cgb.transfert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    	Account account1 = new Account();
    	IbanGenerator account1Iban = new IbanGenerator();
    	account1.setAccountNumber(account1Iban.generateValidIban());
    	account1.setSolde(300.00);
    	accountRepository.save(account1);

    	Account account2 = new Account();
    	IbanGenerator account2Iban = new IbanGenerator();
    	account2.setAccountNumber(account2Iban.generateValidIban());
    	account2.setSolde(400.00);
    	accountRepository.save(account2);

    	Account account3 = new Account();
    	IbanGenerator account3Iban = new IbanGenerator();
    	account3.setAccountNumber(account3Iban.generateValidIban());
    	account3.setSolde(500.00);
    	accountRepository.save(account3);

    	Account account4 = new Account();
    	IbanGenerator account4Iban = new IbanGenerator();
    	account4.setAccountNumber(account4Iban.generateValidIban());
    	account4.setSolde(600.00);
    	accountRepository.save(account4);

    	Account account5 = new Account();
    	IbanGenerator account5Iban = new IbanGenerator();
    	account5.setAccountNumber(account5Iban.generateValidIban());
    	account5.setSolde(700.00);
    	accountRepository.save(account5);

    	Account account6 = new Account();
    	IbanGenerator account6Iban = new IbanGenerator();
    	account6.setAccountNumber(account6Iban.generateValidIban());
    	account6.setSolde(800.00);
    	accountRepository.save(account6);

    	Account account7 = new Account();
    	IbanGenerator account7Iban = new IbanGenerator();
    	account7.setAccountNumber(account7Iban.generateValidIban());
    	account7.setSolde(900.00);
    	accountRepository.save(account7);

    	Account account8 = new Account();
    	IbanGenerator account8Iban = new IbanGenerator();
    	account8.setAccountNumber(account8Iban.generateValidIban());
    	account8.setSolde(1000.00);
    	accountRepository.save(account8);

    	Account account9 = new Account();
    	IbanGenerator account9Iban = new IbanGenerator();
    	account9.setAccountNumber(account9Iban.generateValidIban());
    	account9.setSolde(1100.00);
    	accountRepository.save(account9);

    	Account account10 = new Account();
    	IbanGenerator account10Iban = new IbanGenerator();
    	account10.setAccountNumber(account10Iban.generateValidIban());
    	account10.setSolde(1200.00);
    	accountRepository.save(account10);

    	Account account11 = new Account();
    	IbanGenerator account11Iban = new IbanGenerator();
    	account11.setAccountNumber(account11Iban.generateValidIban());
    	account11.setSolde(1300.00);
    	accountRepository.save(account11);

    	Account account12 = new Account();
    	IbanGenerator account12Iban = new IbanGenerator();
    	account12.setAccountNumber(account12Iban.generateValidIban());
    	account12.setSolde(1400.00);
    	accountRepository.save(account12);

    	Account account13 = new Account();
    	IbanGenerator account13Iban = new IbanGenerator();
    	account13.setAccountNumber(account13Iban.generateValidIban());
    	account13.setSolde(1500.00);
    	accountRepository.save(account13);

    	Account account14 = new Account();
    	IbanGenerator account14Iban = new IbanGenerator();
    	account14.setAccountNumber(account14Iban.generateValidIban());
    	account14.setSolde(1600.00);
    	accountRepository.save(account14);

    	Account account15 = new Account();
    	IbanGenerator account15Iban = new IbanGenerator();
    	account15.setAccountNumber(account15Iban.generateValidIban());
    	account15.setSolde(1700.00);
    	accountRepository.save(account15);

    	Account account16 = new Account();
    	IbanGenerator account16Iban = new IbanGenerator();
    	account16.setAccountNumber(account16Iban.generateValidIban());
    	account16.setSolde(1800.00);
    	accountRepository.save(account16);

    	Account account17 = new Account();
    	IbanGenerator account17Iban = new IbanGenerator();
    	account17.setAccountNumber(account17Iban.generateValidIban());
    	account17.setSolde(1900.00);
    	accountRepository.save(account17);

    	Account account18 = new Account();
    	IbanGenerator account18Iban = new IbanGenerator();
    	account18.setAccountNumber(account18Iban.generateValidIban());
    	account18.setSolde(2000.00);
    	accountRepository.save(account18);

    	Account account19 = new Account();
    	IbanGenerator account19Iban = new IbanGenerator();
    	account19.setAccountNumber(account19Iban.generateValidIban());
    	account19.setSolde(2100.00);
    	accountRepository.save(account19);

    	Account account20 = new Account();
    	IbanGenerator account20Iban = new IbanGenerator();
    	account20.setAccountNumber(account20Iban.generateValidIban());
    	account20.setSolde(2200.00);
    	accountRepository.save(account20);
    }
}
