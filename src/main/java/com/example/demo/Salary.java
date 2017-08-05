package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name="default_gen", sequenceName="salary_id_seq", allocationSize=100)
public class Salary extends BaseEntity {

	private BigDecimal amount;
	private Date fromDate;
	private Date toDate;
}
