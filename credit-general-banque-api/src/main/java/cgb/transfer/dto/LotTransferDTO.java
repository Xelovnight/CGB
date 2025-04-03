package cgb.transfer.dto;

import java.time.LocalDate;
import java.util.List;

public class LotTransferDTO {

	private Long id;
	private String descriptionLot;
	private String sourceAccountNumber;
	private String reference;
	private LocalDate dateLotTransfer;

	private Etat etatLotTransfer;
	private List<TransferDTO> lotTransfers;

	public LotTransferDTO() {
	}

	public LotTransferDTO(Long id, String descriptionLot, String sourceAccountNumber, String reference,
			LocalDate dateLotTransfer, Etat etatLotTransfer, List<TransferDTO> lotTransfers) {
		this.id = id;
		this.descriptionLot = descriptionLot;
		this.dateLotTransfer = dateLotTransfer;
		this.etatLotTransfer = etatLotTransfer;
		this.lotTransfers = lotTransfers;
		this.sourceAccountNumber = sourceAccountNumber;
		this.reference = reference;
	}

	public LotTransferDTO(String descriptionLot, String sourceAccountNumber, String reference,
			LocalDate dateLotTransfer, Etat etatLotTransfer, List<TransferDTO> lotTransfers) {
		this.descriptionLot = descriptionLot;
		this.dateLotTransfer = dateLotTransfer;
		this.etatLotTransfer = etatLotTransfer;
		this.lotTransfers = lotTransfers;
		this.sourceAccountNumber = sourceAccountNumber;
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriptionLot() {
		return descriptionLot;
	}

	public void setDescriptionLot(String descriptionLot) {
		this.descriptionLot = descriptionLot;
	}

	public LocalDate getDateLotTransfer() {
		return dateLotTransfer;
	}

	public void setDateLotTransfer(LocalDate dateLotTransfer) {
		this.dateLotTransfer = dateLotTransfer;
	}

	public Etat getEtatLotTransfer() {
		return etatLotTransfer;
	}

	public void setEtatLotTransfer(Etat etatLotTransfer) {
		this.etatLotTransfer = etatLotTransfer;
	}

	public List<TransferDTO> getLotTransfers() {
		return lotTransfers;
	}

	public void setLotTransfers(List<TransferDTO> lotTransfers) {
		this.lotTransfers = lotTransfers;
	}

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
