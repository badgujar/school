package org.schooling.process.file.body.validation.rules.javaimpl;

import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.springframework.stereotype.Component;

@Component
public class CanditateSt12Papers1RedefinesValidationRule extends AbstractFileBodyValidationRule {

	@Override
	public boolean executeRule(StudentTransactionRecord studenTransactionRecord, ExecutionContext ctx) {
		return false;
	}

}
