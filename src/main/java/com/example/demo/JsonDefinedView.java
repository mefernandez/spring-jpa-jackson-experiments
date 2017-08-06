package com.example.demo;

import com.fasterxml.jackson.annotation.JsonClassDescription;

@JsonClassDescription("{ name: true, boss: {name: true} }")
public interface JsonDefinedView {
	
}
