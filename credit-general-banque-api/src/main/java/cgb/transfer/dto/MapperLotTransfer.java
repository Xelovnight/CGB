package cgb.transfer.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cgb.transfer.entity.LotTransfer;
import cgb.transfer.entity.Transfer;
import cgb.transfer.repository.LotTransferRepository;

@Component
public class MapperLotTransfer {

	@Autowired
	private LotTransferRepository lotTransferRepository;

	public LotTransfer toEntity(LotTransferDTO ltdto) {
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

		return lt;
	}

	public Transfer toEntity(TransferDTO tdto) {
		return new Transfer(tdto.getSourceAccountNumber(), tdto.getDestinationAccountNumber(), tdto.getAmount(),
				tdto.getTransferDate(), tdto.getDescription(), Etat.WAITING);
	}

}
