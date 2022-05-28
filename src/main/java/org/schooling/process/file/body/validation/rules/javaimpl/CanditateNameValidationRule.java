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
public class CanditateNameValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateNameValidationRule.class);
	private static final int STUDENT_NAME_START_INDEX = 12;
	private static final int STUDENT_NAME_END_INDEX = 47;
	private static final int FATHER_NAME_START_INDEX = 48;
	private static final int FATHER_NAME_END_INDEX = 82;
	private static final int MOTHER_NAME_START_INDEX = 83;
	private static final int MOTHER_NAME_END_INDEX = 117;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String studentName = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
							STUDENT_NAME_START_INDEX, STUDENT_NAME_END_INDEX);
			String studentFatherName = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
							FATHER_NAME_START_INDEX, FATHER_NAME_END_INDEX);
			String studentMotherName = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
							MOTHER_NAME_START_INDEX, MOTHER_NAME_END_INDEX);

			studentTransactionRecord
					.setStudentPersonalData(new StudentPersonalData(studentName, studentFatherName, studentMotherName));

			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}

}
