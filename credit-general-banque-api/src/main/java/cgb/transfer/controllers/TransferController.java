package cgb.transfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cgb.transfer.Etat;
import cgb.transfer.entity.Account;
import cgb.transfer.entity.Log;
import cgb.transfer.entity.Transfer;
import cgb.transfer.entity.TransferRequest;
import cgb.transfer.services.LogService;
import cgb.transfer.services.TransferService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

	@Autowired
	private LogService logService;
	
	@Autowired
	private TransferService transferService;

	@PostMapping("/creerTransfer/")
	public ResponseEntity<?> createTransfer(@RequestBody TransferRequest transferRequest) {
		// public ResponseEntity<Transfer> createTransfer(@RequestBody TransferRequest
		// transferRequest) {
		try {
			Transfer transfer = transferService.createTransfer(transferRequest.getSourceAccountNumber(),
					transferRequest.getDestinationAccountNumber(), transferRequest.getAmount(),
					transferRequest.getTransferDate(), transferRequest.getDescription());
			logService.saveLog(
					new Log("Lot Transfer Réussie", Etat.SUCCESS, LocalDate.now(), this.getClass().getSimpleName()));
			
			return ResponseEntity.ok(transfer);
		} catch (RuntimeException e) {
			TransferResponse errorResponse = new TransferResponse("FAILURE", e.getMessage());
			logService.saveLog(
					new Log("Transfer échoué", Etat.FAILED, LocalDate.now(), this.getClass().getSimpleName()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

	}

	@GetMapping("/findAll/Account")
	public ResponseEntity<?> findAllAccount() {
		List<Account> account = transferService.findAllAccount();
		return ResponseEntity.ok(account);
	}
	
	@GetMapping("/findAll/Transfer")
	public ResponseEntity<?> findAllTransfer() {
		List<Transfer> transfer = transferService.findAllTransfer();
		return ResponseEntity.ok(transfer);
	}

	/*
	 * @PostMapping public ResponseEntity<String> testTransfer(@RequestBody String
	 * s) { System.out.println("Post reçu"); return
	 * ResponseEntity.ok("Post bien traité: "+ s); }
	 */

}

class TransferResponse {
	private String status;
	private String message;

	// Constructeur
	public TransferResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	// Getters et Setters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
