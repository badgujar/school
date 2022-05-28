package org.schooling.process.file.body.validation.rules.javaimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.input.AppearingCode;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateAppearingCodeValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateAppearingCodeValidationRule.class);
	private static final int START_INDEX = 130;
	private static final int END_INDEX = 131;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String appearingCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), START_INDEX,
							END_INDEX);

			if (appearingCode != null && (AppearingCode.ARTS.code().equalsIgnoreCase(appearingCode)
					|| AppearingCode.COMMERCE.code().equalsIgnoreCase(appearingCode)
					|| AppearingCode.SCIENCE.code().equalsIgnoreCase(appearingCode))) {
				studentTransactionRecord.setAppearingCode(appearingCode);
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
