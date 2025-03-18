package cgb.transfert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cgb.transfert.entity.Transfer;
import cgb.transfert.exceptions.ExceptionInvalidIbanFormat;
import cgb.transfert.exceptions.ExceptionInvalidUnCheckableIban;
import cgb.transfert.services.Utilitaire;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user")
public class appliTest {
	@Autowired
	private MockMvc mockMvc;

//	private static final String USERNAME = "user"; // Utilisateur par défaut généré par Spring
//    private static final String PASSWORD = "a6ed9901-337f-4796-ad00-cef35ab2fefb"; // Exemple de mot de passe généré

	@Test
	public void testObtenirUtilisateur() throws Exception {
		int id = 1;
		mockMvc.perform(get("/test/{id}", id)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Recu : " + id));
	}

	@Test
	public void testVide() throws Exception {
		mockMvc.perform(get("/test/")).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(content().string("Racine sous test "));
	}

//	@Test
//	public void testAll() throws Exception {
//		String expectedJson = """
//					        [
//				    {
//				        "accountNumber": "123456789",
//				        "solde": 300.0
//				    },
//				    {
//				        "accountNumber": "987654321",
//				        "solde": 500.0
//				    },
//				    {
//				        "accountNumber": "456789123",
//				        "solde": 2000.0
//				    }
//				]
//					        """;
//
//		mockMvc.perform(get("/api/transfers/findAll")).andExpect(status().isOk())
//				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(content().json(expectedJson));
//	}

	@Test
	public void createTransferTest_Success() throws Exception {
		Transfer transfer = new Transfer();
		transfer.setAmount(100.0);
		transfer.setDescription("Test du transfer");
		transfer.setDestinationAccountNumber("FR7630001007941234567890185");
		transfer.setSourceAccountNumber("FR7630004000031234567890143");
		transfer.setTransferDate(LocalDate.parse("2018-12-06"));
		mockMvc.perform(post("/api/transfers").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transfer)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	public void createTransferTest_Failure() throws Exception {
		Transfer transfer = new Transfer();
		transfer.setAmount(400000.0);
		transfer.setDescription("Test du transfer");
		transfer.setDestinationAccountNumber("123456789");
		transfer.setSourceAccountNumber("987654321");
		transfer.setTransferDate(LocalDate.parse("2018-12-06"));
		mockMvc.perform(post("/api/transfers").content(asJsonString(transfer)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().findAndRegisterModules().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testCorrectIban() throws Exception {
		String iban = "FR7630001007941234567890185";
		Utilitaire.getInstanceValidator().isIbanValide(iban);
	}

	@Test
	public void testCorrectIbanCountryName() throws Exception {
		String iban = "FR7630001007941234567890185";
		Utilitaire.getInstanceValidator().getCodePays(iban);
	}

	@Test
	public void testCorrectIbanControlNumber() throws Exception {
		String iban = "FR7630001007941234567890185";
		Utilitaire.getInstanceValidator().getControlNumbers(iban);
	}

	@Test
	public void testCorrectIbanBasicBankAccountNumber() throws Exception {
		String iban = "FR7630001007941234567890185";
		Utilitaire.getInstanceValidator().getBasicBankNumbers(iban);
	}

	@Test
	public void testCorrectStructureIban() throws Exception {
		String iban = "FR7630001007941234567890185";
		Utilitaire.getInstanceValidator().isIbanStructureValide(iban);
	}

	/*
	 * @Test public void testCorrectGeneretedIban() throws Exception { String iban =
	 * IbanGenerator.generateValidIban();
	 * Utilitaire.getInstanceValidator().isIbanStructureValide(iban); }
	 */

	@Test
	public void testIncorrectIbanThrowsException() {
		String iban = "FR76300010079412345678";
		ExceptionInvalidIbanFormat exception = assertThrows(ExceptionInvalidIbanFormat.class,
				() -> Utilitaire.getInstanceValidator().isIbanValide(iban));
		assertEquals(ExceptionInvalidIbanFormat.FailureType.INVALIDCARACTERCOUNT.name(), exception.getMessage());
	}

	@Test
	public void testIncorrectIbanStructureThrowsException() {
		String iban = "gesFR7630001007941234567890185";
		ExceptionInvalidUnCheckableIban exception = assertThrows(ExceptionInvalidUnCheckableIban.class,
				() -> Utilitaire.getInstanceValidator().isIbanStructureValide(iban));
		assertEquals(ExceptionInvalidUnCheckableIban.FailureType.UNVERIFIABLE.name(), exception.getMessage());
	}

	@Test
	void contextLoad() {
	}
	


	
	

}
