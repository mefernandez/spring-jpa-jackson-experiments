package com.example.json.experiments.views;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@JsonTest
public class PublicPrivateViewJsonTest {

    @Autowired
    private JacksonTester<Employee> json;
    
    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper(); 
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void publicViewSerializesEmployeeName() throws Exception {
    	Employee details = new Employee();
    	details.setName("Mark");
        // Assert against a `.json` file in the same package as the test
        JsonContent<Employee> serializedEmployee = this.json.forView(View.Public.class).write(details);
		//assertThat(serializedEmployee).isEqualToJson("employee.json");
        // Or use JSON path based assertions
        assertThat(serializedEmployee).extractingJsonPathStringValue("@.name").isEqualTo("Mark");
    }

    @Test
    public void publicViewDoesNotSerializeEmployeePass() throws Exception {
    	Employee details = new Employee();
    	details.setPass("Secret");
        // Assert against a `.json` file in the same package as the test
        JsonContent<Employee> serializedEmployee = this.json.forView(View.Public.class).write(details);
        assertThat(serializedEmployee).doesNotHaveJsonPathValue("@.pass");
    }

    @Test
    public void privateViewSerializesEmployeePass() throws Exception {
    	Employee details = new Employee();
    	details.setPass("Secret");
        // Assert against a `.json` file in the same package as the test
        JsonContent<Employee> serializedEmployee = this.json.forView(View.Private.class).write(details);
        assertThat(serializedEmployee).extractingJsonPathStringValue("@.pass").isEqualTo("Secret");
    }

    @Test
    public void privateViewSerializesEmployeeName() throws Exception {
    	Employee details = new Employee();
    	details.setName("Mark");
        // Assert against a `.json` file in the same package as the test
        JsonContent<Employee> serializedEmployee = this.json.forView(View.Private.class).write(details);
        assertThat(serializedEmployee).extractingJsonPathStringValue("@.name").isEqualTo("Mark");
    }

}
