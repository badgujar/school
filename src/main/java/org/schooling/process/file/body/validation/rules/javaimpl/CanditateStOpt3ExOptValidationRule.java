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

			List<Theory> theories = new ArrayList<>();
			buildTheory1(studentTxnRecordMessage, theories);
			buildTheory2(studentTxnRecordMessage, theories);
			subject.setListOfTheories(theories);


			List<Practical> praticals = new ArrayList<>();
			buildPractical1(studentTxnRecordMessage, praticals);
			buildPractical2(studentTxnRecordMessage, praticals);
			subject.setListOfPractical(praticals);

			subjects.add(subject);
			studentTransactionRecord.setSubject(subjects);
			return true;

		} catch (Exception e) {
			log.error("Exception occured: " + e.getMessage());
			studentTransactionRecord.setEnrollmentValidationStatus(Constant.ENROLLEMENT_STATUS_FAILED);
			return false;
		}
		
	}

	private void buildTheory1(String studentTxnRecordMessage, List<Theory> theories) {

		Theory theory = new Theory();

		theory.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 192, 193));
		theory.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 193, 196));
		theory.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 196, 198));

		theories.add(theory);
	}

	private void buildTheory2(String studentTxnRecordMessage, List<Theory> theories) {

		Theory theory = new Theory();

		theory.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 198, 199));
		theory.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 199, 202));
		theory.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 202, 204));

		theories.add(theory);
	}

	private void buildPractical1(String studentTxnRecordMessage, List<Practical> praticals) {

		Practical pratical = new Practical();

		pratical.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 204, 205));
		pratical.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 205, 208));
		pratical.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 208, 210));
		praticals.add(pratical);
	}

	private void buildPractical2(String studentTxnRecordMessage, List<Practical> praticals) {

		Practical pratical = new Practical();

		pratical.setSubjectTag(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 210, 211));
		pratical.setMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 211, 214));
		pratical.setHcrMarks(
				RecordUtils.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), 214, 216));

		praticals.add(pratical);


	}
}
