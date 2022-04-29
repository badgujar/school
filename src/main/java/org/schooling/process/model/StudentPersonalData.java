package org.schooling.process.model;

import lombok.Data;

@Data
public class StudentPersonalData {

	private String studentName;
	private String studentFatherName;
	private String studentMotherName;
	private String gender;
	private String communityCode;

	public StudentPersonalData() {

	}

	public StudentPersonalData(String studentName) {
		this.studentName = studentName;

	}

	public StudentPersonalData(String studentName, String studentFatherName) {
		this(studentName);
		this.studentFatherName = studentFatherName;
	}

	public StudentPersonalData(String studentName, String studentFatherName, String studentMotherName) {
		this(studentName, studentFatherName);
		this.studentMotherName = studentMotherName;
	}

	public StudentPersonalData(String studentName, String studentFatherName, String studentMotherName, String gender) {
		this(studentName, studentFatherName, studentMotherName);
		this.gender = gender;
	}

	public StudentPersonalData(String studentName, String studentFatherName, String studentMotherName, String gender,
			String communityCode) {
		this(studentName, studentFatherName, studentMotherName, gender);
		this.communityCode = communityCode;

	}
}
