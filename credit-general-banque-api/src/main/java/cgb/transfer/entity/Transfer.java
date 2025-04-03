package cgb.transfer.entity;

import cgb.transfer.dto.Etat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Transfer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sourceAccountNumber;
	private String destinationAccountNumber;
	private Double amount;
	private LocalDate transferDate;
	private String description;

	private Etat etatTransfer;


	public Transfer(Long id, String sourceAccountNumber, String destinationAccountNumber, Double amount,
			LocalDate transferDate, String description, Etat etatTransfer) {
		this.id = id;
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		this.amount = amount;
		this.transferDate = transferDate;
		this.description = description;
		this.etatTransfer = etatTransfer;
	}

	public Transfer(String sourceAccountNumber, String destinationAccountNumber, Double amount, LocalDate transferDate,
			String description, Etat etatTransfer) {
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		this.amount = amount;
		this.transferDate = transferDate;
		this.description = description;
		this.etatTransfer = etatTransfer;
	}

	public Transfer() {
		// TODO Auto-generated constructor stub
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

}

/*
 * { "sourceAccountNumber": "FR7630001007941234567890185",
 * "destinationAccountNumber": "FR7630004000031234567890143", "amount": 2000.00,
 * "transferDate": "2018-12-06", "description": "Description transfer test" }
 * 
 */