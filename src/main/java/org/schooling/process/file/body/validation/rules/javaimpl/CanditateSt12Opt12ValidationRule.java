package org.schooling.process.file.body.validation.rules.javaimpl;

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
public class CanditateSt12Opt12ValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateSt12Opt12ValidationRule.class);

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			List<Subject> subjects = studentTransactionRecord.getSubject();

			Subject subject = new Subject();

			String subjectCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 168, 171);

			subject.setCode(subjectCode);

			subjects.add(buildTheory1(studentTxnRecordMessage, subject));
			subjects.add(buildTheory2(studentTxnRecordMessage));
			subjects.add(buildPractical(studentTxnRecordMessage));

			studentTransactionRecord.setSubject(subjects);

			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}

	private Subject buildTheory2(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 177, 178));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 178, 181));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 181, 183));

		return subject;
	}

	private Subject buildTheory1(String studentTxnRecordMessage, Subject subject) {

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 171, 172));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 172, 175));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 175, 177));

		return subject;
	}

	private Subject buildPractical(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 183, 184));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 184, 187));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 187, 189));

		return subject;
	}

}
