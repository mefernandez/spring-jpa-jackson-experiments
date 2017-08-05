package com.example.demo;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@JsonTest
public class MyJsonTests {

    @Autowired
    private JacksonTester<Employee> json;

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

}
