package dev.ranieri.controllermocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import dev.ranieri.entities.Associate;
import dev.ranieri.services.AssociateService;

@AutoConfigureMockMvc
@SpringBootTest(classes = dev.ranieri.app.RpasApplication.class)
class AssoicateControllerTests {

	@MockBean // should automatically create a mock for this bean
	AssociateService as;
	
	@Autowired
	MockMvc mvc; // Model View Controller
	
	@Test
	void test() {
		Mockito.when(as.getAssociateById(10)).thenReturn(new Associate(10,"McFake",900));		
		System.out.println(as.getAssociateById(10));
	}
	
	@Test
	void getAssociateById() throws Exception {
		ResultActions ra = mvc.perform(get("/associates/10"));
		ra.andExpect(status().isOk());
	}
	
	@Test
	void deleteAssociateUnauthorized() throws Exception {
		ResultActions ra = mvc.perform(delete("/associates/2"));
		ra.andExpect(status().isUnauthorized());// checks to see the http response is a 401)
	}
	
	@Test
	void getAllAssociate() throws Exception {
		ResultActions ra = mvc.perform(get("/associates"));
		ra.andExpect(status().is(200));
	}
	
	

}
