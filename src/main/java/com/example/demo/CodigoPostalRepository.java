package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employees" , collectionResourceRel = "employees", itemResourceRel = "employee")
public interface CodigoPostalRepository extends JpaRepository<Employee, Long> {

}