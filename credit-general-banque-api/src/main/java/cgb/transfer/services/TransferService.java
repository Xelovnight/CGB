package cgb.transfer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import cgb.transfer.Etat;
import cgb.transfer.entity.Account;
import cgb.transfer.entity.Log;
import cgb.transfer.entity.Transfer;
import cgb.transfer.repository.AccountRepository;
import cgb.transfer.repository.TransferRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransferService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private LogService logService;
	
	/*
	 * Rappel du cours sur les transactions... Tout ou rien
	 */
	@Transactional
	public Transfer createTransfer(String sourceAccountNumber, String destinationAccountNumber, Double amount,
			LocalDate transferDate, String description) {
		Account sourceAccount = accountRepository.findById(sourceAccountNumber)
				.orElseThrow(() -> new RuntimeException("Source account not found"));
		Account destinationAccount = accountRepository.findById(destinationAccountNumber)
				.orElseThrow(() -> new RuntimeException("Destination account not found"));

		logService.saveLog(
				new Log("Création d'un transfer", Etat.WAITING, LocalDate.now(), this.getClass().getSimpleName()));
		
		/* Pas de découvert autorisé */
		if (sourceAccount.getSolde().compareTo(amount) < 0) {
			throw new RuntimeException("Insufficient funds");
		} else {

			sourceAccount.setSolde(sourceAccount.getSolde() - (amount));
			destinationAccount.setSolde(destinationAccount.getSolde() + (amount));

			accountRepository.save(sourceAccount);
			accountRepository.save(destinationAccount);

			Transfer transfer = new Transfer();
			transfer.setSourceAccountNumber(sourceAccountNumber);
			transfer.setDestinationAccountNumber(destinationAccountNumber);
			transfer.setAmount(amount);
			transfer.setTransferDate(transferDate);
			transfer.setDescription(description);

			logService.saveLog(
					new Log("Transfer créé", Etat.SUCCESS, LocalDate.now(), this.getClass().getSimpleName()));
			
			return transferRepository.save(transfer);
		}
	}

	

	@Transactional
	public void createTransferForLot(String sourceAccountNumber, String destinationAccountNumber, Double amount,
			LocalDate transferDate, String description) {
		
		logService.saveLog(
				new Log("Création d'un transfer du lot", Etat.WAITING, LocalDate.now(), this.getClass().getSimpleName()));
		
		Account sourceAccount = accountRepository.findById(sourceAccountNumber)
				.orElseThrow(() -> new RuntimeException("Source account not found"));
		Account destinationAccount = accountRepository.findById(destinationAccountNumber)
				.orElseThrow(() -> new RuntimeException("Destination account not found"));

		if (sourceAccount.getSolde().compareTo(amount) < 0) {
			throw new RuntimeException("Insufficient funds");
		} else {

			sourceAccount.setSolde(sourceAccount.getSolde() - (amount));
			destinationAccount.setSolde(destinationAccount.getSolde() + (amount));

			accountRepository.save(sourceAccount);
			accountRepository.save(destinationAccount);

			logService.saveLog(
					new Log("Transfer d'un lot créé", Etat.SUCCESS, LocalDate.now(), this.getClass().getSimpleName()));
		}
	}

	@GetMapping
	public List<Account> findAllAccount() {
		return accountRepository.findAll();
	}

	@GetMapping
	public List<Transfer> findAllTransfer() {
		return transferRepository.findAll();
	}
}
