package dev.ranieri.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

// Spring data will use Hibernate under the good to persist your information
// Spring data abstracts away having to manually create sessions and a session factory
@Entity
@Table(name ="associate")
public class Associate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private int aId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "points")
	private int points;
	
	@ManyToOne
	@JoinColumn(name = "t_id")
	@JsonBackReference
	private Trainer trainer;

	public Associate() {
		super();
	}

	public Associate(int aId, String name, int points) {
		super();
		this.aId = aId;
		this.name = name;
		this.points = points;
	}

	public int getaId() {
		return aId;
	}

	public void setaId(int aId) {
		this.aId = aId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Associate [aId=" + aId + ", name=" + name + ", points=" + points + "]";
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	
}
