package org.schooling.process.model;

import java.util.Date;

import lombok.Data;

@Data
public class StudentRecordMetadata {

	private String fileName;
	private String rawData;
	private Date fileProcessDate;
}
