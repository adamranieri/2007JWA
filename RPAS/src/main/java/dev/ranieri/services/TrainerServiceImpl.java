package dev.ranieri.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.ranieri.entities.Trainer;
import dev.ranieri.repositories.TrainerRepository;

@Component
@Service
public class TrainerServiceImpl implements TrainerService {
	
	@Autowired
	TrainerRepository tr;

	@Override
	public List<Trainer> getAllTrainers() {
		List<Trainer> trainers = (List<Trainer>) this.tr.findAll();
		return trainers;
	}

	@Override
	public Trainer getTrainerById(int id) {
		
		return this.tr.findById(id).get();
	}

}
