package com.sample.prototypesample.file.validation.rules.javaimpl;

import org.apache.camel.Exchange;

import com.sample.prototypesample.rule.JavaRule;

public abstract class AbstractFileValidationRule implements JavaRule {

	@Override
	public boolean execute(Object... params) throws Exception {
		Exchange exchange = (Exchange) params[0];
		return this.executeRule(exchange);
	}

	public abstract boolean executeRule(Exchange exchange);

}
