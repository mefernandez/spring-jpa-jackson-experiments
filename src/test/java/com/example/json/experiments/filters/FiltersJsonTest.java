package com.example.json.experiments.filters;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.ResolvableType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RunWith(SpringRunner.class)
@JsonTest
public class FiltersJsonTest {

	@Autowired
	private JacksonTester<Employee> json;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testFilter() throws Exception {
		// Data
		Employee mark = new Employee();
		mark.setName("Mark");
		Employee boss = new Employee();
		boss.setName("Bob");
		mark.setBoss(boss);

		// Setup
		PropertyFilter theFilter = new JsonDefinedViewSimpleBeanProperyFilter();
		FilterProvider filters = new SimpleFilterProvider().addFilter(JsonDefinedViewSimpleBeanProperyFilter.JSON_DEFINED_VIEW_SIMPLE_BEAN_PROPERTY_FILTER, theFilter);

		String dtoAsString = mapper.writer(filters).withView(JsonDefinedView.class).writeValueAsString(mark);
		System.out.println(dtoAsString);
		JsonContent<Employee> exp = new JsonContent<Employee>(this.getClass(), ResolvableType.forClass(Employee.class), dtoAsString);
		assertThat(exp).extractingJsonPathStringValue("@.name").isEqualTo("Mark");
	}

}
