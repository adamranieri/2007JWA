package dev.ranieri.services;

import java.util.List;

import dev.ranieri.entities.Trainer;

public interface TrainerService {
	
	List<Trainer> getAllTrainers();
	
	Trainer getTrainerById(int id);

}
