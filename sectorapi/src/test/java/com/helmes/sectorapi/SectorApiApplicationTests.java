package com.helmes.sectorapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helmes.sectorapi.registration.internal.RegistrationForm;
import com.helmes.sectorapi.customer.api.CustomerService;
import com.helmes.sectorapi.sector.api.Sector;
import com.helmes.sectorapi.sector.api.SectorService;
import com.helmes.sectorapi.session.api.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SectorApiApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	CustomerService customerService;

	@Autowired
	SessionService sessionService;

	@Autowired
	SectorService sectorService;

	@Test
	void contextLoads() {
	}

	@Test
	void givenCustomer_thenReturnCustomerName() throws Exception {
		List<String> sectorCodes = new ArrayList<>();
		sectorCodes.add("MANUFACTURING");
		RegistrationForm registrationForm = new RegistrationForm("testName", sectorCodes, true);

		mockMvc.perform(MockMvcRequestBuilders
						.post("/sector-api/customer")
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("testName"));
	}

	@Test
	void givenEmptyName__thenBadRequest() throws Exception {
		String exceptionParam = "Invalid request content.";

		List<String> sectorCodes = new ArrayList<>();
		sectorCodes.add("MANUFACTURING");
		RegistrationForm registrationForm = new RegistrationForm("", sectorCodes, true);

		mockMvc.perform(MockMvcRequestBuilders
						.post("/sector-api/customer")
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertNotNull(result.getResolvedException()))
				.andExpect(result -> assertEquals(exceptionParam, Objects.requireNonNull(result.getResponse().getErrorMessage())));
	}

	@Test
	void saveCustomer__getCustomerWithSession__thenReturnSavedCustomer() throws Exception {
		List<String> sectorCodes = new ArrayList<>();
		sectorCodes.add("MANUFACTURING");
		RegistrationForm registrationForm = new RegistrationForm("testName", sectorCodes, true);

		var cookie = mockMvc.perform(MockMvcRequestBuilders
				.post("/sector-api/customer")
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
		).andReturn().getResponse().getCookie("TASK_SESSION");

		mockMvc.perform(MockMvcRequestBuilders
				.get("/sector-api/customer").cookie(cookie)
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("testName"));
	}

	@Test
	void getSectors__thenReturnSectorsAsTree() throws Exception {
		List<Sector> sectors = new ArrayList<>();

		mockMvc.perform(MockMvcRequestBuilders
						.get("/sector-api/sectors")
						.content(asJsonString(sectors))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].code").value("MANUFACTURING"))
				.andExpect(jsonPath("$.[0].children[0].code").value("MANUFACTURING_CONSTRUCTION_MATERIALS"))
				.andExpect(jsonPath("$.[1].code").value("OTHER"));
	}

	@Test
	void addSector_thenReplaceSector_thenReturnReplacedSector() throws Exception {
		List<String> sectorCodes = new ArrayList<>();
		sectorCodes.add("MANUFACTURING");
		sectorCodes.add("MANUFACTURING_CONSTRUCTION_MATERIALS");

		RegistrationForm registrationForm = new RegistrationForm("testName", sectorCodes, true);

		var cookie = mockMvc.perform(MockMvcRequestBuilders
						.post("/sector-api/customer")
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn().getResponse().getCookie("TASK_SESSION");

		List<String> oldSectorCodes = new ArrayList<>();
		oldSectorCodes.add("MANUFACTURING");
		oldSectorCodes.add("MANUFACTURING_CONSTRUCTION_MATERIALS");

		sectorCodes.removeAll(oldSectorCodes);

		sectorCodes.add("MANUFACTURING");
		sectorCodes.add("OTHER");

		mockMvc.perform(MockMvcRequestBuilders
						.post("/sector-api/customer").cookie(cookie)
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders
						.get("/sector-api/customer").cookie(cookie)
						.content(asJsonString(registrationForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.sectors[0]").value("Manufacturing"))
				.andExpect(jsonPath("$.sectors[1]").value("Other"));
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
