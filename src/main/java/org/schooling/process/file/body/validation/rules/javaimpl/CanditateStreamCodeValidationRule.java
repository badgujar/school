package org.schooling.process.file.body.validation.rules.javaimpl;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.utils.Constant;
import org.schooling.process.utils.RecordUtils;
import org.springframework.stereotype.Component;

@Component
public class CanditateStreamCodeValidationRule extends AbstractFileBodyValidationRule {

	private Logger log = LogManager.getLogger(CanditateStreamCodeValidationRule.class);
	private static final int START_INDEX = 4;
	private static final int END_INDEX = 5;

	@Override
	public boolean executeRule(StudentTransactionRecord studentTransactionRecord, ExecutionContext ctx) {

		try {
			String studentTxnRecordMessage = (String) ctx.get(Constant.STUDENT_TXN_RECORD_MESSAGE);

			String streamCode = RecordUtils
					.fetchCharactersByStartIndexAndEndIndex(studentTxnRecordMessage.toCharArray(), START_INDEX,
							END_INDEX);

			if (streamCodeRange().collect(Collectors.toList()).contains(Integer.valueOf(streamCode))) {
				studentTransactionRecord.setStreamCode(streamCode);
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

	@PostConstruct
	public Stream<Integer> streamCodeRange() {
		return IntStream.range(1, 8).boxed();
	}
}
