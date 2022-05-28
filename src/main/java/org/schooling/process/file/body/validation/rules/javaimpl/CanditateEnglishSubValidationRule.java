package org.schooling.process.file.body.validation.rules.javaimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.model.Subject;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateEnglishSubValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateEnglishSubValidationRule.class);

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {

			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			List<Subject> subjects = new ArrayList<>();

			subjects.add(buildPaper1(studentTxnRecordMessage));
			subjects.add(buildPaper2(studentTxnRecordMessage));

			studentTransactionRecord.setSubject(subjects);

			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}

	private Subject buildPaper2(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 153, 154));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 154, 157));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 157, 159));
		subject.setTheory(true);
		return subject;

	}

	private Subject buildPaper1(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setCode(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 144, 146));

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 147, 148));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 148, 151));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 151, 153));
		subject.setTheory(true);

		return subject;
	}

}
