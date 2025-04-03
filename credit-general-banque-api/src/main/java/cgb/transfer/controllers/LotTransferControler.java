package cgb.transfer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cgb.transfer.dto.LotTransferDTO;
import cgb.transfer.dto.MapperLotTransfer;
import cgb.transfer.entity.LotTransfer;
import cgb.transfer.services.LotTransferService;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/lotTransfers")
public class LotTransferControler {

	@Autowired
	private LotTransferService lotTransferService;

	@Autowired
	private MapperLotTransfer mapperLotTransfer;

	@PostMapping("/send/")
	public ResponseEntity<?> createLotTransfer(@RequestBody LotTransferDTO lotTransfersDTO) {
		try {

			LotTransfer lotTransfers = mapperLotTransfer.toEntity(lotTransfersDTO);

			LocalDate now = LocalDate.now();
			lotTransfers.setDateLotTransfer(now);
			lotTransfers.setSourceAccountNumber(lotTransferService.getSourceAcccountCode());
			lotTransfers.setReference(lotTransferService.getReference(lotTransfersDTO.getId()));
			
			LotTransfer res = lotTransferService.sauvegarderLotTransfer(lotTransfers);

			return ResponseEntity.ok(res);

		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LotTransferResponse("FAILURE", e.getMessage()));

		} catch (RuntimeException e) {
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
