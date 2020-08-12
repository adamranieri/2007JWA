package dev.ranieri.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "trainer")
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "t_id")
	private int tId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "specialty")
	private String specialty;
	
	@OneToMany(mappedBy = "trainer")
	@JsonManagedReference
	private List<Associate> associates = new ArrayList<Associate>();
	
	public Trainer() {
		super();
	}

	public Trainer(int tId, String name, String specialty, List<Associate> associates) {
		super();
		this.tId = tId;
		this.name = name;
		this.specialty = specialty;
		this.associates = associates;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public List<Associate> getAssociates() {
		return associates;
	}

	public void setAssociates(List<Associate> associates) {
		this.associates = associates;
	}
	
	
	
}
