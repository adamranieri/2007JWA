package dev.ranieri.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ranieri.entities.Associate;
import dev.ranieri.entities.Trainer;
import dev.ranieri.services.AssociateService;

@Component
@RestController // @ RestController says that all methods in the controller return string
// ie they do not return views be default which is @controller default
// You do not have to put @ResponseBody on each method
public class AssociateController {
	
	@Autowired
	AssociateService as;
	
	@RequestMapping(value = "/associates", method = RequestMethod.GET)
	public List<Associate> getAllAssociates(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max){
		
		if(min != null && max != null) {
			return this.as.findAssociatesInPointRange(min, max);
		}
		
		return this.as.getAllAssociates();
	}
	
	@RequestMapping(value = "/associates", method = RequestMethod.POST)
	public Associate createAssociate(@RequestBody Associate associate) {
		return this.as.createAssociate(associate);
	}
	
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.GET)
	public Associate getAssociateById(@PathVariable int id) {
		return this.as.getAssociateById(id);
	}
	
	// Two ways of update endpoints
	@RequestMapping(value = "/associates", method = RequestMethod.PUT)
	public Associate updateAssociate(@RequestBody Associate associate) {
		return this.as.updateAssociate(associate);
	}
	
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.PUT)
	public Associate updateAssociateId(@RequestBody Associate associate, @PathVariable int id) {
		associate.setaId(id);
		return this.as.updateAssociate(associate);
	}
	
	@RequestMapping(value = "/associates/{id}", method = RequestMethod.DELETE)
	public Boolean deleteAssociate(@PathVariable int id) { // Spring does not like returning primitives. Use wrapper classes
		Associate a = this.as.getAssociateById(id);
		return this.as.deleteAssociate(a);
	}
	

}
