package dev.ranieri.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import dev.ranieri.entities.Associate;
import dev.ranieri.repositories.AssociateRepository;

@SpringBootTest
@Transactional
class RpasApplicationTests {
	
	@Autowired // Spring data will create an implemenation class for your repository when you start spring
	AssociateRepository ar;

	@Test
	@Commit // any operation which performs save or an update is automatically rolledback at the end of the test
	void addAnAssociate() {
		
		Associate adam = new Associate(0,"Ismael",2500);
		ar.save(adam);
		System.out.println(adam);
		
	}
	
	@Test
	void getAllAssociates() {
		List<Associate> associates = (List<Associate>) ar.findAll();
		System.out.println(associates);
	}
	
	@Test
	void getAssociatesBetween() {
		List<Associate> associates = ar.findByPointsBetween(500, 2000);
		System.out.println(associates);
		
	}

}
