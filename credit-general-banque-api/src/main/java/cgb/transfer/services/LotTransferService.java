package cgb.transfer.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cgb.transfer.entity.LotTransfer;
import cgb.transfer.entity.Transfer;
import cgb.transfer.repository.LotTransferRepository;
import jakarta.transaction.Transactional;

@Service
public class LotTransferService {

	@Autowired
	private LotTransferRepository lotTransferRepository;
	
	@Autowired
	private TransferService transferService;

	@Transactional
	public LotTransfer sauvegarderLotTransfer(LotTransfer lotTransfer) {
		
		for(Transfer t : lotTransfer.getLotTransfer()) {
			transferService.createTransferForLot(t.getSourceAccountNumber(), t.getDestinationAccountNumber(), t.getAmount(),t.getTransferDate() ,t.getDescription());
		}
		return lotTransferRepository.save(lotTransfer);
	}

	public String getSourceAcccountCode() {
		return "FR7630001007941234567890185";
	}

	public String getReference(long id) {
		LocalDate now = LocalDate.now();
		return now + "id" + id;
	}
}
