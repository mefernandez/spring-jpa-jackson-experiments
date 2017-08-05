package com.example.demo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.hateoas.Identifiable;

@MappedSuperclass
public class BaseEntity  implements Identifiable<Long> {

	@Id
	//@GeneratedValue
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen") 
	protected Long id;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
