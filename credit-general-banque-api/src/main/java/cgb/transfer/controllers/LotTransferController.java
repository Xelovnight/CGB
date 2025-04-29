package cgb.transfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cgb.transfer.Etat;
import cgb.transfer.dto.LotTransferDTO;
import cgb.transfer.dto.MapperLotTransfer;
import cgb.transfer.entity.Log;
import cgb.transfer.entity.LotTransfer;
import cgb.transfer.services.LogService;
import cgb.transfer.services.LotTransferService;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/lotTransfers")
public class LotTransferController {

	@Autowired
	private LotTransferService lotTransferService;

	@Autowired
	private MapperLotTransfer mapperLotTransfer;

	@Autowired
	private LogService logService;
	
	@PostMapping("/send/")
	public ResponseEntity<?> createLotTransfer(@RequestBody LotTransferDTO lotTransfersDTO) {
		try {

			LotTransfer lotTransfers = mapperLotTransfer.toEntity(lotTransfersDTO);

			LocalDate now = LocalDate.now();
			lotTransfers.setDateLotTransfer(now);
			lotTransfers.setSourceAccountNumber(lotTransferService.getSourceAcccountCode());
			lotTransfers.setReference(lotTransferService.getReference(lotTransfersDTO.getId()));
			
			//Envoie du traitement asynchrone
			// LotTransfer res = lotTransferService.sauvegarderLotTransfer(lotTransfers);
			lotTransferService.traitementLotTransfer(lotTransfers);
			
			//Formater la réponse attendue (
			String msg = "Traitement Lancé";
			String resjson=String.format("{ refLot : %s , dateLancement : %s , message : %s, etat : %s }",lotTransfers.getReference(),lotTransfers.getDateLotTransfer(),msg,lotTransfers.getEtatLotTransfer());
	
			logService.saveLog(
					new Log("Lot envoyé, traitement en attente...", Etat.WAITING, LocalDate.now(), this.getClass().getSimpleName()));
			
			return ResponseEntity.ok(resjson);

		} catch (EntityNotFoundException e) {
			logService.saveLog(
					new Log("Lot Transfer échoué", Etat.FAILED, LocalDate.now(), this.getClass().getSimpleName()));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LotTransferResponse("FAILURE", e.getMessage()));

		} catch (RuntimeException e) {
			logService.saveLog(
					new Log("Lot Transfer échoué", Etat.FAILED, LocalDate.now(), this.getClass().getSimpleName()));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new LotTransferResponse("FAILURE", e.getMessage()));
		}
	}

}

class LotTransferResponse {
	private String status;
	private String message;

	// Constructeur
	public LotTransferResponse(String status, String message) {
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
