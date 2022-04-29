package org.schooling.process.file.body.validation.rules.javaimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.Practical;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.model.Subject;
import org.schooling.process.model.Theory;
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

			List<Theory> theories = new ArrayList<>();
			buildTheory1(studentTxnRecordMessage, theories);
			buildTheory2(studentTxnRecordMessage, theories);
			subject.setListOfTheories(theories);

			buildPractical(studentTxnRecordMessage, subject);

			subjects.add(subject);
			studentTransactionRecord.setSubject(subjects);
			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
	}

	private void buildTheory2(String studentTxnRecordMessage, List<Theory> theories) {

		Theory theory = new Theory();

		theory.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 177, 178));
		theory.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 178, 181));
		theory.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 181, 183));

		theories.add(theory);
	}

	private void buildTheory1(String studentTxnRecordMessage, List<Theory> theories) {

		Theory theory = new Theory();

		theory.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 171, 172));
		theory.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 172, 175));
		theory.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 175, 177));

		theories.add(theory);
	}

	private void buildPractical(String studentTxnRecordMessage, Subject subject) {

		Practical pratical = new Practical();

		pratical.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 183, 184));
		pratical.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 184, 187));
		pratical.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 187, 189));

		List<Practical> praticals = new ArrayList<>();
		praticals.add(pratical);
		subject.setListOfPractical(praticals);

	}

}
