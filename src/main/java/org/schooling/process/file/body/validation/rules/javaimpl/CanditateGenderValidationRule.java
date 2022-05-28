package org.schooling.process.file.body.validation.rules.javaimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentPersonalData;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateGenderValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateGenderValidationRule.class);
	private static final int START_INDEX = 127;
	private static final int END_INDEX = 128;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String gender = RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
					START_INDEX, END_INDEX);

			if (Constant.GENDER_MALE_ABBREVATION.equalsIgnoreCase(gender)
					|| Constant.GENDER_FEMALE_ABBREVATION.equalsIgnoreCase(gender)) {
				StudentPersonalData studentPersonalData = studentTransactionRecord.getStudentPersonalData();
				studentPersonalData.setGender(gender);

				studentTransactionRecord.setStudentPersonalData(studentPersonalData);
				return true;
			}

			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}
}
