package org.schooling.process.file.body.validation.rules.javaimpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.model.Subject;
import org.schooling.process.model.Subject.MilSubject;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateMilSubValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateMilSubValidationRule.class);

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			List<Subject> subjects = studentTransactionRecord.getSubject();

			Subject subject = new Subject();
			String subjectCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 159, 162);

			if (subjectCode != null && MilSubject.isValidMilSubject(subjectCode)) {
				subject.setCode(subjectCode);
			} else {
				studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
				return false;
			}

			subjects.add(buildTheory(studentTxnRecordMessage, subject));
			studentTransactionRecord.setSubject(subjects);
			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}

	}

	private Subject buildTheory(String studentTxnRecordMessage, Subject subject) {

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 162, 163));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 163, 166));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 166, 168));
		return subject;
	}

}
