package dev.ranieri.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private int mId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "runtime")
	private int runtime;
	
	// we do not need to have the id column we can have the actual director object
//	@Column(name = "d_id") // we know that this field is actuall a foreign key
//	private int dId;
	
	@ManyToOne
	@JoinColumn(name = "d_id") // it will use that foreign key column to get director that is associated with that movie
	private Director director;
	
	@ManyToMany(mappedBy = "movies") // bidirectional relationship. Each entity can be used to get the other
	// unidirectional relationship. Only one entity can be used to get the other
	private List<Actor> actors = new ArrayList<Actor>();

	public Movie() {
		super();
	}

	public Movie(int mId, String title, int runtime, Director director) {
		super();
		this.mId = mId;
		this.title = title;
		this.runtime = runtime;
		this.director = director;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}
	
	

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie [mId=" + mId + ", title=" + title + ", runtime=" + runtime + ", director=" + director + "]";
	}

	
	


}
