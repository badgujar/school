package org.schooling.process.model;

import java.util.List;

import lombok.Data;

@Data
public class Subject {

	private String code;
	private List<Theory> listOfTheories;
	private List<Practical> listOfPractical;
}
