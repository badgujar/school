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
public class CanditateStOpt3ExOptValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateStOpt3ExOptValidationRule.class);

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			List<Subject> subjects = studentTransactionRecord.getSubject();
			Subject subject = new Subject();
			String subjectCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 189, 192);

			subject.setCode(subjectCode);

			subjects.add(buildTheory1(studentTxnRecordMessage, subject));
			subjects.add(subject);

			subjects.add(buildTheory2(studentTxnRecordMessage));

			subjects.add(buildPractical1(studentTxnRecordMessage));
			subjects.add(buildPractical2(studentTxnRecordMessage));

			studentTransactionRecord.setSubject(subjects);
			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
		
	}

	private Subject buildTheory1(String studentTxnRecordMessage, Subject subject) {

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 192, 193));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 193, 196));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 196, 198));

		return subject;
	}

	private Subject buildTheory2(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 198, 199));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 199, 202));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 202, 204));
		return subject;
	}

	private Subject buildPractical1(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 204, 205));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 205, 208));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 208, 210));
		return subject;
	}

	private Subject buildPractical2(String studentTxnRecordMessage) {

		Subject subject = new Subject();

		subject.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 210, 211));
		subject.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 211, 214));
		subject.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 214, 216));
		return subject;


	}
}
