package dev.ranieri.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import dev.ranieri.entities.Trainer;

@Component
@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Integer> {

}
