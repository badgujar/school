package org.schooling.process.file.validation.rules.javaimpl;

import org.schooling.process.core.ExecutionContext;
import org.schooling.process.rule.JavaRule;

public abstract class AbstractFileValidationRule implements JavaRule {

	@Override
	public boolean execute(Object... params) throws Exception {
		ExecutionContext ctx = (ExecutionContext) params[0];
		return this.executeRule(ctx);
	}

	public abstract boolean executeRule(ExecutionContext ctx);

}
