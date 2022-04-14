package com.sample.prototypesample.rules;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sample.prototypesample.rule.JavaRule;
import com.sample.prototypesample.rule.RuleEngine;

@Component
public class FileBodyValidationRuleEngine implements RuleEngine {

	private List<JavaRule> rules;

	@Override
	public void executeRules(Object... params) throws Exception {
		for (JavaRule javaRule : rules) {
			boolean nextRule = javaRule.execute(params);
			if (!nextRule) {
				break;
			}
		}
	}

	@PostConstruct
	private void init() {
		rules = new LinkedList<>();
//		rules.add(null)
	}

}
