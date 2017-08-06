package com.example.json.experiments.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.json.experiments.views.Employee;

@RepositoryRestResource(path = "employees" , collectionResourceRel = "employees", itemResourceRel = "employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
