package cgb.transfer.dto;

import java.time.LocalDate;

import cgb.transfer.entity.LotTransfer;

public class TransferDTO {

	private Long id;
	private String sourceAccountNumber;
	private String destinationAccountNumber;
	private Double amount;
	private LocalDate transferDate;
	private String description;

	private Etat etatTransfer;

	private LotTransfer lotTransfer;

	public TransferDTO(Long id, String sourceAccountNumber, String destinationAccountNumber, Double amount,
			LocalDate transferDate, String description, Etat etatTransfer, LotTransfer lotTransfer) {
		this.id = id;
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		this.amount = amount;
		this.transferDate = transferDate;
		this.description = description;
		this.etatTransfer = etatTransfer;
		this.lotTransfer = lotTransfer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public void setSourceAccountNumber(String sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}

	public String getDestinationAccountNumber() {
		return destinationAccountNumber;
	}

	public void setDestinationAccountNumber(String destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(LocalDate transferDate) {
		this.transferDate = transferDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Etat getEtatTransfer() {
		return etatTransfer;
	}

	public void setEtatTransfer(Etat etatTransfer) {
		this.etatTransfer = etatTransfer;
	}

	public LotTransfer getLotTransfer() {
		return lotTransfer;
	}

	public void setLotTransfer(LotTransfer lotTransfer) {
		this.lotTransfer = lotTransfer;
	}

}
