package dev.ranieri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.ranieri.entities.Associate;
import dev.ranieri.repositories.AssociateRepository;

@Component
@Service
public class AssociateServiceImpl implements AssociateService {
	
	@Autowired
	AssociateRepository ar;

	@Override
	public Associate createAssociate(Associate associate) {
		return ar.save(associate);
	}

	@Override
	public Associate getAssociateById(int id) {
		Associate associate = ar.findById(id).get();
		return associate;
	}

	@Override
	public List<Associate> getAllAssociates() {
		List<Associate> associates = (List<Associate>) ar.findAll();
		return associates;
	}

	@Override
	public Associate updateAssociate(Associate associate) {	
		return ar.save(associate);// will add a new associate if ID passed in does not exist
	}

	@Override
	public boolean deleteAssociate(Associate associate) {
		
//		if(ar.findById(associate.getaId()).get() == null) {
//			return false;
//		}
		
		ar.delete(associate);
		return true;
	}

	@Override
	public List<Associate> findAssociatesInPointRange(int min, int max) {
		return ar.findByPointsBetween(min, max);
	}

	// this is method that could be mocked
	@Override
	public boolean pointTransfer(Associate sender, Associate reciever, int amount) {
		
		
		return false;
	}

}
