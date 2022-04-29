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
public class CanditateEnglishSubValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateEnglishSubValidationRule.class);

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {

			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			List<Subject> subjects = new ArrayList<>();
			Subject subject = new Subject();

			subject.setCode(RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
					144, 146));

			buildTheory(studentTxnRecordMessage, subject);

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

	private void buildPractical(String studentTxnRecordMessage, Subject subject) {

		Practical pratical = new Practical();

		pratical.setSubjectTag(RecordUtils
				.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 153, 154));
		pratical.setMarks(RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
				154, 157));
		pratical.setHcrMarks(RecordUtils
				.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 157, 159));

		List<Practical> praticals = new ArrayList<>();
		praticals.add(pratical);
		subject.setListOfPractical(praticals);

	}

	private void buildTheory(String studentTxnRecordMessage, Subject subject) {

		Theory theory = new Theory();

		theory.setSubjectTag(RecordUtils
				.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 147, 148));
		theory.setMarks(RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
				148, 151));
		theory.setHcrMarks(RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(),
				151, 153));

		List<Theory> theories = new ArrayList<>();
		theories.add(theory);
		subject.setListOfTheories(theories);
	}

}
