package cgb.transfer.services;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import cgb.transfer.Etat;
import cgb.transfer.entity.Log;
import cgb.transfer.entity.LotTransfer;
import cgb.transfer.entity.Transfer;
import cgb.transfer.repository.LotTransferRepository;

@Service
public class LotTransferService {

	@Autowired
	private LotTransferRepository lotTransferRepository;
	
	@Autowired
	private TransferService transferService;

	@Autowired
	private LogService logService;

	@Async
	public void traitementLotTransfer(LotTransfer lotTransfer) {
		
		try {

			lotTransferRepository.save(lotTransfer);
			TimeUnit.SECONDS.sleep(10);
			for(Transfer t : lotTransfer.getLotTransfer()) {
				transferService.createTransferForLot(t.getSourceAccountNumber(), t.getDestinationAccountNumber(), t.getAmount(),t.getTransferDate() ,t.getDescription());
				t.setEtatTransfer(Etat.SUCCESS);
			}
			lotTransfer.setEtatLotTransfer(Etat.SUCCESS);
			lotTransferRepository.save(lotTransfer);
			
			logService.saveLog(
					new Log("Lot Transfer Réussie", lotTransfer.getEtatLotTransfer(), LocalDate.now(), this.getClass().getSimpleName()));
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	
	}

	public String getSourceAcccountCode() {
		return "FR7630001007941234567890185";
	}

	public String getReference(long id) {
		LocalDate now = LocalDate.now();
		return now + "id" + id;
	}
}
