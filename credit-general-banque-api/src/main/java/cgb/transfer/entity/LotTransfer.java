package cgb.transfer.entity;

import java.time.LocalDate;
import java.util.List;

import cgb.transfer.dto.Etat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class LotTransfer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descriptionLot;
	private String sourceAccountNumber;
	private String reference;
	private LocalDate dateLotTransfer;

	private Etat etatLotTransfer;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "transfer_id")
	private List<Transfer> lotTransfers;

	public LotTransfer() {

	}

	public LotTransfer(Long id, List<Transfer> lotTransfers, String description, String sourceAccountNumber, String reference, LocalDate dateLotTransfer,
			Etat etatLotTransfer) {
		this.id = id;
		this.lotTransfers = lotTransfers;
		this.descriptionLot = description;
		this.dateLotTransfer = dateLotTransfer;
		this.etatLotTransfer = etatLotTransfer;
		this.sourceAccountNumber = sourceAccountNumber;
		this.reference = reference;
	}

	public LotTransfer(List<Transfer> lotTransfers, String description,String sourceAccountNumber, String reference, LocalDate dateLotTransfer, Etat etatLotTransfer) {
		this.lotTransfers = lotTransfers;
		this.descriptionLot = description;
		this.dateLotTransfer = dateLotTransfer;
		this.etatLotTransfer = etatLotTransfer;
		this.sourceAccountNumber = sourceAccountNumber;
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transfer> getLotTransfer() {
		return lotTransfers;
	}

	public void setLotTransfer(List<Transfer> lotTransfer) {
		this.lotTransfers = lotTransfer;
	}

	public String getDescription() {
		return descriptionLot;
	}

	public void setDescription(String description) {
		this.descriptionLot = description;
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

	public void setEtatLotTransfer(Etat etatTransfer) {
		this.etatLotTransfer = etatTransfer;
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