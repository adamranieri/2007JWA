package dev.ranieri.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.ranieri.entities.Trainer;
import dev.ranieri.services.TrainerService;

@Component
@RestController
public class TrainerController {
	
	@Autowired
	TrainerService ts;
	
	@RequestMapping(value = "/trainers", method = RequestMethod.GET)
	public List<Trainer> getAllTrainers() {
		return this.ts.getAllTrainers();
	}
	

}
