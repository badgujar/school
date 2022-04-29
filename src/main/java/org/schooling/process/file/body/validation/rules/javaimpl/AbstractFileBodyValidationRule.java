package org.schooling.process.file.body.validation.rules.javaimpl;

import org.schooling.process.core.ExecutionContext;
import org.schooling.process.model.StudentTransactionRecord;
import org.schooling.process.rule.JavaRule;

public abstract class AbstractFileBodyValidationRule implements JavaRule {

	@Override
	public boolean execute(Object... params) throws Exception {
		StudentTransactionRecord studentTxnRecord = (StudentTransactionRecord) params[0];
		ExecutionContext ctx = (ExecutionContext) params[1];
		return this.executeRule(studentTxnRecord, ctx);
	}

	public abstract boolean executeRule(StudentTransactionRecord studentTxnRecord, ExecutionContext ctx);

}

