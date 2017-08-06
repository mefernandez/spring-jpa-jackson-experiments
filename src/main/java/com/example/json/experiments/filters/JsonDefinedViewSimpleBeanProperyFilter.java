package com.example.json.experiments.filters;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JsonDefinedViewSimpleBeanProperyFilter extends SimpleBeanPropertyFilter {
	
	public final static String JSON_DEFINED_VIEW_SIMPLE_BEAN_PROPERTY_FILTER = "JsonDefinedViewSimpleBeanProperyFilter"; 
	
	String path = "$";
	
	@Override
	public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
		Class<?> activeView = provider.getActiveView();
		if (activeView.isAssignableFrom(JsonDefinedView.class)) {
			JsonClassDescription annotation = activeView.getAnnotation(JsonClassDescription.class);
			String jsonDefinition = annotation.value();
			String propertyName = writer.getName();
			if (checkPath(jsonDefinition, propertyName)) {
					this.path += "." + propertyName;
					writer.serializeAsField(pojo, jgen, provider);
					this.path = this.path.substring(0, this.path.length() - propertyName.length() - 1);
			}
			//System.out.println(path);
		}
	}
	
	private boolean checkPath(String jsonDefinition, String propertyName) {
		DocumentContext parse = JsonPath.parse(jsonDefinition);
		try {
		 String fullPath = path+"."+propertyName;
		if (parse.read(fullPath) != null) {
			 return true;
		 }
		} catch (PathNotFoundException e) {
			return false;
		}
		return false; 
	}

	@Override
	protected boolean include(BeanPropertyWriter writer) {
		return true;
	}

	@Override
	protected boolean include(PropertyWriter writer) {
		return true;
	}
}