package com.example.demo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@JsonFilter(JsonDefinedViewSimpleBeanProperyFilter.JSON_DEFINED_VIEW_SIMPLE_BEAN_PROPERTY_FILTER)
@Data
@Entity
@SequenceGenerator(name="default_gen", sequenceName="employee_id_seq", allocationSize=100)
public class Employee extends BaseEntity {
	
	@JsonView({View.Public.class, JsonDefinedView.class})
	private String name;

	// Sensitive!
	@JsonView(View.Private.class)
	private String pass;

	// Sensitive
	@OneToMany
	@JsonView(View.Private.class)
	private Set<Salary> salaries;
	
	@JsonView({View.Public.class, JsonDefinedView.class})
	@ManyToOne
	private Employee boss;

}
