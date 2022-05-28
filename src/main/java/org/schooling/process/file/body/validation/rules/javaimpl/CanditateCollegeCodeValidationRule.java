package org.schooling.process.file.body.validation.rules.javaimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateCollegeCodeValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateCollegeCodeValidationRule.class);
	private static final int START_INDEX = 2;
	private static final int END_INDEX = 4;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {

			// HaashSet
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String collegeCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), START_INDEX,
							END_INDEX);

			studentTransactionRecord.setCollegeCode(collegeCode);

			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}

}
