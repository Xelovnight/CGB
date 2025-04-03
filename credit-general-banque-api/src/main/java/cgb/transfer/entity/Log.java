package cgb.transfer.entity;

import java.time.LocalDate;

import cgb.transfer.dto.Etat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private Etat etatLog;
	private LocalDate date;
	private String nomClasse;
	
	public Log() {

	}
	
	public Log(long id, String description, Etat etatLog, LocalDate date, String nomClasse) {
		this.id = id;
		this.description = description;
		this.etatLog = etatLog;
		this.date = date;
		this.nomClasse = nomClasse;
	}
	
	public Log(String description, Etat etatLog, LocalDate date, String nomClasse) {
		this.description = description;
		this.etatLog = etatLog;
		this.date = date;
		this.nomClasse = nomClasse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Etat getEtatLog() {
		return etatLog;
	}

	public void setEtatLog(Etat etatLog) {
		this.etatLog = etatLog;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNomClasse() {
		return nomClasse;
	}

	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	
	
}
