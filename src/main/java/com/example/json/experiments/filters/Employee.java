package com.example.json.experiments.filters;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.example.json.experiments.filters.JsonDefinedView;
import com.example.json.experiments.filters.JsonDefinedViewSimpleBeanProperyFilter;
import com.example.json.experiments.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@JsonFilter(JsonDefinedViewSimpleBeanProperyFilter.JSON_DEFINED_VIEW_SIMPLE_BEAN_PROPERTY_FILTER)
@Data
@Entity
@SequenceGenerator(name="default_gen", sequenceName="employee_id_seq", allocationSize=100)
public class Employee extends BaseEntity {
	
	@JsonView({JsonDefinedView.class})
	private String name;

	// Sensitive!
	private String pass;

	// Sensitive
	@OneToMany
	private Set<Salary> salaries;
	
	@JsonView(JsonDefinedView.class)
	@ManyToOne
	private Employee boss;

}
