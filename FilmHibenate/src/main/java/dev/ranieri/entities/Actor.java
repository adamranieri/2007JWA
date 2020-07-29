package dev.ranieri.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor {
	
	@Id // this is a primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // let hibernate know this field is auto incremented
	@Column(name = "a_id" )
	private int aId;
	
	@Column(name = "name")
	private String name;
	
	
	@ManyToMany(cascade = CascadeType.ALL)// if you edit a movie that is in the actor's movie list. And you update
	// the actor then the movie also gets update
	// neither the movie or the actor table actually has the information
	// in the database to connect the two. 
	@JoinTable(name = "actor_movie",  // what junction table holds the information
	joinColumns = {@JoinColumn(name = "a_id")}, // which foreign key related back to this entity (actor)
	inverseJoinColumns = {@JoinColumn(name ="m_id")} // which foreign key relates to movie
	)
	List<Movie> movies =new ArrayList<Movie>();
	

	public Actor() {
		super();
	}

	public Actor(int aId, String name, List<Movie> movies) {
		super();
		this.aId = aId;
		this.name = name;
		this.movies = movies;
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

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Actor [aId=" + aId + ", name=" + name + "]";
	}


}
