package org.schooling.process.file.body.validation.rules.javaimpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateDistrictCodeValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateDistrictCodeValidationRule.class);
	private static final int DISTRICT_START_INDEX = 0;
	private static final int DISTRICT_END_INDEX = 2;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			// AA, AB , AC
//			HashSet<String> dist= new  
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			if (isValidStudentString(studentTxnRecordMessage)) {

				String districtCode = RecordUtils.fetchCharactersByStartIndexAndEndIndex(
						studentTxnRecordMessage.toCharArray(), DISTRICT_START_INDEX, DISTRICT_END_INDEX);

				studentTransactionRecord.setDistrictCode(districtCode);

				return true;
			}

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
		}

		studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
		return false;
	}

	private boolean isValidStudentString(String studentTxnRecordMessage) {

		if (studentTxnRecordMessage == null) {
			return false;
		}

		studentTxnRecordMessage = studentTxnRecordMessage.replaceAll("[^a-zA-Z0-9]", StringUtils.EMPTY);

		if (studentTxnRecordMessage.equals(StringUtils.EMPTY)) {
			return false;
		}

		return true;
	}

}
