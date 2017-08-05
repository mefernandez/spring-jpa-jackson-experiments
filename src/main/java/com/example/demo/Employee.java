package com.example.demo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name="default_gen", sequenceName="employee_id_seq", allocationSize=100)
public class Employee extends BaseEntity {
	
	@JsonView(View.Public.class)
	private String name;

	// Sensitive!
	@JsonView(View.Private.class)
	private String pass;

	// Sensitive
	@OneToMany
	@JsonView(View.Private.class)
	private Set<Salary> salaries;

}
