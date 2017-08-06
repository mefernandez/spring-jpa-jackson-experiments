package com.example.json.experiments.filters;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JsonPathTest {

	@Test
	public void testPathFindsProperty() {
		DocumentContext parse = JsonPath.parse("{ name: null, boss: {name: true} }");
		assertTrue(parse.read("$.boss.name"));
	}

	@Test(expected = PathNotFoundException.class)
	public void testPathDoesNotFindProperty() {
		DocumentContext parse = JsonPath.parse("{ name: null, boss: {name: true} }");
		assertNull(parse.read("$.boss.pass"));
	}
}
