package dev.ranieri.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Override
	public String toString() {
		return "Director [dId=" + dId + ", name=" + name + "]";
	}

}
