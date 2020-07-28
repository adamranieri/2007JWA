package dev.ranieri.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



// Hibernate requires your entities be beans
@Entity // marks class as an entity
@Table(name="director") // says what table to connect this entity to
public class Director {
	
	@Id // says this is the primary key of the entity
	@GeneratedValue(strategy = GenerationType.IDENTITY) // we say that this column is auto-incremented
	@Column(name ="d_id")
	private int dId;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@OneToMany(mappedBy = "director", fetch = FetchType.EAGER, cascade = CascadeType.ALL)// this is the field in JAVA that has the annotation 
	// saying what column has the foreignkey
	// In hibernate the child table (the table that has the foreign key is called the owning table)
	// Beacuse it is the child table that actually information to connect the two entities
	List<Movie> movies = new ArrayList();
	
	public Director() {
		super();
	}
		
	public Director(int dId, String name) {
		super();
		this.dId = dId;
		this.name = name;
	}

	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Director [dId=" + dId + ", name=" + name + "]";
	}

	
}
