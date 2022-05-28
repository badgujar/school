package org.schooling.process.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class StudentTransactionRecord extends StudentRecordMetadata{

	private String enrollmentValidationStatus;
	private String districtCode;
	private String collegeCode;
	private String streamCode;
	private String serialNumber;
	private String centerCode;
	private StudentPersonalData studentPersonalData;
	private String registrationNumber;
	private String categoryCode;
	private String appearingCode;
	private String previousRollNumber;
	private List<Subject> subject;
	private String redefinedStreamData;

}
