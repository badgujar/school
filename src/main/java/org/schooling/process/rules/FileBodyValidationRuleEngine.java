package org.schooling.process.rules;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.schooling.process.rule.JavaRule;
import org.schooling.process.rule.RuleEngine;
import org.springframework.stereotype.Component;

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
