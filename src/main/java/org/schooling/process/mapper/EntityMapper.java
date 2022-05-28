package org.schooling.process.mapper;

import org.schooling.process.entity.Marks;
import org.schooling.process.entity.Registration;
import org.schooling.process.entity.StudentMaster;
import org.schooling.process.model.StudentPersonalData;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.model.Subject;
import org.schooling.process.utils.RecordUtils;

public class EntityMapper {

	public static StudentMaster mapToStudentMaster(StudentTransactionRecord studentTransactionRecord,
			StudentPersonalData studentPersonalData) {

		StudentMaster studentMaster = new StudentMaster();

		studentMaster.setRegistrationId(buildStudentRegistrationNumber(studentTransactionRecord));
		studentMaster.setStudentName(studentPersonalData.getStudentName());
		studentMaster.setFatherName(studentPersonalData.getStudentFatherName());
		studentMaster.setMotherName(studentPersonalData.getStudentMotherName());
		studentMaster.setSex(studentPersonalData.getGender());
		studentMaster.setCommunityCode(studentPersonalData.getCommunityCode());

		studentMaster.setTotalSubjects(studentTransactionRecord.getSubject().size());
		studentMaster.setStreamCategory(studentTransactionRecord.getCategoryCode());
		studentMaster.setAppearingCode(studentTransactionRecord.getAppearingCode());
		studentMaster.setStream(studentTransactionRecord.getStreamCode());
		studentMaster.setPreviousRegistrationId(studentTransactionRecord.getPreviousRollNumber());

		return studentMaster;
	}

	public static Registration mapToRegistration(StudentTransactionRecord studentTransactionRecord) {

		Registration registration = new Registration();

		registration.setRegistrationId(buildStudentRegistrationNumber(studentTransactionRecord));
		registration.setDistrictCode(studentTransactionRecord.getDistrictCode());
		registration.setCenterCode(studentTransactionRecord.getCenterCode());
		registration.setStreamCode(studentTransactionRecord.getStreamCode());
		registration.setCollegeCode(studentTransactionRecord.getCollegeCode());

		String registrationNumber = studentTransactionRecord.getRegistrationNumber();
		registration.setRegistrationNumber(registrationNumber);
		registration.setRegistrationYear(getRegistrationYear(registrationNumber));

		return registration;

	}

	public static Marks mapToMarks(StudentTransactionRecord studentTransactionRecord, Subject subject) {

		Marks marks = new Marks();

		marks.setRegistrationId(buildStudentRegistrationNumber(studentTransactionRecord));
		marks.setSubjectCode(subject.getCode());
		marks.setSubjectTag(subject.getSubjectTag());
		
		return marks;
	}

	private static String buildStudentRegistrationNumber(StudentTransactionRecord studentTransactionRecord) {

		return studentTransactionRecord.getDistrictCode() + studentTransactionRecord.getCollegeCode()
				+ studentTransactionRecord.getStreamCode() + studentTransactionRecord.getSerialNumber();
	}

	public static String getRegistrationYear(String registrationNumber) {
		return RecordUtils.fetchCharactersByStartIndexAndEndIndex(registrationNumber.toCharArray(), 1, 4);
	}

}
