package org.schooling.process.file.body.validation.rules.javaimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.input.CategoryCode;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateCategoryCodeValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateCategoryCodeValidationRule.class);
	private static final int START_INDEX = 129;
	private static final int END_INDEX = 130;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String categoryCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), START_INDEX,
							END_INDEX);

			if (categoryCode != null && (CategoryCode.REGULAR.code().equalsIgnoreCase(categoryCode)
					|| CategoryCode.EX_REGULAR.code().equalsIgnoreCase(categoryCode)
					|| CategoryCode.PRIVATE.code().equalsIgnoreCase(categoryCode))) {
				studentTransactionRecord.setCategoryCode(categoryCode);
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
