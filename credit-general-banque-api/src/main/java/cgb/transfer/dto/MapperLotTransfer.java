package cgb.transfer.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cgb.transfer.Etat;
import cgb.transfer.entity.Log;
import cgb.transfer.entity.LotTransfer;
import cgb.transfer.entity.Transfer;
import cgb.transfer.repository.LotTransferRepository;
import cgb.transfer.services.LogService;

@Component
public class MapperLotTransfer {

	@Autowired
	private LotTransferRepository lotTransferRepository;

	@Autowired
	private LogService logService;

	public LotTransfer toEntity(LotTransferDTO ltdto) {
		Log log = new Log("Creation d'un lot de transfer", Etat.WAITING, LocalDate.now(),
				this.getClass().getSimpleName());
		List<Transfer> transfers = new ArrayList<Transfer>();
		for (TransferDTO dto : ltdto.getLotTransfers()) {
			transfers.add(toEntity(dto));
		}

		LotTransfer lt;
		if (ltdto.getId() != null) {
			lt = lotTransferRepository.findById(ltdto.getId()).orElse(new LotTransfer());
		} else {
			lt = new LotTransfer();
		}

		lt.setLotTransfer(transfers);
		lt.setDescription(ltdto.getDescriptionLot());
		lt.setReference(ltdto.getReference());
		lt.setSourceAccountNumber(ltdto.getSourceAccountNumber());
		lt.setDateLotTransfer(ltdto.getDateLotTransfer());
		lt.setEtatLotTransfer(Etat.WAITING);

		logService.saveLog(log);
		logService.saveLog(
				new Log("Lot de transfer créé", Etat.SUCCESS, LocalDate.now(), this.getClass().getSimpleName()));
		return lt;
	}

	public Transfer toEntity(TransferDTO tdto) {
		return new Transfer(tdto.getSourceAccountNumber(), tdto.getDestinationAccountNumber(), tdto.getAmount(),
				tdto.getTransferDate(), tdto.getDescription(), Etat.WAITING);
	}

}
